package zj.gov.foc.user.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zj.gov.foc.user.service.UserService;
import zj.gov.foc.user.vo.UserVO;

import java.sql.Timestamp;

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

    public UserVO reg(String username, String password, String rePwd, String name, String power , String remarks){
        Timestamp regData = new Timestamp(System.currentTimeMillis());
        return userService.reg(username, password, rePwd , name, power ,regData,remarks);
    }
    public UserVO changePwd(String username,String oldPwd ,String newPwd,String reNewPwd){
        return userService.changePwd(username,oldPwd,newPwd,reNewPwd);
    }
}
