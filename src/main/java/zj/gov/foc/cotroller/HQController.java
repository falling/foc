package zj.gov.foc.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zj.gov.foc.service.HQService;
import zj.gov.foc.util.Response;
import zj.gov.foc.vo.HQVO;
import zj.gov.foc.vo.VO;


@RestController
public class HQController {
    @Autowired
    HQService hqService ;

    @RequestMapping("/insertHQ")
    public VO createHQ(@RequestBody HQVO hqvo){

        HQVO hqvo1 = hqService.addHQ(hqvo);
        if(hqvo1.getInfo().equals("创建成功")){
            return Response.success(hqvo1.getInfo());
        }else{
            return Response.warning(hqvo1.getInfo());
        }
    }

    @RequestMapping("/modifyHQ")
    public VO modifyHQ(@RequestBody HQVO hqvo){
        int rs= hqService.modifyHQ(hqvo);
        if(rs == 1){
            return Response.success("修改成功");
        }else {
            return Response.success("修改失败");
        }

    }
    @RequestMapping("/deleteHQ")
    public  VO deleteHQ(@RequestBody HQVO hqvo){
        int rs = hqService.deleteHQ(hqvo.getHqId());
        if(rs == 1){
            return Response.success("删除成功");
        }else{
            return Response.success("删除失败");
        }
    }

}
