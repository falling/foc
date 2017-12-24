package zj.gov.foc.login.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/my")
public class LoginController {
    @ResponseBody
    public String login(){
        return "hello world";
    }
}
