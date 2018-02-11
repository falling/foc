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
import zj.gov.foc.vo.QjVO;
import zj.gov.foc.vo.QjVOwithRelation;
import zj.gov.foc.vo.RelationVO;
import zj.gov.foc.vo.VO;

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
    public boolean saveWithrelation(QjVO qjVO, List<RelationVO> relationVOList) {
        QJBean qjBean = new QJBean();
        BeanUtils.copyProperties(qjVO, qjBean);
        qjBean.setDel("0");
        QJBean bean = qjRepository.save(qjBean);
        List<RelationBean> relationBeanList = new ArrayList<>();
        RelationCover(relationVOList, bean, relationBeanList);
        relationRepository.save(relationBeanList);
        return true;
    }


    @Transactional
    public VO loadByPassport(String passport_no) {
        QJBean bean = qjRepository.loadByPassport(passport_no);
        QjVOwithRelation vo = null;
        if (bean != null) {
            vo = new QjVOwithRelation();
            QjVO qjVO = new QjVO();
            BeanUtils.copyProperties(bean, qjVO);
            vo.setValue(qjVO);

            //relation
            List relations = relationRepository.getByQjID(qjVO.getQj_id());
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

    public boolean confirmPassport(String passport_no, long id) {
        return qjRepository.confirmPassport(passport_no,id) == null;
    }

    @Transactional
    public boolean updateWithrelation(QjVO qjVO, List<RelationVO> relationVOList) {
        QJBean bean = qjRepository.getById(qjVO.getQj_id());
        if (bean == null) return false;
        BeanUtils.copyProperties(qjVO, bean);
        if(qjRepository.save(bean) == null) return false;

        //relation
        relationRepository.deleteByqjId(bean.getQj_id());
        List<RelationBean> relationBeanList = new ArrayList<>();
        RelationCover(relationVOList, bean, relationBeanList);
        relationRepository.save(relationBeanList);
        return true;

    }

    private void RelationCover(List<RelationVO> relationVOList, QJBean bean, List<RelationBean> relationBeanList) {
        relationVOList.forEach(e -> {
            e.setQj_id(bean.getQj_id());
            RelationBean bean1 = new RelationBean();
            BeanUtils.copyProperties(e, bean1);
            relationBeanList.add(bean1);
        });
    }

    @Transactional
    public boolean delete(Long id) {
         if(qjRepository.delete(id)  == 1){
             relationRepository.deleteByqjId(id);
             return true;
         }
         return false;
    }
}
