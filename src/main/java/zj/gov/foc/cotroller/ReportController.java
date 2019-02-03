package zj.gov.foc.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zj.gov.foc.service.StatisticsService;
import zj.gov.foc.vo.UserVO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User: falling
 * Date: 2018/6/17
 * Time: 下午2:22
 * Description:
 * 报表页面的后台API
 */
@RestController
@RequestMapping("/api")
public class ReportController {

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    HttpSession session;

    @RequestMapping("/total")
    public HashMap<String, Long> getTotal() {
        return statisticsService.total();
    }

    @RequestMapping("/sex")
    public HashMap<String, Long> getSex() {
        return statisticsService.sex();
    }

    @RequestMapping("/HQCountry")
    public HashMap<Object, Long> getHQCountry() {
        return statisticsService.HQCountry();
    }

    @RequestMapping("/LXCountry")
    public HashMap<Object, Long> getLXCountry() {
        return statisticsService.LXCountry();
    }

    @RequestMapping("/QJHQCountry")
    public HashMap<Object, Long> getQJHQCountry() {
        return statisticsService.QJHQCountry();
    }

    @RequestMapping("/QJLXCountry")
    public HashMap<Object, Long> getQJLXCountry() {
        return statisticsService.QJLXCountry();
    }

    @RequestMapping("/allCountry")
    public HashMap<Object, Long> getAllCountry() {
        HashMap<Object, Long> result = new HashMap<>();
        statisticsService.HQCountry().forEach((k,v)-> result.merge(k,v,(x, y)->x+y));
        statisticsService.LXCountry().forEach((k,v)-> result.merge(k,v,(x, y)->x+y));
        statisticsService.QJHQCountry().forEach((k,v)-> result.merge(k,v,(x, y)->x+y));
        statisticsService.QJLXCountry().forEach((k,v)-> result.merge(k,v,(x, y)->x+y));
        return result;
    }

    @RequestMapping("/HQAreaData")
    public HashMap<Object, Long> getHQAreaMap(@RequestParam("area") String area) {
        return statisticsService.getHQAreaCount(area);
    }
    @RequestMapping("/LXAreaData")
    public HashMap<Object, Long> getLXAreaMap(@RequestParam("area") String area) {
        return statisticsService.getLXAreaCount(area);
    }
    @RequestMapping("/QJLXAreaData")
    public HashMap<Object, Long> getQJLXAreaMap(@RequestParam("area") String area) {
        return statisticsService.getQJLXAreaCount(area);
    }
    @RequestMapping("/QJHQAreaData")
    public HashMap<Object, Long> getQJHQAreaMap( @RequestParam("area") String area) {
        return statisticsService.getQJHQAreaCount(area);
    }

    @RequestMapping("/allAreaData")
    public HashMap<Object, Long> getAreaMap(@RequestParam("area") String area) {
        HashMap<Object, Long> result = new HashMap<>();
        statisticsService.getHQAreaCount(area).forEach((k,v)-> result.merge(k,v,(x, y)->x+y));
        statisticsService.getLXAreaCount(area).forEach((k,v)-> result.merge(k,v,(x, y)->x+y));
        statisticsService.getQJHQAreaCount(area).forEach((k,v)-> result.merge(k,v,(x, y)->x+y));
        statisticsService.getQJLXAreaCount(area).forEach((k,v)-> result.merge(k,v,(x, y)->x+y));
        return result;
    }


    @RequestMapping("/NativePlace")
    public HashMap<Object, Object> getNativePlace() {
        return statisticsService.NativePlace();
    }

    private String getSecondManager_area() {
        UserVO userVO = (UserVO) session.getAttribute("user");
        List<String> list = new ArrayList<>();
        Collections.addAll(list, userVO.getManager_area().split("/"));
        if (list.size() == 3) {
            list.remove(2);
        }
        return String.join("/", list) + "%";
    }

}
