package zj.gov.foc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.LxBean;
import zj.gov.foc.repository.LXRepository;
import zj.gov.foc.vo.LxVO;
import zj.gov.foc.vo.VO;

import javax.transaction.Transactional;

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


    @Transactional
    public boolean addLX(LxVO lxVO) {
        if(lxRepository.loadByPassport(lxVO.getPassport_no())!=null){
            return false;
        }
        LxBean bean = new LxBean();
        BeanUtils.copyProperties(lxVO,bean);
        return lxRepository.save(bean) != null;
    }

    public VO loadByPassport(String passport_no) {
        LxBean bean = lxRepository.loadByPassport(passport_no);
        LxVO vo = null;
        if(bean!=null){
            vo = new LxVO();
            BeanUtils.copyProperties(bean,vo);
        }
        return vo;
    }
}
