package zj.gov.foc.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User: falling
 * Date: 2019-02-01
 * Time: 22:17
 * Description:
 */
public class ExcelListener<T> extends AnalysisEventListener<T>{

    private List<T> data = new ArrayList<>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        data.add((T) object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }

    public List<T> getData() {
        return data;
    }

    public void clearData(){
        data.clear();
    }
}
