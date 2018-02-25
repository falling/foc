package zj.gov.foc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.HQBean;
import zj.gov.foc.po.LxBean;
import zj.gov.foc.po.QJBean;
import zj.gov.foc.po.RelationBean;
import zj.gov.foc.repository.HQRepository;
import zj.gov.foc.repository.LXRepository;
import zj.gov.foc.repository.QJRepository;
import zj.gov.foc.repository.RelationRepository;
import zj.gov.foc.util.CommonFunction;
import zj.gov.foc.vo.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User: falling
 * Date: 2018/2/11
 * Time: 下午2:24
 * Description:
 */
@Service
public class QJService {

    @Autowired
    QJRepository qjRepository;

    @Autowired
    LXRepository lxRepository;

    @Autowired
    HQRepository hqRepository;

    @Autowired
    RelationRepository relationRepository;


    @Transactional
    public QJBean saveWithRelation(QjVO qjVO, List<RelationVO> relationVOList) {
        QJBean qjBean = new QJBean();
        BeanUtils.copyProperties(qjVO, qjBean);
        qjBean.setDel("0");
        QJBean bean = qjRepository.save(qjBean);
        List<RelationBean> relationBeanList = new ArrayList<>();
        RelationCover(relationVOList, bean, relationBeanList);
        relationRepository.save(relationBeanList);
        return bean;
    }


    @Transactional
    public QjVOwithRelation loadByPassport(String passport_no) {
        QJBean bean = qjRepository.loadByPassport(passport_no);
        QjVOwithRelation vo = null;
        if (bean != null) {
            vo = new QjVOwithRelation();
            QjVO qjVO = new QjVO();
            BeanUtils.copyProperties(bean, qjVO);
            vo.setValue(qjVO);

            //relation
            List<RelationBean> relations = relationRepository.getByQjID(qjVO.getQj_id());
            List<RelationVO> relationVOList = new ArrayList<>();
            relations.forEach(e -> {
                RelationVO relationVO = new RelationVO();
                BeanUtils.copyProperties(e, relationVO);
                if (relationVO.getType().equals("华侨")) {
                    HQBean hqBean = hqRepository.getById(relationVO.getO_id());
                    relationVO.setCh_name(hqBean.getCh_name());
                    relationVO.setPassport_no(hqBean.getPassport_no());
                    relationVO.setSex(hqBean.getSex());
                    relationVOList.add(relationVO);
                } else {
                    LxBean lxbean = lxRepository.getById(relationVO.getO_id());
                    relationVO.setCh_name(lxbean.getCh_name());
                    relationVO.setPassport_no(lxbean.getPassport_no());
                    relationVO.setSex(lxbean.getSex());
                    relationVOList.add(relationVO);
                }

            });
            vo.setRelationList(relationVOList);
        }
        return vo;
    }

    public VO loadByPassportWithoutRelation(String passport_no) {
        QJBean bean = qjRepository.loadByPassport(passport_no);
        QjVO vo = null;
        if(bean!=null){
            vo = new QjVO();
            BeanUtils.copyProperties(bean,vo);
        }
        return vo;
    }

    public boolean confirmPassport(String passport_no, long id) {
        return qjRepository.confirmPassport(passport_no,id) == null;
    }

    @Transactional
    public QJBean updateWithRelation(QjVO qjVO, List<RelationVO> relationVOList) {
        QJBean bean = qjRepository.getById(qjVO.getQj_id());
        if (bean == null) return null;
        BeanUtils.copyProperties(qjVO, bean);
        QJBean newBean = qjRepository.save(bean);
        if(newBean == null) return null;

        //relation
        relationRepository.deleteByqjId(bean.getQj_id());
        List<RelationBean> relationBeanList = new ArrayList<>();
        RelationCover(relationVOList, bean, relationBeanList);
        relationRepository.save(relationBeanList);
        return newBean;

    }

    private void RelationCover(List<RelationVO> relationVOList, QJBean bean, List<RelationBean> relationBeanList) {
        relationVOList.forEach(e -> {
            e.setQj_id(bean.getQj_id());
            RelationBean bean1 = new RelationBean();
            BeanUtils.copyProperties(e, bean1);
            bean1.setId(null);
            relationBeanList.add(bean1);
        });
    }

    @Transactional
    public boolean deleteQJ(Long id) {
         if(qjRepository.delete(id)  == 1){
             relationRepository.deleteByqjId(id);
             return true;
         }
         return false;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public SearchVO<QjVOwithRelation> search(String col, String value) {
        SearchVO<QjVOwithRelation> searchVO = new SearchVO<>();
        if(col.equals("lx_id")){
            LxBean bean = lxRepository.loadByPassport(value);
            if (bean == null) {
                return searchVO;
            }
            Long lxId = bean.getLx_id();
            List<RelationBean> relationBeanList = relationRepository.getByLxId(lxId);
            List<QjVOwithRelation> returnList = new ArrayList<>();
            getQjVOwithRelation(relationBeanList, returnList);
            searchVO.setResult(returnList);
            return searchVO;
        }else if(col.equals("hq_id")){
            HQBean bean = hqRepository.loadByPassport(value);
            if (bean == null) {
                return searchVO;
            }
            Long hqId = bean.getHq_id();
            List<RelationBean> relationBeanList = relationRepository.getByHqId(hqId);
            List<QjVOwithRelation> returnList = new ArrayList<>();
            getQjVOwithRelation(relationBeanList, returnList);
            searchVO.setResult(returnList);
            return searchVO;

        }else{
            String sql = "SELECT * FROM qj WHERE "+col+" LIKE '%"+value+"%' AND del = '0'";
            Query query = entityManager.createNativeQuery(sql,QJBean.class);
            List<QJBean> resultList = query.getResultList();
            List<QjVOwithRelation> returnList = new ArrayList<>();
            resultList.forEach(result->{
                QjVO vo = new QjVO();
                BeanUtils.copyProperties(result,vo);
                QjVOwithRelation qjVOwithRelation = new QjVOwithRelation();
                qjVOwithRelation.setValue(vo);

                List<RelationBean> relations = relationRepository.getByQjID(vo.getQj_id());
                List<RelationVO> relationVOList = new ArrayList<>();
                CommonFunction.getQJRelationList(relations,relationVOList,hqRepository,lxRepository);
                qjVOwithRelation.setRelationList(relationVOList);
                returnList.add(qjVOwithRelation);
            });
            searchVO.setResult(returnList);
            return searchVO;

        }
    }

    private void getQjVOwithRelation(List<RelationBean> relationBeanList, List<QjVOwithRelation> returnList) {
        relationBeanList.forEach(relationBean->{
            QjVOwithRelation qjVOwithRelation = new QjVOwithRelation();
            QJBean qjBean = qjRepository.getById(relationBean.getQj_id());
            QjVO qjVO = new QjVO();
            BeanUtils.copyProperties(qjBean,qjVO);
            qjVOwithRelation.setValue(qjVO);

            List<RelationBean> relations = relationRepository.getByQjID(qjVO.getQj_id());
            List<RelationVO> relationVOList = new ArrayList<>();
            CommonFunction.getQJRelationList(relations,relationVOList,hqRepository,lxRepository);
            qjVOwithRelation.setRelationList(relationVOList);
            returnList.add(qjVOwithRelation);

        });
    }
}
