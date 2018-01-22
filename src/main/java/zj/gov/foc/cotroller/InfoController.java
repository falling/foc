package zj.gov.foc.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zj.gov.foc.service.HQService;
import zj.gov.foc.service.LXService;
import zj.gov.foc.util.Response;
import zj.gov.foc.vo.HQVO;
import zj.gov.foc.vo.LxVO;
import zj.gov.foc.vo.UserVO;
import zj.gov.foc.vo.VO;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by User: falling
 * Date: 2018/1/20
 * Time: 下午2:34
 * Description:
 */
@RestController
public class InfoController {

    @Autowired
    HQService hqService ;

    @Autowired
    LXService lxService ;

    @Value("${upload-path}")
    private String path;


    @RequestMapping("/addHQInfo")
    public VO addHQInfo(@RequestBody HQVO hqvo,HttpSession httpSession) {
        UserVO userVO = (UserVO) httpSession.getAttribute("user");
        if(userVO == null){
            return Response.warning("未登录");
        }
        hqvo.setRegistrant(userVO.getId());
        hqvo.setReg_date(new Date());
        hqvo.setDel("0");
        HQVO hqvo1 = hqService.addHQ(hqvo);
        if(hqvo1.getInfo().equals("录入成功")){
            return Response.success(hqvo1.getInfo());
        }else{
            return Response.warning(hqvo1.getInfo());
        }
    }

    @RequestMapping("/addLXInfo")
    public VO addLXInfo(@RequestBody LxVO lxVO, HttpSession httpSession) {
        UserVO userVO = (UserVO) httpSession.getAttribute("user");
        if(userVO == null){
            return Response.warning("未登录");
        }
        lxVO.setRegistrant(userVO.getId());
        lxVO.setReg_date(new Date());
        lxVO.setDel("0");
        LxVO lxVO1 = lxService.addLX(lxVO);
        if(lxVO1 == null){
            return Response.success("录入失败");
        }else{
            return Response.warning("录入成功");
        }
    }


    @RequestMapping("/fileUpload")
    public VO fileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                File targetFile = new File(path);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                String newFileName =new Date().getTime()+"_"+file.getOriginalFilename();
                Files.copy(file.getInputStream(), Paths.get(path, newFileName));
                return Response.success("/"+newFileName);
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
                return Response.warning(e.getMessage());
            }
        } else {
            return Response.warning("文件为空");
        }
    }


}
