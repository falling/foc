package zj.gov.foc.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zj.gov.foc.service.UserService;
import zj.gov.foc.vo.UserVO;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/userLogin")
    public UserVO login(@RequestParam("username")String username, @RequestParam("password")String password,HttpSession httpSession) {
        UserVO userVO = userService.login(username,password);
        httpSession.setAttribute("user",userVO);
        return userVO;
    }

    public UserVO reg(String username, String password, String rePwd, String name, String power , String remarks){
        Timestamp regData = new Timestamp(System.currentTimeMillis());
        return userService.reg(username, password, rePwd , name, power ,regData,remarks);
    }
    public UserVO changePwd(String username,String oldPwd ,String newPwd,String reNewPwd){
        return userService.changePwd(username,oldPwd,newPwd,reNewPwd);
    }
}
