package zj.gov.foc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.LogBean;
import zj.gov.foc.po.LoginBean;
import zj.gov.foc.repository.LogRepository;
import zj.gov.foc.repository.LoginLogRepository;
import zj.gov.foc.repository.UserRepository;
import zj.gov.foc.vo.LogVO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
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

    @PersistenceContext
    private EntityManager entityManager;

    public List<LogVO> loadLog(Long o_id, String type) {
        String sql = "SELECT user.user_name as operating_user,log.* FROM log,user WHERE log.o_id = '"+o_id+"' AND log.identity = '"+type+"' and log.operating_user = user.user_id ORDER BY log.log_date DESC";
        Query query = entityManager.createNativeQuery(sql,LogVO.class);
        return query.getResultList();
    }

    @Transactional
    public void saveList(List<LogBean> logList) {
        logRepository.save(logList);
    }
}
