package zj.gov.foc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.LxBean;
import zj.gov.foc.po.RelationBean;
import zj.gov.foc.repository.LXRepository;
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

/**
 * Created by User: falling
 * Date: 2018/1/21
 * Time: 下午5:13
 * Description:
 */
@Service
public class LXService {
    @Autowired
    LXRepository lxRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QJRepository qjRepository;

    @Autowired
    RelationRepository relationRepository;

    @Transactional
    public LxBean addLX(LxVOwithRelation lxVOwithRelation, Long id) {
        LxVO lxVO = lxVOwithRelation.getValue();
        if(lxRepository.loadByPassport(lxVO.getPassport_no())!=null){
            return null;
        }
        LxBean bean = new LxBean();
        BeanUtils.copyProperties(lxVO,bean);
        bean.setReg_date(new Date(System.currentTimeMillis()));
        bean.setDel("0");
        bean.setRemarks("");
        bean.setRegistrant(id);
        LxBean newBean = lxRepository.save(bean);
        if(newBean==null){
            return null;
        }
        saveLxRelation(bean,lxVOwithRelation);
        return newBean;
    }

    public LxVOwithRelation loadByPassport(String passport_no) {
        LxBean bean = lxRepository.loadByPassport(passport_no);
        LxVOwithRelation vo = null;
        if(bean!=null){
            vo = new LxVOwithRelation();
            LxVO lxVO = new LxVO();
            BeanUtils.copyProperties(bean,lxVO);
            String registrant_name = userRepository
                    .getById(bean.getRegistrant())
                    .getName();
            lxVO.setRegistrant_name(registrant_name);
            vo.setValue(lxVO);

            //relation
            List relations = relationRepository.getByOId(lxVO.getLx_id());
            List<RelationVO> relationVOList = new ArrayList<>();
            CommonFunction.getQJRelationList(relations,relationVOList,qjRepository);
            vo.setRelationList(relationVOList);
        }
        return vo;
    }
    public VO loadByPassportWithoutRelation(String passport_no) {
        LxBean bean = lxRepository.loadByPassport(passport_no);
        LxVO vo = null;
        if(bean!=null){
            vo = new LxVO();
            BeanUtils.copyProperties(bean,vo);
            String registrant_name = userRepository
                    .getById(bean.getRegistrant())
                    .getName();
            vo.setRegistrant_name(registrant_name);
        }
        return vo;
    }

    public boolean confirmPassport(String passport_no, long id) {
        return lxRepository.confirmPassport(passport_no,id)==null;
    }

    @Transactional
    public LxBean update(LxVOwithRelation lxVOwithRelation) {
        LxVO vo = lxVOwithRelation.getValue();
        LxBean bean = lxRepository.getById(vo.getLx_id());
        if(bean == null) return null;
        BeanUtils.copyProperties(vo,bean);
        LxBean lxBean = lxRepository.save(bean);

        saveLxRelation(bean,lxVOwithRelation);
        return lxBean;
    }
    private void saveLxRelation(LxBean bean,LxVOwithRelation lxVOwithRelation){
        relationRepository.deletebyoId(bean.getLx_id());
        List<RelationBean> relationBeanList = new ArrayList<>();
        List<RelationVO> relationVOList = lxVOwithRelation.getRelationList();
        relationVOList.forEach(e -> {
            e.setO_id(bean.getLx_id());
            RelationBean bean1 = new RelationBean();
            BeanUtils.copyProperties(e, bean1);
            bean1.setType("留学");
            bean1.setId(null);
            relationBeanList.add(bean1);
        });
        relationRepository.save(relationBeanList);
    }

    @Transactional
    public boolean deleteLX(Long id) {
        if(lxRepository.delete(id) ==1){
            relationRepository.deletebyoId(id);
            return true;
        }
        return false;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public SearchVO<LxVOwithRelation> search(String col, String value) {
        SearchVO<LxVOwithRelation> searchVO = new SearchVO<>();
        String sql = "SELECT * FROM lx WHERE "+col+" LIKE '%"+value+"%' AND del = '0'";
        Query query = entityManager.createNativeQuery(sql,LxBean.class);
        List<LxBean> resultList = query.getResultList();
        List<LxVOwithRelation> returnList = new ArrayList<>();
        resultList.forEach(result->{
            String registrant_name = userRepository
                    .getById(result.getRegistrant())
                    .getName();
            LxVO vo = new LxVO();
            BeanUtils.copyProperties(result,vo);
            vo.setRegistrant_name(registrant_name);

            LxVOwithRelation lxVOwithRelation = new LxVOwithRelation();
            lxVOwithRelation.setValue(vo);

            List relations = relationRepository.getByOId(vo.getLx_id());
            List<RelationVO> relationVOList = new ArrayList<>();
            CommonFunction.getQJRelationList(relations,relationVOList,qjRepository);
            lxVOwithRelation.setRelationList(relationVOList);
            returnList.add(lxVOwithRelation);
        });
        searchVO.setResult(returnList);
        return searchVO;
    }
}
