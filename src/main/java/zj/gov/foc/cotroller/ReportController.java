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

    @RequestMapping("/allCountry")
    public HashMap<Object, Object> getAllCountry() {
        HashMap<Object, Object> result = new HashMap<>();
        result.putAll(statisticsService.HQCountry());
        result.putAll(statisticsService.LXCountry());
        result.putAll(statisticsService.QJHQCountry());
        result.putAll(statisticsService.QJLXCountry());
        return result;
    }

    @RequestMapping("/HQAreaData")
    public HashMap<Object, Object> getHQAreaMap(@RequestParam("area") String area) {
        return statisticsService.getHQAreaCount(area);
    }
    @RequestMapping("/LXAreaData")
    public HashMap<Object, Object> getLXAreaMap(@RequestParam("area") String area) {
        return statisticsService.getLXAreaCount(area);
    }
    @RequestMapping("/QJLXAreaData")
    public HashMap<Object, Object> getQJLXAreaMap(@RequestParam("area") String area) {
        return statisticsService.getQJLXAreaCount(area);
    }
    @RequestMapping("/QJHQAreaData")
    public HashMap<Object, Object> getQJHQAreaMap( @RequestParam("area") String area) {
        return statisticsService.getQJHQAreaCount(area);
    }

    @RequestMapping("/allAreaData")
    public HashMap<Object, Object> getAreaMap(@RequestParam("area") String area) {
        HashMap<Object, Object> result = new HashMap<>();
        result.putAll(statisticsService.getHQAreaCount(area));
        result.putAll(statisticsService.getLXAreaCount(area));
        result.putAll(statisticsService.getQJHQAreaCount(area));
        result.putAll(statisticsService.getQJLXAreaCount(area));
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
