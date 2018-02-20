package zj.gov.foc.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zj.gov.foc.service.LogService;
import zj.gov.foc.vo.LogVO;

import java.util.List;

/**
 * Created by User: falling
 * Date: 2018/2/20
 * Time: 上午11:58
 * Description:
 */
@RestController
public class LogController {

    @Autowired
    LogService logService;

    @RequestMapping("/loadLog")
    public List<LogVO> loadLog(@RequestParam("o_id")Long o_id, @RequestParam("type")String type){
        return logService.loadLog(o_id,type);
    }
}
