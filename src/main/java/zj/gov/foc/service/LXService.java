package zj.gov.foc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.LxBean;
import zj.gov.foc.po.QJBean;
import zj.gov.foc.po.RelationBean;
import zj.gov.foc.repository.LXRepository;
import zj.gov.foc.repository.QJRepository;
import zj.gov.foc.repository.RelationRepository;
import zj.gov.foc.repository.UserRepository;
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
    public boolean addLX(LxVOwithRelation lxVOwithRelation, Long id) {
        LxVO lxVO = lxVOwithRelation.getValue();
        if(lxRepository.loadByPassport(lxVO.getPassport_no())!=null){
            return false;
        }
        LxBean bean = new LxBean();
        BeanUtils.copyProperties(lxVO,bean);
        bean.setReg_date(new Date(System.currentTimeMillis()));
        bean.setDel("0");
        bean.setRemarks("");
        bean.setRegistrant(id);
        if(lxRepository.save(bean)==null){
            return false;
        }
        saveLxRelation(bean,lxVOwithRelation);
        return true;
    }

    public VO loadByPassport(String passport_no) {
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
            relations.forEach(e->{
                RelationVO relationVO = new RelationVO();
                BeanUtils.copyProperties(e, relationVO);
                QJBean qjBean = qjRepository.getById(relationVO.getQj_id());
                relationVO.setSex(qjBean.getSex());
                relationVO.setPassport_no(qjBean.getPassport_no());
                relationVO.setCh_name(qjBean.getCh_name());
                relationVOList.add(relationVO);
            });
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
    public boolean update(LxVOwithRelation lxVOwithRelation) {
        LxVO vo = lxVOwithRelation.getValue();
        LxBean bean = lxRepository.getById(vo.getLx_id());
        if(bean == null) return false;
        BeanUtils.copyProperties(vo,bean);
        lxRepository.save(bean);

        //todo update relation
        saveLxRelation(bean,lxVOwithRelation);
        return true;
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
            relationBeanList.add(bean1);
        });
        relationRepository.save(relationBeanList);
    }

    @Transactional
    public boolean delete(Long id) {
        //todo delete relation
        if(lxRepository.delete(id) ==1){
            relationRepository.deletebyoId(id);
            return true;
        }
        return false;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public SearchVO search(String col, String value) {
        SearchVO<LxVO> searchVO = new SearchVO<>();
        String sql = "SELECT * FROM lx WHERE "+col+" LIKE '%"+value+"%' AND del = '0'";
        Query query = entityManager.createNativeQuery(sql,LxBean.class);
        List<LxBean> resultList = query.getResultList();
        List<LxVO> returnList = new ArrayList<>();
        resultList.forEach(result->{
            String registrant_name = userRepository
                    .getById(result.getRegistrant())
                    .getName();
            LxVO vo = new LxVO();
            BeanUtils.copyProperties(result,vo);
            vo.setRegistrant_name(registrant_name);
            returnList.add(vo);
        });
        searchVO.setResult(returnList);
        return searchVO;
    }
}
