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
import zj.gov.foc.vo.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

/**
 * Created by User: falling
 * Date: 2018/1/20
 * Time: 下午2:34
 * Description:
 */
@RestController
public class InfoController {

    @Autowired
    HQService hqService;

    @Autowired
    LXService lxService;

    @Value("${upload-path}")
    private String path;


    @RequestMapping("/addHQInfo")
    public VO addHQInfo(@RequestBody HQVO hqvo, HttpSession httpSession) {
        UserVO userVO = (UserVO) httpSession.getAttribute("user");
        if (userVO == null) {
            return Response.warning("未登录");
        }
        HQVO hqvo1 = hqService.addHQ(hqvo, userVO);
        if (hqvo1.getInfo().equals("录入成功")) {
            return Response.success(hqvo1.getInfo());
        } else {
            return Response.warning(hqvo1.getInfo());
        }
    }
    @RequestMapping("/addQjInfo")
    public VO addQjInfo(@RequestBody QjVOwithRelation vo) {
        QjVO qjVO = vo.getValue();
        List<RelationVO> relationVOList = vo.getRelationList();
        return Response.success("test");
//        UserVO userVO = (UserVO) httpSession.getAttribute("user");
//        if (userVO == null) {
//            return Response.warning("未登录");
//        }
//        HQVO hqvo1 = hqService.addHQ(hqvo, userVO);
//        if (hqvo1.getInfo().equals("录入成功")) {
//            return Response.success(hqvo1.getInfo());
//        } else {
//            return Response.warning(hqvo1.getInfo());
//        }
    }

    @RequestMapping("/addLXInfo")
    public VO addLXInfo(@RequestBody LxVO lxVO, HttpSession httpSession) {
        UserVO userVO = (UserVO) httpSession.getAttribute("user");
        if (userVO == null) {
            return Response.warning("未登录");
        }

        if (lxService.addLX(lxVO, userVO.getId())) {
            return Response.success("录入成功");
        } else {
            return Response.warning("录入失败，该护照已经添加");
        }
    }


    @RequestMapping("/loadByPassport")
    public VO loadByPassport(@RequestParam("passport_no") String passport_no, @RequestParam("type") String type) {
        if (type.equals("lx")) {
            VO result = lxService.loadByPassport(passport_no);
            if (result == null) {
                return Response.warning("用户不存在");
            } else {
                return Response.success(result);
            }
        } else if (type.equals("hq")) {
            VO result = hqService.loadByPassport(passport_no);
            if (result == null) {
                return Response.warning("用户不存在");
            } else {
                return Response.success(result);
            }

        } else {
            return Response.warning("未开放");
        }
    }

    @RequestMapping("/updateLXInfo")
    public VO updateLXInfo(@RequestBody LxVO vo) {
        if (lxService.update(vo) != null) {
            return Response.success("更新成功");
        } else {
            return Response.warning("用户不存在");
        }
    }


    @RequestMapping("/updateHQInfo")
    public VO updateHQInfo(@RequestBody HQVO vo) {
        hqService.update(vo);
        return Response.success("更新成功");
    }

    @RequestMapping("/deleteInfo")
    public VO deleteInfo(@RequestParam("id") Long id, @RequestParam("type") String type) {
        boolean result = false;
        if (type.equals("lx")) {
            result = lxService.delete(id);
        } else if (type.equals("hq")) {
            result = hqService.deleteHQ(id);
        } else if (type.equals("qj")) {

        }
        if (result) {
            return Response.success("删除成功");
        } else {
            return Response.warning("删除失败");
        }
    }

    @RequestMapping("/searchTable")
    public VO searchTable(@RequestParam("type") String type,@RequestParam("value") String value,@RequestParam("col") String col) {
        if (type.equals("lx")){
            return Response.success(lxService.search(col,value));
        }else if (type.equals("hq")){
            return Response.success(hqService.search(col,value));
        }else{
            return Response.success(lxService.search(col,value));
        }
    }


    @RequestMapping("/fileUpload")
    public VO fileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                File targetFile = new File(path);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                String newFileName = new Date(System.currentTimeMillis()).getTime() + "_" + file.getOriginalFilename();
                Files.copy(file.getInputStream(), Paths.get(path, newFileName));
                return Response.success("/" + newFileName);
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
                return Response.warning(e.getMessage());
            }
        } else {
            return Response.warning("文件为空");
        }
    }


}
