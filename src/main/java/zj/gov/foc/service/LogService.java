package zj.gov.foc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.LogBean;
import zj.gov.foc.po.LoginBean;
import zj.gov.foc.repository.LogRepository;
import zj.gov.foc.repository.LoginLogRepository;
import zj.gov.foc.repository.UserRepository;
import zj.gov.foc.vo.LogVO;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    LogRepository logRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void loginLog(LoginBean bean){
        loginLogRepository.save(bean);
    }

    @Transactional
    public void log(LogBean logBean){
        logRepository.save(logBean);
    }

    public List<LogVO> loadLog(Long o_id, String type) {
        List<LogVO> result = new ArrayList<>();
        List<LogBean> list = logRepository.getLogByO_idAndType(o_id,type);
        list.forEach(logBean -> {
            LogVO logVO = new LogVO();
            BeanUtils.copyProperties(logBean,logVO);
            String operator = userRepository.getById(logBean.getOperating_user()).getName();
            logVO.setOperating_user(operator);
            result.add(logVO);
        });
        return result;
    }

    @Transactional
    public void saveList(List<LogBean> logList) {
        logRepository.save(logList);
    }
}
