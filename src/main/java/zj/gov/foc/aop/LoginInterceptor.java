package zj.gov.foc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zj.gov.foc.po.LoginBean;
import zj.gov.foc.service.LogService;
import zj.gov.foc.vo.UserVO;
import zj.gov.foc.vo.VO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by User: falling
 * Date: 2018/1/10
 * Time: 下午8:19
 * Description:
 * 登陆log
 */
@Aspect
@Component
public class LoginInterceptor {
    @Autowired
    LogService logService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession httpSession;

    @Around("execution(* zj.gov.foc.cotroller.UserController.login(..))")
    public VO LoginLog(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        VO result = (VO) point.proceed(args);
        if(result.getStatus()>=0){
            LoginBean loginBean = new LoginBean();
            loginBean.setLogin_date(new Date(System.currentTimeMillis()));
            Long id = ((UserVO)httpSession.getAttribute("user")).getId();
            loginBean.setUser_id(id);
            loginBean.setIp_id(request.getRemoteAddr());
            logService.loginLog(loginBean);
        }
        return result;
    }
}
