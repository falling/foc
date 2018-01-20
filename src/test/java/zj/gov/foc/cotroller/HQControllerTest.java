package zj.gov.foc.cotroller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import zj.gov.foc.repository.HQRepository;
import zj.gov.foc.vo.HQVO;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"zj.gov.foc"})
public class HQControllerTest {

    @Resource
    HQController hqController ;

    HQVO hqvo = new HQVO();


    @Test
    public void createUser() {
        hqvo.setChName("yu");
        hqvo.setUsedName("yu");
        hqvo.setPyName("yu");
        hqvo.setSex("女");
        hqvo.setEthnicity("1");
        hqvo.setPassportNo("1");
        hqController.createHQ(hqvo);
    }

    @Test
    public void modifyHQ() {
        hqvo.setHqId(3);
        hqvo.setChName("yu");
        hqvo.setUsedName("yu");
        hqvo.setPyName("yu");
        hqvo.setSex("女");
        hqvo.setEthnicity("1");
        hqvo.setPassportNo("1");

        hqController.modifyHQ(hqvo);


    }

    @Test
    public void deleteHQ() {
        hqvo.setHqId(1);
        hqController.deleteHQ(hqvo);
    }
}