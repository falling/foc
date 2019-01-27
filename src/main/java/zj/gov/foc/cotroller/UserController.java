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

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/userInfo")
    public VO getInfo(HttpSession httpSession) {
        UserVO vo = (UserVO) httpSession.getAttribute("user");
        if (vo == null) {
            return new UserVO();
        }
        return (UserVO) httpSession.getAttribute("user");
    }

    @RequestMapping("/checkName")
    public VO checkName(@RequestParam("username") String username) {
        String result = userService.checkName(username);
        return Response.info(result);
    }

    @RequestMapping("/createUser")
    public VO createUser(@RequestBody UserVO user) {
        String result = userService.create(user);
        if (result.equals("创建成功")) {
            return Response.success(result);
        } else {
            return Response.warning(result);
        }
    }

    @RequestMapping("/searchUser")
    public VO searchUser(@RequestParam("username") String username,HttpSession httpSession) {
        UserVO user = (UserVO) httpSession.getAttribute("user");
        VO vo = userService.search(username,user);
        if (vo == null) {
            return Response.warning("该用户不存在或者您无权限查看修改");
        } else {
            return Response.success(vo);
        }
    }


    @RequestMapping("/updateUser")
    public VO updateUser(@RequestBody UserVO user, HttpSession httpSession) {
        UserVO userVO = (UserVO) httpSession.getAttribute("user");
        user.setId(userVO.getId());
        if (userService.update(user) == 1) {
            httpSession.setAttribute("user", user);
            return Response.success("修改成功");
        }
        return Response.warning("修改失败");
    }

    @RequestMapping("/updateUserManager")
    public VO updateUserManager(@RequestBody UserVO user) {
        if (userService.update(user) == 1) {
            return Response.success("修改成功");
        }
        return Response.warning("修改失败");
    }

    @RequestMapping("/deleteUser")
    public VO deleteUser(@RequestParam("id") Long id) {
        if (userService.delete(id) == 1) {
            return Response.success("删除成功");
        }
        return Response.warning("删除失败");
    }

    @RequestMapping("/sighOff")
    public VO sighOff(HttpSession httpSession) {
        httpSession.setAttribute("user", null);
        return Response.success("注销成功");
    }

    @RequestMapping("/changePwd")
    public VO changPwd(@RequestParam("password_old") String password_old, @RequestParam("password") String password, HttpSession session) {
        UserVO userVO = (UserVO) session.getAttribute("user");
        if (userService.changPwd(password_old, password, userVO.getId())) {
            return Response.success("修改成功");
        }
        return Response.warning("修改失败,密码错误");
    }

}
