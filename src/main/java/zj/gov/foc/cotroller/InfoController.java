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
import zj.gov.foc.service.QJService;
import zj.gov.foc.service.StatisticsService;
import zj.gov.foc.util.Response;
import zj.gov.foc.vo.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;

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

    @Autowired
    QJService qjService;

    @Autowired
    StatisticsService statisticsService;

    @Value("${upload-path}")
    private String path;


    @RequestMapping("/addHQInfo")
    public VO addHQInfo(@RequestBody HQVO hqvo, HttpSession httpSession) {
        UserVO userVO = (UserVO) httpSession.getAttribute("user");
        if (userVO == null) {
            return Response.warning("未登录");
        }
        if (hqService.addHQ(hqvo, userVO)!=null) {
            return Response.success("录入成功");
        } else {
            return Response.warning("录入失败");
        }
    }

    @RequestMapping("/addQjInfo")
    public VO addQjInfo(@RequestBody QjVO qjVO) {
        if(qjService.saveQj(qjVO)!=null){
            return Response.success("录入成功");
        }
        return Response.success("录入失败");
    }

    @RequestMapping("/updateQjInfo")
    public VO updateQjInfo(@RequestBody QjVO qjVO) {
        qjService.update(qjVO);
        return Response.success("更新成功");
    }
    @RequestMapping("/addLXInfo")
    public VO addLXInfo(@RequestBody LxVO lxVO, HttpSession httpSession) {
        UserVO userVO = (UserVO) httpSession.getAttribute("user");
        if (userVO == null) {
            return Response.warning("未登录");
        }

        if (lxService.addLX(lxVO, userVO.getId()) != null) {
            return Response.success("录入成功");
        } else {
            return Response.warning("录入失败");
        }
    }

    @RequestMapping("/confirmPassport")
    public VO confirmPassport(@RequestParam("passport_no") String passport_no,
                             @RequestParam("type") String type,
                              @RequestParam("id")long id){
        if (type.equals("lx")) {
            if (lxService.confirmPassport(passport_no,id)) {
                return Response.warning("不存在");
            } else {
                return Response.success("存在");
            }
        } else if (type.equals("hq")) {
            if (hqService.confirmPassport(passport_no,id)) {
                return Response.warning("用户不存在");
            } else {
                return Response.success("存在");
            }

        } else {
            if (qjService.confirmPassport(passport_no,id)) {
                return Response.warning("不存在");
            } else {
                return Response.success("存在");
            }
        }
    }
    
    @RequestMapping("/loadByPassport")
    public VO loadByPassport(@RequestParam("passport_no") String passport_no,
                             @RequestParam("type") String type){
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
        } else if(type.equals("qj_hq")){
            VO result = qjService.loadByPassport(passport_no,"qj_hq");
            if (result == null) {
                return Response.warning("用户不存在");
            } else {
                return Response.success(result);
            }
        }else{
            VO result = qjService.loadByPassport(passport_no,"qj_lx");
            if (result == null) {
                return Response.warning("用户不存在");
            } else {
                return Response.success(result);
            }
        }
    }

    @RequestMapping("/updateLXInfo")
    public VO updateLXInfo(@RequestBody LxVO vo) {
        if (lxService.update(vo)!=null) {
            return Response.success("更新成功");
        } else {
            return Response.warning("用户不存在");
        }
    }


    @RequestMapping("/updateHQInfo")
    public VO updateHQInfo(@RequestBody HQVO vo) {
        if(hqService.update(vo)!=null) {
            return Response.success("更新成功");
        }else{
            return Response.warning("用户不存在");
        }
    }

    @RequestMapping("/deleteInfo")
    public VO deleteInfo(@RequestParam("id") Long id, @RequestParam("type") String type) {
        boolean result = false;
        if (type.equals("lx")) {
            result = lxService.deleteLX(id);
        } else if (type.equals("hq")) {
            result = hqService.deleteHQ(id);
        } else if (type.equals("qj")) {
            result = qjService.deleteQJ(id);
        }
        if (result) {
            return Response.success("删除成功");
        } else {
            return Response.warning("删除失败");
        }
    }

    @RequestMapping("/searchTable")
    public VO searchTable(@RequestParam("type") String type,
                          @RequestParam("value") String value,
                          @RequestParam("col") String col) {
        if (type.equals("lx")){
            return Response.success(lxService.search(col,value));
        }else if (type.equals("hq")){
            return Response.success(hqService.search(col,value));
        }else{
            return Response.success(qjService.search(col,value,type));
        }
    }

    @RequestMapping("/statistics")
    public long[] getStatistics(){
        return statisticsService.statistics();
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
