package zj.gov.foc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.HQBean;
import zj.gov.foc.po.RelationBean;
import zj.gov.foc.repository.HQRepository;
import zj.gov.foc.repository.QJRepository;
import zj.gov.foc.repository.RelationRepository;
import zj.gov.foc.repository.UserRepository;
import zj.gov.foc.util.CommonFunction;
import zj.gov.foc.vo.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Service
public class HQService {

    @Autowired
    HQRepository hqRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RelationRepository relationRepository;

    @Autowired
    QJRepository qjRepository;

    @Transactional
    public HQBean addHQ(HQVOWithRelation hqvOwithRelation, UserVO userVO){
        HQVO hqvo = hqvOwithRelation.getValue();

        if(hqRepository.loadByPassport(hqvo.getPassport_no())!=null){
            return null;
        }
        HQBean bean = new HQBean();
        BeanUtils.copyProperties(hqvo,bean);
        bean.setRegistrant(userVO.getId());
        bean.setReg_date(new Date(System.currentTimeMillis()));
        bean.setDel("0");
        bean.setRemarks("");
        HQBean new_bean =hqRepository.save(bean);
        if(new_bean != null){
            saveHqRalation(bean,hqvOwithRelation);
            return new_bean;
        }else{
            return null;
        }
    }

    @Transactional
    public boolean deleteHQ(long hqid){
        if(hqRepository.deleteHQ(hqid) == 1){
            relationRepository.deletebyoId(hqid);
            return true;
        }
        return false;
    }


    public VO loadByPassport(String passport_no) {
        HQBean bean = hqRepository.loadByPassport(passport_no);
        HQVOWithRelation vo = null;
        if(bean!=null){
            vo = new HQVOWithRelation();
            HQVO hqvo = new HQVO();
            BeanUtils.copyProperties(bean,hqvo);
            String registrant_name = userRepository
                    .getById(bean.getRegistrant())
                    .getName();
            hqvo.setRegistrant_name(registrant_name);
            vo.setValue(hqvo);

            //relation
            List relations = relationRepository.getByOId(hqvo.getHq_id());
            List<RelationVO> relationVOList = new ArrayList<>();
            CommonFunction.getQJRelationList(relations,relationVOList,qjRepository);
            vo.setRelationList(relationVOList);


        }
        return vo;
    }

    public VO loadByPassportWithoutRelation(String passport_no) {
        HQBean bean = hqRepository.loadByPassport(passport_no);
        HQVO vo = null;
        if(bean!=null){
            vo = new HQVO();
            BeanUtils.copyProperties(bean,vo);
            String registrant_name = userRepository
                    .getById(bean.getRegistrant())
                    .getName();
            vo.setRegistrant_name(registrant_name);

        }
        return vo;
    }

    @Transactional
    public HQBean update(HQVOWithRelation hqvOwithRelation) {
        HQVO vo = hqvOwithRelation.getValue();
        HQBean bean = hqRepository.getById(vo.getHq_id());
        if(bean == null) return null;
        BeanUtils.copyProperties(vo,bean);
        saveHqRalation(bean,hqvOwithRelation);
        return hqRepository.save(bean);
    }

    private void saveHqRalation(HQBean bean,HQVOWithRelation hqvOwithRelation){
        relationRepository.deletebyoId(bean.getHq_id());
        List<RelationBean> relationBeanList = new ArrayList<>();
        List<RelationVO> relationVOList = hqvOwithRelation.getRelationList();
        relationVOList.forEach(e -> {
            e.setO_id(bean.getHq_id());
            RelationBean bean1 = new RelationBean();
            BeanUtils.copyProperties(e, bean1);
            bean1.setType("华侨");
            bean1.setId(null);
            relationBeanList.add(bean1);
        });
        relationRepository.save(relationBeanList);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public SearchVO search(String col, String value) {
        SearchVO<HQVOWithRelation> searchVO = new SearchVO<>();
        String sql = "SELECT * FROM hq WHERE "+col+" LIKE '%"+value+"%' AND del = '0'";
        Query query = entityManager.createNativeQuery(sql,HQBean.class);
        List<HQBean> resultList = query.getResultList();
        List<HQVOWithRelation> returnList = new ArrayList<>();
        resultList.forEach(result->{
            String registrant_name = userRepository
                    .getById(result.getRegistrant())
                    .getName();
            HQVO vo = new HQVO();
            BeanUtils.copyProperties(result,vo);
            vo.setRegistrant_name(registrant_name);

            HQVOWithRelation hqvOwithRelation = new HQVOWithRelation();
            hqvOwithRelation.setValue(vo);

            List relations = relationRepository.getByOId(vo.getHq_id());
            List<RelationVO> relationVOList = new ArrayList<>();
            CommonFunction.getQJRelationList(relations,relationVOList,qjRepository);
            hqvOwithRelation.setRelationList(relationVOList);
            returnList.add(hqvOwithRelation);
        });
        searchVO.setResult(returnList);
        return searchVO;
    }

    public boolean confirmPassport(String passport_no, long id) {
        return hqRepository.confirmPassport(passport_no,id) == null;
    }
}
