package zj.gov.foc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.LxBean;
import zj.gov.foc.repository.LXRepository;
import zj.gov.foc.repository.QJRepository;
import zj.gov.foc.repository.UserRepository;
import zj.gov.foc.vo.LxVO;
import zj.gov.foc.vo.SearchVO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Date;
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

    @Transactional
    public LxBean addLX(LxVO lxVO, Long id) {
        return getLxBean(lxVO, id);
    }

    public LxBean addLXCover(LxVO lxVO, Long id) {
        return getLxBean(lxVO, id);
    }

    private LxBean getLxBean(LxVO lxVO, Long id) {
        LxBean bean = new LxBean();
        BeanUtils.copyProperties(lxVO,bean);
        bean.setReg_date(new Date(System.currentTimeMillis()));
        bean.setDel("0");
        bean.setRegistrant(id);
        return lxRepository.save(bean);
    }

    public LxVO loadByPassport(String passport_no) {
        LxBean bean = lxRepository.loadByPassport(passport_no);
        LxVO lxVO = null;
        if(bean!=null){
            lxVO = new LxVO();
            BeanUtils.copyProperties(bean,lxVO);
            String registrant_name = userRepository
                    .getById(bean.getRegistrant())
                    .getName();
            lxVO.setRegistrant_name(registrant_name);
        }
        return lxVO;
    }

    public boolean confirmPassport(String passport_no, long id) {
        return lxRepository.confirmPassport(passport_no,id)==null;
    }

    @Transactional
    public LxBean update(LxVO vo,long id) {
        LxBean bean = lxRepository.getById(vo.getLx_id());
        if(bean == null) return null;
        BeanUtils.copyProperties(vo,bean);
        return lxRepository.save(bean);
    }

    @Transactional
    public boolean deleteLX(Long id,Long userId) {
        return lxRepository.deleteLx(id) == 1;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public SearchVO<LxVO> search(String col, String value,String  manager_area) {
        SearchVO<LxVO> searchVO = new SearchVO<>();
        String sql = "SELECT lx.*,user.name as registrant_name FROM lx , user WHERE lx."+col+" LIKE '%"+value+"%' AND lx.del = '0'  and lx.registrant = user.user_id and lx.manager_area like '"+ manager_area + "%'";
        Query query = entityManager.createNativeQuery(sql,LxVO.class);
        List<LxVO> resultList = query.getResultList();
        searchVO.setResult(resultList);
        return searchVO;
    }
}
