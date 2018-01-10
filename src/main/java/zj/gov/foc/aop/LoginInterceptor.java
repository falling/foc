package zj.gov.foc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import zj.gov.foc.vo.UserVO;

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
    @Around("execution(* zj.gov.foc.cotroller.UserController.login(..))")
    public UserVO LoginLog(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        UserVO result = (UserVO) point.proceed(args);
        if(result.getStatus()>=0){
            System.out.println("登陆成功！");
            //log 登陆时间
            System.out.println(args[0].toString()+args[1].toString());
        }
        return result;
    }
}
