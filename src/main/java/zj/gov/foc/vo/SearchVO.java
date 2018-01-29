package zj.gov.foc.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User: falling
 * Date: 2018/1/29
 * Time: 下午7:43
 * Description:
 */
public class SearchVO<T> extends BaseVO{
    private List<T> result = new ArrayList<>();

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
