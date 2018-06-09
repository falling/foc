package zj.gov.foc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.HQBean;
import zj.gov.foc.repository.HQRepository;
import zj.gov.foc.repository.QJRepository;
import zj.gov.foc.repository.UserRepository;
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
    QJRepository qjRepository;

    @Transactional
    public HQBean addHQ(HQVO hqvo, Long id){
        HQBean bean = new HQBean();
        BeanUtils.copyProperties(hqvo,bean);
        bean.setRegistrant(id);
        if (bean.getReg_date()==null)
            bean.setReg_date(new Date(System.currentTimeMillis()));
        bean.setDel("0");
        return hqRepository.save(bean);
    }

    @Transactional
    public boolean deleteHQ(long hqid){
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
    public HQBean update(HQVO vo) {
        HQBean bean = hqRepository.getById(vo.getHq_id());
        if(bean == null) return null;
        BeanUtils.copyProperties(vo,bean);
        return hqRepository.save(bean);
    }


    @PersistenceContext
    private EntityManager entityManager;

    public SearchVO search(String col, String value) {
        SearchVO<HQVO> searchVO = new SearchVO<>();
        String sql = "SELECT * FROM hq WHERE "+col+" LIKE '%"+value+"%' AND del = '0'";
        Query query = entityManager.createNativeQuery(sql,HQBean.class);
        List<HQBean> resultList = query.getResultList();
        List<HQVO> returnList = new ArrayList<>();
        resultList.forEach(result->{
            String registrant_name = userRepository
                    .getById(result.getRegistrant())
                    .getName();
            HQVO vo = new HQVO();
            BeanUtils.copyProperties(result,vo);
            vo.setRegistrant_name(registrant_name);
            returnList.add(vo);
        });
        searchVO.setResult(returnList);
        return searchVO;
    }

    public boolean confirmPassport(String passport_no, long id) {
        return hqRepository.confirmPassport(passport_no,id) == null;
    }

    @Transactional
    public Iterable<HQBean> save(List<HQVO> hqvoList, Long id) {
        List<HQBean> hqBeans = new ArrayList<>();
        hqvoList.forEach(hqvo -> {
            HQBean bean = new HQBean();
            BeanUtils.copyProperties(hqvo,bean);
            bean.setRegistrant(id);
            if (bean.getReg_date()==null)
                bean.setReg_date(new Date(System.currentTimeMillis()));
            bean.setDel("0");
            hqBeans.add(bean);
        });
        return hqRepository.save(hqBeans);
    }
}
