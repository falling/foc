package zj.gov.foc.vo;

/**
 * Created by User: falling
 * Date: 2018/1/17
 * Time: 下午9:07
 * Description:
 */
public class BaseVO implements VO{
    protected int status;
    protected String info;

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String info) {
        this.info = info;
    }
}
