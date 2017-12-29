package zj.gov.foc.user.cotroller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"zj.gov.foc"})

public class LoginControllerTest {

    @Test
    public void reg() {
    }

    @Test
    public void changePwd() {
    }

    @Resource
    LoginController loginController;

    @Test
    public void testreg(){
        loginController.reg("yu","test","test","yu","管理员","..");
        loginController.reg("","test","test","yu","管理员","..");
        loginController.reg("yuuuuuuuuuuuuu","test","test","yu","管理员","..");
    }
    @Test
    public void testchangePwd(){
        loginController.changePwd("yu","123","321","321");
    }


}