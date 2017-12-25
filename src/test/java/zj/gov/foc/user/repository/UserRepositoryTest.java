package zj.gov.foc.user.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by User: falling
 * Date: 2017/12/25
 * Time: 下午10:43
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"zj.gov.foc"})

public class UserRepositoryTest {
    @Test
    public void login() throws Exception {
    }

    @Resource
    UserRepository userRepository;

    @Test
    public void test(){
        userRepository.login("falling","test");
    }
}
