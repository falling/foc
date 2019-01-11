package zj.gov.foc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.UserBean;
import zj.gov.foc.repository.UserRepository;
import zj.gov.foc.vo.UserVO;
import zj.gov.foc.vo.VO;

import javax.transaction.Transactional;
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
        userVO.setReg_date(userBean.getReg_date());
        userVO.setRemarks(userBean.getRemarks());
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
        UserBean userBean = userRepository.searchUser(user.getUsername());
        if (userBean != null) {
            return "用户名已经存在";
        }
        int result = userRepository.insert(user.getUsername(), "123456", user.getName(), user.getPower(),user.getManager_area(), new Date(), "", "0");
        return result != 0 ? "创建成功" : "创建失败";
    }

    public VO search(String username, UserVO user) {
        UserBean bean = userRepository.searchUser(username);
        if (bean == null) {
            return null;
        }
        String power = user.getPower();
        String searchPower = bean.getPower();
        if(power.equals("admin") && !searchPower.equals("user")){
            return null;
        }

        UserVO vo = new UserVO();
        BeanUtils.copyProperties(bean,vo);
        vo.setId(bean.getUser_id());
        vo.setUsername(bean.getUser_name());
        return vo;
    }

    @Transactional
    public int update(UserVO user) {
        return userRepository.updateUserInfo(user.getName(), user.getPower(),user.getManager_area(),user.getId());
    }

    @Transactional
    public int delete(Long id) {
        return userRepository.deleteUser(id);
    }

    @Transactional
    public boolean changPwd(String password_old, String password, Long id) {
        return userRepository.getById(id).getPwd().equals(password_old)
                && userRepository.changPwd(id, password) == 1;
    }
}
