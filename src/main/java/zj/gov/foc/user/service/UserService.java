package zj.gov.foc.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.user.dao.UserRepository;
import zj.gov.foc.user.po.UserBean;
import zj.gov.foc.user.vo.UserVO;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserVO login(String username, String password) {
        UserBean userBean = userRepository.login(username,password);
        UserVO userVO = new UserVO();
        if(userBean == null){
            return userVO;
        }
        userVO.setId(userBean.getId());
        userVO.setName(userBean.getName());
        userVO.setUsername(userBean.getUsername());
        userVO.setPower(userBean.getPower());
        return userVO;
    }

}
