package zj.gov.foc.login.cotroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @RequestMapping("/greeting")
    public String login(){
        return "hello world";
    }
}
