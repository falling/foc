package zj.gov.foc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.UserBean;
import zj.gov.foc.repository.UserRepository;
import zj.gov.foc.vo.UserVO;
import zj.gov.foc.vo.VO;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserVO login(String username, String password) {
        UserVO userVO = new UserVO();
        UserBean userBean = userRepository.login(username, password);
        if (userBean == null) {
            userVO.setInfo("用户名或密码错误");
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
     *
     * @return Warning
     */

    public UserVO reg(String username, String pwd, String rePwd, String name, String power, Timestamp reg_date, String remarks) {
        UserVO userVO = new UserVO();
        if ((username == null) || (username.length() > 10) || "".equals(username)) {
            userVO.setInfo("用户账号必须是1-10个字符");
            return userVO;
        } else if ((pwd == null) || (pwd.length() > 20) || "".equals(pwd)) {
            userVO.setInfo("密码长度必须是1-20个字符");
            return userVO;
        } else if (pwd.equals(rePwd)) {
            userVO.setInfo("密码不一致");
            return userVO;
        }
        UserBean userBean = userRepository.searchUser(username);
        if (userBean != null) {
            userVO.setInfo("用户名已存在");
            return userVO;
        }
        userRepository.reg(username, pwd, name, power, reg_date, remarks);
        return userVO;
    }



    public String checkName(String username) {
        UserBean userBean = userRepository.searchUser(username);
        if (userBean != null) {
            return "用户已经存在";
        }
        return null;
    }

    @Transactional
    public String create(UserVO user) {
        UserBean userBean= userRepository.searchUser(user.getUsername());
        if(userBean!=null){
            return "用户名已经存在";
        }
        int result = userRepository.insert(user.getUsername(), "123456", user.getName(), user.getPower(), new Date(), "", "0");
        return result != 0 ? "创建成功":"创建失败";
    }

    public VO search(String username) {
        UserBean bean =  userRepository.searchUser(username);
        if(bean==null){
            return null;
        }
        UserVO vo = new UserVO();
        vo.setId(bean.getUser_id());
        vo.setName(bean.getName());
        vo.setPower(bean.getPower());
        vo.setUsername(bean.getUser_name());
        vo.setRemarks(bean.getRemarks());
        vo.setReg_date(bean.getReg_date());
        return vo;
    }

    @Transactional
    public int update(UserVO user) {
        return userRepository.updateUserInfo(user.getName(),user.getPower(),user.getId());
    }

    @Transactional
    public int delete(Long id) {
        return userRepository.deleteUser(id);
    }
}
