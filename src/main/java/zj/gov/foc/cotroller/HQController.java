package zj.gov.foc.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import zj.gov.foc.service.UserService;


@RestController
public class HQController {
    @Autowired
    UserService userService;


}
