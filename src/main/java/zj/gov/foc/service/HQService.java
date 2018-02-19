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
import zj.gov.foc.util.InputDeal;
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
    public HQVO addHQ(HQVOwithRelation hqvOwithRelation, UserVO userVO){

        HQVO hqvo = hqvOwithRelation.getValue();
        if(!InputDeal.isChineseCharacters(hqvo.getCh_name())){
            hqvo.setInfo("中文名字为2-20个汉字");
            return hqvo;
        }
        else if (!InputDeal.isPY(hqvo.getPy_name())){
            hqvo.setInfo("拼音为1-50个字母");
            return hqvo;
        }
        if(hqRepository.loadByPassport(hqvo.getPassport_no())!=null){
            hqvo.setInfo("该护照已经录入");
            return hqvo;
        }
        HQBean bean = new HQBean();
        BeanUtils.copyProperties(hqvo,bean);
        bean.setRegistrant(userVO.getId());
        bean.setReg_date(new Date(System.currentTimeMillis()));
        bean.setDel("0");
        bean.setRemarks("");
        HQBean new_bean =hqRepository.save(bean);
        if(new_bean != null){
            hqvo.setHq_id(new_bean.getHq_id());
            saveHqRalation(bean,hqvOwithRelation);
            hqvo.setInfo("录入成功");
        }else{
            hqvo.setInfo("创建失败");
        }
        return hqvo;
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
        HQVOwithRelation vo = null;
        if(bean!=null){
            vo = new HQVOwithRelation();
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
    public HQBean update(HQVOwithRelation hqvOwithRelation) {
        HQVO vo = hqvOwithRelation.getValue();
        HQBean bean = hqRepository.getById(vo.getHq_id());
        if(bean == null) return null;
        BeanUtils.copyProperties(vo,bean);
        saveHqRalation(bean,hqvOwithRelation);
        return hqRepository.save(bean);
    }

    private void saveHqRalation(HQBean bean,HQVOwithRelation hqvOwithRelation){
        relationRepository.deletebyoId(bean.getHq_id());
        List<RelationBean> relationBeanList = new ArrayList<>();
        List<RelationVO> relationVOList = hqvOwithRelation.getRelationList();
        relationVOList.forEach(e -> {
            e.setO_id(bean.getHq_id());
            RelationBean bean1 = new RelationBean();
            BeanUtils.copyProperties(e, bean1);
            bean1.setType("华侨");
            relationBeanList.add(bean1);
        });
        relationRepository.save(relationBeanList);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public SearchVO search(String col, String value) {
        SearchVO<HQVOwithRelation> searchVO = new SearchVO<>();
        String sql = "SELECT * FROM hq WHERE "+col+" LIKE '%"+value+"%' AND del = '0'";
        Query query = entityManager.createNativeQuery(sql,HQBean.class);
        List<HQBean> resultList = query.getResultList();
        List<HQVOwithRelation> returnList = new ArrayList<>();
        resultList.forEach(result->{
            String registrant_name = userRepository
                    .getById(result.getRegistrant())
                    .getName();
            HQVO vo = new HQVO();
            BeanUtils.copyProperties(result,vo);
            vo.setRegistrant_name(registrant_name);

            HQVOwithRelation hqvOwithRelation = new HQVOwithRelation();
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
