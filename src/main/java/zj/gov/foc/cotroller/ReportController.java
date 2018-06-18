package zj.gov.foc.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zj.gov.foc.service.StatisticsService;

import java.util.HashMap;

/**
 * Created by User: falling
 * Date: 2018/6/17
 * Time: 下午2:22
 * Description:
 */
@RestController
public class ReportController {

    @Autowired
    StatisticsService statisticsService;

    @RequestMapping("/total")
    public HashMap<String, Long> getTotal() {
        return statisticsService.total();
    }

    @RequestMapping("/sex")
    public HashMap<String, Long> getSex() {
        return statisticsService.sex();
    }

    @RequestMapping("/HQCountry")
    public HashMap<Object, Object> getHQCountry() {
        return statisticsService.HQCountry();
    }

    @RequestMapping("/LXCountry")
    public HashMap<Object, Object> getLXCountry() {
        return statisticsService.LXCountry();
    }

    @RequestMapping("/QJHQCountry")
    public HashMap<Object, Object> getQJHQCountry() {
        return statisticsService.QJHQCountry();
    }

    @RequestMapping("/QJLXCountry")
    public HashMap<Object, Object> getQJLXCountry() {
        return statisticsService.QJLXCountry();
    }

    @RequestMapping("/NativePlace")
    public HashMap<Object, Object> getNativePlace() {
        return statisticsService.NativePlace();
    }

}
