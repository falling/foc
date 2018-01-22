package zj.gov.foc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.LxBean;
import zj.gov.foc.repository.LXRepository;
import zj.gov.foc.vo.LxVO;

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


    public boolean addLX(LxVO lxVO) {
        LxBean bean = new LxBean();
        BeanUtils.copyProperties(lxVO,bean);
        return lxRepository.save(bean) != null;
    }
}
