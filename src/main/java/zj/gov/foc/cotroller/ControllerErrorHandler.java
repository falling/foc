package zj.gov.foc.cotroller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import zj.gov.foc.util.Response;
import zj.gov.foc.vo.VO;

/**
 * Created by User: falling
 * Date: 2018/1/31
 * Time: 下午7:34
 * Description:
 */
@ControllerAdvice
public class ControllerErrorHandler {

    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public VO errorHandler(Exception ex) {
        ex.printStackTrace();
        return Response.warning("系统异常或数据有误");
    }

}
