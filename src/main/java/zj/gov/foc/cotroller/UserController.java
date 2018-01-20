package zj.gov.foc.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zj.gov.foc.service.UserService;
import zj.gov.foc.util.Response;
import zj.gov.foc.vo.UserVO;
import zj.gov.foc.vo.VO;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/userLogin")
    public VO login(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession httpSession) {
        UserVO userVO = userService.login(username,password);
        if(userVO.getStatus()<0)
            return userVO;
        httpSession.setAttribute("user",userVO);
        return Response.success();
    }

    @RequestMapping("/userInfo")
    public VO getInfo(HttpSession httpSession){
        UserVO vo = (UserVO) httpSession.getAttribute("user");
        if(vo==null){
            return new UserVO();
        }
        return (UserVO) httpSession.getAttribute("user");
    }

    @RequestMapping("/checkName")
    public VO checkName(@RequestParam("username")String username){
        String result = userService.checkName(username);
        return Response.info(result);
    }

    @RequestMapping("/createUser")
    public VO createUser(@RequestBody UserVO user){
        String result = userService.create(user);
        if(result.equals("创建成功")){
           return Response.success(result);
        }else{
           return Response.warning(result);
        }
    }

    @RequestMapping("/searchUser")
    public VO searchUser(@RequestParam("username")String username){
        VO vo = userService.search(username);
        if(vo == null){
            return Response.warning("该用户不存在");
        }else {
            return Response.success(vo);
        }
    }


    @RequestMapping("/updateUser")
    public VO updateUser(@RequestBody UserVO user){
        if(userService.update(user) == 1){
            return Response.success("修改成功");
        }
        return Response.success("修改失败");
    }

    @RequestMapping("/deleteUser")
    public VO deleteUser(@RequestParam("id") Long id){
        if(userService.delete(id) == 1){
            return Response.success("删除成功");
        }
        return Response.success("删除失败");
    }

}
