package zj.gov.foc.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zj.gov.foc.po.HQBean;
import zj.gov.foc.po.LogBean;
import zj.gov.foc.po.LxBean;
import zj.gov.foc.po.QJBean;
import zj.gov.foc.repository.HQRepository;
import zj.gov.foc.repository.LXRepository;
import zj.gov.foc.repository.QJRepository;
import zj.gov.foc.service.HQService;
import zj.gov.foc.service.LXService;
import zj.gov.foc.service.LogService;
import zj.gov.foc.service.QJService;
import zj.gov.foc.vo.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by User: falling
 * Date: 2018/2/19
 * Time: 下午5:22
 * Description:
 */
@Aspect
@Component
public class LogInterceptor {

    @Autowired
    HttpSession session;

    @Autowired
    LogService logService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    HQRepository hqRepository;

    @Autowired
    LXRepository lxRepository;

    @Autowired
    QJRepository qjRepository;

    @Autowired
    HQService hqService;

    @Autowired
    LXService lxService;

    @Autowired
    QJService qjService;



    @Around("execution(* zj.gov.foc.service.HQService.addHQ(..))")
    public HQBean HQLog_add(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        HQBean result = (HQBean) point.proceed(args);
        if (result != null) {
            String newValue = objectMapper.writeValueAsString(result);
            logService.log(generateLogBean("hq", "添加", result.getHq_id(),
                    "", newValue));
        }

        return result;
    }

    @Around("execution(* zj.gov.foc.service.LXService.addLX(..))")
    public LxBean LXLog_add(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        LxBean result = (LxBean) point.proceed(args);
        if (result != null) {
            String newValue = objectMapper.writeValueAsString(result);
            logService.log(generateLogBean("lx", "添加", result.getLx_id(),
                    "", newValue));
        }

        return result;
    }

    @Around("execution(* zj.gov.foc.service.QJService.saveQj(..))")
    public QJBean QJLog_add(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        QJBean result = (QJBean) point.proceed(args);
        if (result != null) {
            String newValue = objectMapper.writeValueAsString(result);
            logService.log(generateLogBean("qj", "添加", result.getQj_id(),
                    "", newValue));
        }
        return result;
    }

    @Around("execution(* zj.gov.foc.service.HQService.update(..))")
    public HQBean HQLog_update(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        HQVO vo = (HQVO) args[0];
        String oldValue = objectMapper.writeValueAsString(hqRepository.getById(vo.getHq_id()));
        HQBean result = (HQBean) point.proceed(args);
        if (result != null) {
            String newValue = objectMapper.writeValueAsString(result);
            logService.log(generateLogBean("hq", "修改", result.getHq_id(),
                    oldValue, newValue));
        }
        return result;
    }


    @Around("execution(* zj.gov.foc.service.LXService.update(..))")
    public LxBean LXLog_update(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        LxVO vo = (LxVO) args[0];
        String oldValue = objectMapper.writeValueAsString(lxRepository.getById(vo.getLx_id()));
        LxBean result = (LxBean) point.proceed(args);
        if (result != null) {
            String newValue = objectMapper.writeValueAsString(result);
            logService.log(generateLogBean("lx", "修改", result.getLx_id(),
                    oldValue, newValue));
        }

        return result;
    }

    @Around("execution(* zj.gov.foc.service.QJService.update(..))")
    public QJBean QJLog_update(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        QjVO vo = (QjVO) args[0];
        String oldValue = objectMapper.writeValueAsString(qjRepository.getById(vo.getQj_id()));
        QJBean result = (QJBean) point.proceed(args);
        if (result != null) {
            String newValue = objectMapper.writeValueAsString(result);
            logService.log(generateLogBean("qj", "修改", result.getQj_id(),
                    oldValue, newValue));
        }

        return result;
    }

    @Around("execution(* zj.gov.foc.service.HQService.deleteHQ(..)) " +
            "|| execution(* zj.gov.foc.service.LXService.deleteLX(..)) " +
            "|| execution(* zj.gov.foc.service.QJService.deleteQJ(..)) ")
    public boolean Log_delete(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        long deleteId = (long) args[0];
        boolean result = (boolean) point.proceed(args);
        String functionName = point.getSignature().getName();
        String tableName = functionName.substring(functionName.length()-2).toLowerCase();
        if (result) {
            logService.log(generateLogBean(tableName,"删除", deleteId, "", ""));
        }
        return result;
    }

    private LogBean generateLogBean(String tableName,
                                    String operation,
                                    Long tableId,
                                    String oldValue,
                                    String newValue) {
        LogBean logBean = new LogBean();

        logBean.setLog_date(new Date(System.currentTimeMillis()));
        logBean.setIdentity(tableName);
        logBean.setOperating(operation);
        logBean.setO_id(tableId);
        logBean.setOperating_user(((UserVO) session.getAttribute("user")).getId());
        logBean.setOld_value(oldValue);
        logBean.setNew_value(newValue);
        return logBean;
    }
}
