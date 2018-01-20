package zj.gov.foc.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zj.gov.foc.service.HQService;
import zj.gov.foc.util.Response;
import zj.gov.foc.vo.HQVO;
import zj.gov.foc.vo.UserVO;
import zj.gov.foc.vo.VO;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/addHQInfo")
    public VO addHQInfo(@RequestBody HQVO hqvo,HttpSession httpSession) {
        UserVO userVO = (UserVO) httpSession.getAttribute("user");
        hqvo.setRegistrant(userVO.getId());
        HQVO hqvo1 = hqService.addHQ(hqvo);
        if(hqvo1.getInfo().equals("创建成功")){
            return Response.success(hqvo1.getInfo());
        }else{
            return Response.warning(hqvo1.getInfo());
        }
    }

    @Value("${upload-path}")
    private String path;

    @RequestMapping("/fileUpload")
    public VO fileUpload(@RequestParam("file") MultipartFile file ,HttpServletRequest request) {
        if (!file.isEmpty()) {
            try {
                File targetFile = new File(path);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                String newFileName =new Date().getTime()+"_"+file.getOriginalFilename();
                Files.copy(file.getInputStream(), Paths.get(path, newFileName));
                return Response.success(newFileName);
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
                return Response.warning(e.getMessage());
            }
        } else {
            return Response.warning("文件为空");
        }
    }
}
