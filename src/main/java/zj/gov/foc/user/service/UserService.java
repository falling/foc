package zj.gov.foc.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.user.po.UserBean;
import zj.gov.foc.user.repository.UserRepository;
import zj.gov.foc.user.vo.UserVO;

import java.sql.Timestamp;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserVO login(String username, String password) {
        UserVO userVO = new UserVO();
        if((username==null)||(username.length()>10)|| "".equals(username)){
            userVO.setWarning("用户账号必须是1-10个字符");
            userVO.setStatus(-1);
            return userVO;
        }

        UserBean userBean = userRepository.login(username,password);
        if(userBean == null){
            userVO.setWarning("用户名或密码错误");
            userVO.setStatus(-1);
            return userVO;
        }
        userVO.setId(userBean.getUser_id());
        userVO.setName(userBean.getName());
        userVO.setUsername(userBean.getUser_name());
        userVO.setPower(userBean.getPower());
        return userVO;
    }
    /**
     * if registration successful
     * @return UserVo==null
     * else
     * @return Warning*/

    public UserVO reg(String username, String pwd, String rePwd, String name, String power, Timestamp reg_date, String remarks){
        UserVO userVO = new UserVO();
        if((username==null)||(username.length()>10)|| "".equals(username)){
            userVO.setWarning("用户账号必须是1-10个字符");
            return userVO;
        }
        else if((pwd==null)||(pwd.length()>20)|| "".equals(pwd)){
            userVO.setWarning("密码长度必须是1-20个字符");
            return userVO;
        }
        else if(pwd.equals(rePwd)){
            userVO.setWarning("密码不一致");
            return userVO;
        }
        UserBean userBean = userRepository.searchUser(username);
        if(userBean != null) {
            userVO.setWarning("用户名已存在");
            return userVO;
        }
        userRepository.reg(username,pwd,name,power,reg_date,remarks);
        return userVO;
    }
    /**
     * if change successful
     * @return UserVo==null
     * else
     * @return Warning*/
    public UserVO changePwd(String username,String oldPwd,String newPwd,String reNewPwd){
        UserVO userVO = new UserVO();
        UserBean userBean = userRepository.searchUser(username);
        if(userBean == null) {
            userVO.setWarning("该用户不存在");
            return userVO;
        }else if(newPwd.equals(reNewPwd)){
            userVO.setWarning("密码不一致");
            return userVO;
        }
        userRepository.changePwd(username,newPwd);
        return userVO;
    }


}
