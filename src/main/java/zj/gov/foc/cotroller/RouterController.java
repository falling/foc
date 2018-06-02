package zj.gov.foc.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by User: falling
 * Date: 2018/1/6
 * Time: 下午7:56
 * Description: router
 */
@Controller
public class RouterController {
    @RequestMapping({"/","/manager","/manager/*","/manager/*/*"})
    public String index() {
        return "index";
    }
}
