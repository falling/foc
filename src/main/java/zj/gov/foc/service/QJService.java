package zj.gov.foc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.HQBean;
import zj.gov.foc.po.LxBean;
import zj.gov.foc.po.QJBean;
import zj.gov.foc.repository.HQRepository;
import zj.gov.foc.repository.LXRepository;
import zj.gov.foc.repository.QJRepository;
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

    @Transactional
    public QJBean saveQj(QjVO qjVO,long id) {
        return getQJBean(qjVO);
    }

    public QJBean saveQjCover(QjVO qjVO,long id) {
        return getQJBean(qjVO);
    }

    private QJBean getQJBean(QjVO qjVO){
        QJBean qjBean = new QJBean();
        BeanUtils.copyProperties(qjVO, qjBean);
        qjBean.setDel("0");
        return qjRepository.save(qjBean);
    }

    public QjVO loadByPassport(String passport_no, String type) {
        QJBean bean = qjRepository.loadByPassport(passport_no, type);
        QjVO vo = null;
        if (bean != null) {
            vo = new QjVO();
            BeanUtils.copyProperties(bean, vo);
        }
        return vo;
    }

    public boolean confirmPassport(String passport_no, long id) {
        return qjRepository.confirmPassport(passport_no, id) == null;
    }

    @Transactional
    public QJBean update(QjVO qjVO,long id) {
        QJBean bean = qjRepository.getById(qjVO.getQj_id());
        if (bean == null) return null;
        BeanUtils.copyProperties(qjVO, bean);
        QJBean newBean = qjRepository.save(bean);
        if (newBean == null) return null;
        return newBean;

    }

    @Transactional
    public boolean deleteQJ(Long id,Long userId) {
        return qjRepository.deleteQJ(id) == 1;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public SearchVO<QjVO> search(String col, String value, String type,String manager_area) {
        SearchVO<QjVO> searchVO = new SearchVO<>();
        manager_area = manager_area + "%";
        if (col.equals("lx_id")) {
            LxBean bean = lxRepository.loadByPassport(value,manager_area);
            if (bean == null) {
                return searchVO;
            }
            return searchVO;
        } else if (col.equals("hq_id")) {
            HQBean bean = hqRepository.loadByPassport(value,manager_area);
            if (bean == null) {
                return searchVO;
            }
            return searchVO;

        } else {
            String sql = "SELECT * FROM qj WHERE " + col + " LIKE '%" + value + "%' AND del = '0' and manager_area like '" + manager_area + "'";
            if (type.startsWith("qj")) {
                sql = sql + " AND type = '" + type + "'";
            }
            Query query = entityManager.createNativeQuery(sql, QJBean.class);
            List<QJBean> resultList = query.getResultList();
            List<QjVO> returnList = new ArrayList<>();
            resultList.forEach(result -> {
                QjVO vo = new QjVO();
                BeanUtils.copyProperties(result, vo);
                returnList.add(vo);
            });
            searchVO.setResult(returnList);
            return searchVO;

        }
    }

}
