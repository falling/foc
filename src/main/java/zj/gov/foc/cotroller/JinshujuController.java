package zj.gov.foc.cotroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zj.gov.foc.po.FormBean;
import zj.gov.foc.service.FormService;

/**
 * Created by User: falling
 * Date: 2019-02-16
 * Time: 19:33
 * Description:
 */
@RestController
public class JinshujuController {
    @Autowired
    FormService formService;

    @RequestMapping("/importFromJson")
    public String saveJson(@RequestBody FormBean bean) throws JsonProcessingException {
        return formService.save(bean);
    }
}
