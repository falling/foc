package zj.gov.foc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    public LxVO addLX(LxVO lxVO) {
        return lxRepository.save(lxVO);
    }
}
