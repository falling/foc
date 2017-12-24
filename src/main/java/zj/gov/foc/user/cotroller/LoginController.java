package zj.gov.foc.user.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zj.gov.foc.user.service.UserService;
import zj.gov.foc.user.vo.UserVO;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/login")
    @ResponseBody
    public UserVO login(String username, String password) {
        return userService.login(username,password);
    }
}
