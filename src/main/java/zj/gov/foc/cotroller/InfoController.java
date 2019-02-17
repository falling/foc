package zj.gov.foc.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zj.gov.foc.service.*;
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
@RequestMapping("/api")
public class InfoController {

    @Autowired
    HQService hqService;

    @Autowired
    LXService lxService;

    @Autowired
    QJService qjService;

    @Autowired
    StatisticsService statisticsService;


    @Autowired
    ExcelService excelService;

    @Autowired
    HttpSession session;

    @Value("${upload-path}")
    private String path;


    @RequestMapping("/addHQInfo")
    public VO addHQInfo(@RequestBody HQVO hqvo, HttpSession httpSession) {
        UserVO userVO = (UserVO) httpSession.getAttribute("user");
        if (userVO == null) {
            return Response.warning("未登录");
        }
        if (hqService.addHQ(hqvo, userVO.getId()) != null) {
            return Response.success("录入成功");
        } else {
            return Response.warning("录入失败");
        }
    }

    @RequestMapping("/addQjInfo")
    public VO addQjInfo(@RequestBody QjVO qjVO) {
        UserVO userVO = (UserVO) session.getAttribute("user");
        if (qjService.saveQj(qjVO,userVO.getId()) != null) {
            return Response.success("录入成功");
        }
        return Response.success("录入失败");
    }

    @RequestMapping("/updateQjInfo")
    public VO updateQjInfo(@RequestBody QjVO qjVO) {
        UserVO userVO = (UserVO) session.getAttribute("user");
        qjService.update(qjVO,userVO.getId());
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
                              @RequestParam("id") long id) {
        if (type.equals("lx")) {
            if (lxService.confirmPassport(passport_no, id)) {
                return Response.warning("不存在");
            } else {
                return Response.success("存在");
            }
        } else if (type.equals("hq")) {
            if (hqService.confirmPassport(passport_no, id)) {
                return Response.warning("用户不存在");
            } else {
                return Response.success("存在");
            }

        } else {
            if (qjService.confirmPassport(passport_no, id)) {
                return Response.warning("不存在");
            } else {
                return Response.success("存在");
            }
        }
    }

    @RequestMapping("/loadByPassport")
    public VO loadByPassport(@RequestParam("passport_no") String passport_no,
                             @RequestParam("type") String type) {
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
        } else if (type.equals("qj_hq")) {
            VO result = qjService.loadByPassport(passport_no, "qj_hq");
            if (result == null) {
                return Response.warning("用户不存在");
            } else {
                return Response.success(result);
            }
        } else {
            VO result = qjService.loadByPassport(passport_no, "qj_lx");
            if (result == null) {
                return Response.warning("用户不存在");
            } else {
                return Response.success(result);
            }
        }
    }

    @RequestMapping("/updateLXInfo")
    public VO updateLXInfo(@RequestBody LxVO vo) {
        UserVO userVO = (UserVO) session.getAttribute("user");
        if (lxService.update(vo,userVO.getId()) != null) {
            return Response.success("更新成功");
        } else {
            return Response.warning("用户不存在");
        }
    }


    @RequestMapping("/updateHQInfo")
    public VO updateHQInfo(@RequestBody HQVO vo) {
        UserVO userVO = (UserVO) session.getAttribute("user");
        if (hqService.update(vo,userVO.getId()) != null) {
            return Response.success("更新成功");
        } else {
            return Response.warning("用户不存在");
        }
    }

    @RequestMapping("/deleteInfo")
    public VO deleteInfo(@RequestParam("id") Long id, @RequestParam("type") String type) {
        UserVO userVO = (UserVO) session.getAttribute("user");
        boolean result = false;
        if (type.equals("lx")) {
            result = lxService.deleteLX(id,userVO.getId());
        } else if (type.equals("hq")) {
            result = hqService.deleteHQ(id,userVO.getId());
        } else if (type.equals("qj")) {
            result = qjService.deleteQJ(id,userVO.getId());
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
        UserVO userVO = (UserVO) session.getAttribute("user");
        String manager_area = userVO.getManager_area();
        if (type.equals("lx")) {
            return Response.success(lxService.search(col, value,manager_area));
        } else if (type.equals("hq")) {
            return Response.success(hqService.search(col, value,manager_area));
        } else {
            return Response.success(qjService.search(col, value, type,manager_area));
        }
    }

    @RequestMapping("/statistics")
    public long[] getStatistics() {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String manager_area = userVO.getManager_area();
        return statisticsService.statistics(manager_area);
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

    @RequestMapping("/excelUpload")
    public VO excelUpload(@RequestParam("file") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                if(!file.getOriginalFilename().endsWith("xls") && !file.getOriginalFilename().endsWith("xlsx")){
                    return Response.warning("只能上传.xls文件和.xlsx文件");
                }
                excelService.saveExcel(file);
                return Response.success("文件上传成功，后台处理中");
            } else {
                return Response.warning("文件为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.warning("excel 文件格式有误");
        }
    }

}
