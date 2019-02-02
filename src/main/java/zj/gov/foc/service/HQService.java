package zj.gov.foc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.HQBean;
import zj.gov.foc.repository.HQRepository;
import zj.gov.foc.repository.QJRepository;
import zj.gov.foc.repository.UserRepository;
import zj.gov.foc.vo.HQVO;
import zj.gov.foc.vo.SearchVO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;


@Service
public class HQService {

    @Autowired
    HQRepository hqRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QJRepository qjRepository;

    @Transactional
    public HQBean addHQ(HQVO hqvo, Long id){
        return getHqBean(hqvo, id);
    }

    @Transactional
    public HQBean addHQCover(HQVO hqvo, Long id){
        return getHqBean(hqvo, id);
    }

    private HQBean getHqBean(HQVO hqvo, Long id) {
        HQBean bean = new HQBean();
        BeanUtils.copyProperties(hqvo,bean);
        bean.setRegistrant(id);
        if (bean.getReg_date()==null)
            bean.setReg_date(new Date(System.currentTimeMillis()));
        bean.setDel("0");
        return hqRepository.save(bean);
    }



    @Transactional
    public boolean deleteHQ(long hqid,long userId){
        return hqRepository.deleteHQ(hqid) == 1;
    }


    public HQVO loadByPassport(String passport_no) {
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
    public HQBean update(HQVO vo,long id) {
        HQBean bean = hqRepository.getById(vo.getHq_id());
        if(bean == null) return null;
        BeanUtils.copyProperties(vo,bean);
        return hqRepository.save(bean);
    }


    @PersistenceContext
    private EntityManager entityManager;

    public SearchVO<HQVO> search(String col, String value,String manager_area) {
        SearchVO<HQVO> searchVO = new SearchVO<>();
        String sql = "SELECT hq.*, user.user_name as registrant_name FROM hq,user WHERE hq."+col+" LIKE '%"+value+"%' AND hq.del = '0' and hq.registrant = user.user_id and hq.manager_area like '" + manager_area + "%'";
        Query query = entityManager.createNativeQuery(sql,HQVO.class);
        List<HQVO> resultList = query.getResultList();
        searchVO.setResult(resultList);
        return searchVO;
    }

    public boolean confirmPassport(String passport_no, long id) {
        return hqRepository.confirmPassport(passport_no,id) == null;
    }
}
