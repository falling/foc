package zj.gov.foc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.HQBean;
import zj.gov.foc.repository.HQRepository;
import zj.gov.foc.repository.UserRepository;
import zj.gov.foc.util.InputDeal;
import zj.gov.foc.vo.HQVO;
import zj.gov.foc.vo.SearchVO;
import zj.gov.foc.vo.UserVO;
import zj.gov.foc.vo.VO;

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

    @Transactional
    public HQVO addHQ(HQVO hqvo, UserVO userVO){

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
            hqvo.setInfo("录入成功");
        }else{
            hqvo.setInfo("创建失败");
        }
        return hqvo;
    }

    @Transactional
    public int  modifyHQ(HQVO hqvo){
        return 0;
    }

    @Transactional
    public boolean deleteHQ(long hqid){
        return hqRepository.deleteHQ(hqid) == 1;
    }


    public VO loadByPassport(String passport_no) {
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
}
