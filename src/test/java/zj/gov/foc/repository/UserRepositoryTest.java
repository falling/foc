package zj.gov.foc.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import zj.gov.foc.po.UserBean;

import java.util.List;

/**
 * Created by User: falling
 * Date: 2018/1/19
 * Time: 下午10:21
 * Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"zj.gov.foc"})
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void seachByusername() {
        List<UserBean> users = userRepository.seachByusername("%"+"f"+"%");
        for (UserBean user : users) {
            System.out.println(user.getUser_name());
        }
    }
}