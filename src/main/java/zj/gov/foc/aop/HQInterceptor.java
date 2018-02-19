package zj.gov.foc.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zj.gov.foc.po.HQBean;
import zj.gov.foc.po.LogBean;
import zj.gov.foc.repository.HQRepository;
import zj.gov.foc.service.LogService;
import zj.gov.foc.vo.HQVO;
import zj.gov.foc.vo.HQVOwithRelation;
import zj.gov.foc.vo.UserVO;

import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * Created by User: falling
 * Date: 2018/2/19
 * Time: 下午5:22
 * Description:
 */
@Aspect
@Component
public class HQInterceptor {

    @Autowired
    HttpSession session;

    @Autowired
    LogService logService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    HQRepository hqRepository;

    @Around("execution(* zj.gov.foc.service.HQService.addHQ(..))")
    public HQBean Log_add(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        HQBean result = (HQBean) point.proceed(args);
        if (result != null) {
            logService.log(generateLogBean("hq", "添加", result.getHq_id(),
                    "", result));
        }

        return result;
    }

    @Around("execution(* zj.gov.foc.service.HQService.update(..))")
    public HQBean Log_update(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        HQVO vo = ((HQVOwithRelation) args[0]).getValue();
        String oldValue = objectMapper.writeValueAsString(hqRepository.getById(vo.getHq_id()));
        HQBean result = (HQBean) point.proceed(args);
        if (result != null) {
            logService.log(generateLogBean("hq", "修改", result.getHq_id(),
                    oldValue, result));
        }

        return result;
    }

    @Around("execution(* zj.gov.foc.service.HQService.deleteHQ(..))")
    public boolean Log_delete(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        long deleteId = (long) args[0];
        boolean result = (boolean) point.proceed(args);
        if (result) {
            logService.log(generateLogBean("hq", "删除", deleteId, "", ""));
        }
        return result;
    }

    private LogBean generateLogBean(String tableName,
                                    String operation,
                                    Long tableId,
                                    String oldValue,
                                    Object newValue) throws JsonProcessingException {
        LogBean logBean = new LogBean();

        logBean.setLog_date(new Date(System.currentTimeMillis()));
        logBean.setIdentity(tableName);
        logBean.setOperating(operation);
        logBean.setO_id(tableId);
        logBean.setOperating_user(((UserVO) session.getAttribute("user")).getId());
        logBean.setOld_value(oldValue);
        logBean.setNew_value(objectMapper.writeValueAsString(newValue));
        return logBean;
    }
}
