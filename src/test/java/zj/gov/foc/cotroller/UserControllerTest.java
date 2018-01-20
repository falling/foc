package zj.gov.foc.cotroller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"zj.gov.foc"})

public class UserControllerTest {

    @Test
    public void reg() {
    }

    @Test
    public void changePwd() {
    }

    @Resource
    UserController UserController ;

    /* @Test
   public void testreg(){
        UserController.reg("yu","test","test","yu","管理员","..");
        UserController.reg("","test","test","yu","管理员","..");
        UserController.reg("yuuuuuuuuuuuuu","test","test","yu","管理员","..");
    }
    @Test
    public void testchangePwd(){
        UserController.changePwd("yu","123","321","321");
    }*/


}