package zj.gov.foc.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class ReportController {

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    HttpSession session;

    @RequestMapping("/total")
    public HashMap<String, Long> getTotal() {
        return statisticsService.total(getSecondManager_area());
    }

    @RequestMapping("/sex")
    public HashMap<String, Long> getSex() {
        return statisticsService.sex(getSecondManager_area());
    }

    @RequestMapping("/HQCountry")
    public HashMap<Object, Object> getHQCountry() {
        return statisticsService.HQCountry(getSecondManager_area());
    }

    @RequestMapping("/LXCountry")
    public HashMap<Object, Object> getLXCountry() {
        return statisticsService.LXCountry(getSecondManager_area());
    }

    @RequestMapping("/QJHQCountry")
    public HashMap<Object, Object> getQJHQCountry() {
        return statisticsService.QJHQCountry(getSecondManager_area());
    }

    @RequestMapping("/QJLXCountry")
    public HashMap<Object, Object> getQJLXCountry() {
        return statisticsService.QJLXCountry(getSecondManager_area());
    }

    @RequestMapping("/NativePlace")
    public HashMap<Object, Object> getNativePlace() {
        return statisticsService.NativePlace(getSecondManager_area());
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
