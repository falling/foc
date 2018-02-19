package zj.gov.foc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import zj.gov.foc.po.LoginBean;
import zj.gov.foc.service.LogService;
import zj.gov.foc.vo.UserVO;
import zj.gov.foc.vo.VO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

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

    @Around("execution(* zj.gov.foc.cotroller.UserController.login(..))")
    public VO LoginLog(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        VO result = (VO) point.proceed(args);
        if(result.getStatus()>=0){
            System.out.println("登陆成功！");
            LoginBean loginBean = new LoginBean();
            loginBean.setLogin_date(new Date(System.currentTimeMillis()));
            Long id = ((UserVO)((HttpSession)args[2]).getAttribute("user")).getId();
            loginBean.setUser_id(id);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            loginBean.setIp_id(request.getRemoteAddr());
            logService.loginLog(loginBean);
        }
        return result;
    }
}
