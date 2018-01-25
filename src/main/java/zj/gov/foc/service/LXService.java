package zj.gov.foc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.LxBean;
import zj.gov.foc.repository.LXRepository;
import zj.gov.foc.repository.UserRepository;
import zj.gov.foc.vo.LxVO;
import zj.gov.foc.vo.VO;

import javax.transaction.Transactional;
import java.sql.Date;

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

    @Transactional
    public boolean addLX(LxVO lxVO,Long id) {
        if(lxRepository.loadByPassport(lxVO.getPassport_no())!=null){
            return false;
        }
        LxBean bean = new LxBean();
        bean.setReg_date(new Date(System.currentTimeMillis()));
        bean.setDel("0");
        bean.setRemarks("");
        bean.setRegistrant(id);
        BeanUtils.copyProperties(lxVO,bean);
        return lxRepository.save(bean) != null;
    }

    public VO loadByPassport(String passport_no) {
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

    @Transactional
    public LxBean update(LxVO vo) {
        LxBean bean = lxRepository.getById(vo.getLx_id());
        BeanUtils.copyProperties(vo,bean);
        return lxRepository.save(bean);
    }

    @Transactional
    public boolean delete(Long id) {
        return lxRepository.delete(id) ==1;
    }
}
