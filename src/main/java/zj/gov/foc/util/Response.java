package zj.gov.foc.util;

import zj.gov.foc.vo.BaseVO;
import zj.gov.foc.vo.VO;

/**
 * Created by User: falling
 * Date: 2018/1/17
 * Time: 下午9:09
 * Description:
 */
public class Response {
    public static VO info(String result) {
        if(result==null){
            return success();
        }else{
            return warning(result);
        }
    }

    public static VO success() {
        return success("success");
    }

    public static VO success(String message) {
        return ACK(new BaseVO(), message);
    }

    public static VO warning() {
        return warning("failed");
    }

    public static VO warning(String message) {
        return NAK(new BaseVO(), message);
    }


    public static <T extends VO> T ACK(T t) {
        return ACK(t,"success");
    }

    public static <T extends VO> T NAK(T t) {
        return NAK(t,"failed");
    }


    public static <T extends VO> T ACK(T t, String message) {
        t.setStatus(1);
        t.setInfo(message);
        return t;
    }

    public static <T extends VO> T NAK(T t, String message) {
        t.setStatus(-1);
        t.setInfo(message);
        return t;
    }

}
