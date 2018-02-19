package zj.gov.foc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.LoginBean;
import zj.gov.foc.repository.LoginLogRepository;

import javax.transaction.Transactional;

/**
 * Created by User: falling
 * Date: 2018/2/19
 * Time: 下午2:54
 * Description:
 */
@Service
public class LogService {
    @Autowired
    LoginLogRepository loginLogRepository;

    @Transactional
    public void loginLog(LoginBean bean){
        loginLogRepository.save(bean);
    }
}
