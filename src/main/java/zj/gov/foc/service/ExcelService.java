package zj.gov.foc.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zj.gov.foc.excelBean.HQExcelBean;
import zj.gov.foc.excelBean.LXExcelBean;
import zj.gov.foc.excelBean.QJExcelBean;
import zj.gov.foc.vo.HQVO;
import zj.gov.foc.vo.LxVO;
import zj.gov.foc.vo.QjVO;
import zj.gov.foc.vo.UserVO;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    HttpSession httpSession;

    @Autowired
    HQService hqService;

    @Autowired
    LXService lxService;

    @Autowired
    QJService qjService;

    public int saveExcel(MultipartFile file) throws Exception {
        Workbook workbook;
        try {
            workbook = new HSSFWorkbook(file.getInputStream());//.xls
        }catch (Exception ignore){
            workbook = new XSSFWorkbook(file.getInputStream());//.xlsx
        }
        ImportParams importParams = new ImportParams();
        importParams.setNeedVerfiy(true);

        Long id = ((UserVO) httpSession.getAttribute("user")).getId();
        int count = 0;
        int i = workbook.getSheetIndex("华侨华人");
        importParams.setStartSheetIndex(i);
        List<HQExcelBean> hqExcelBeanList = ExcelImportUtil.importExcel(file.getInputStream(), HQExcelBean.class,importParams);
        hqExcelBeanList.forEach(hqExcelBean -> {
            HQVO hqvo = new HQVO();
            BeanUtils.copyProperties(hqExcelBean,hqvo);
            hqService.addHQCover(hqvo,id);
        });
        count+=hqExcelBeanList.size();

        i = workbook.getSheetIndex("留学人员");
        importParams.setStartSheetIndex(i);
        List<LXExcelBean> lxExcelBeanList = ExcelImportUtil.importExcel(file.getInputStream(), LXExcelBean.class,importParams);
        lxExcelBeanList.forEach(lxExcelBean->{
            LxVO lxVO = new LxVO();
            BeanUtils.copyProperties(lxExcelBean,lxVO);
            lxService.addLXCover(lxVO,id);
        });

        count+=lxExcelBeanList.size();
        i = workbook.getSheetIndex("归侨侨眷");
        importParams.setStartSheetIndex(i);
        List<QJExcelBean> qjExcelBeans = ExcelImportUtil.importExcel(file.getInputStream(), QJExcelBean.class,importParams);
        qjExcelBeans.forEach(qj->{
            QjVO qjVO = new QjVO();
            BeanUtils.copyProperties(qj,qjVO);
            qjVO.setType("qj_hq");
            qjService.saveQjCover(qjVO);
        });
        count+=qjExcelBeans.size();
        i = workbook.getSheetIndex("留学生家属");
        importParams.setStartSheetIndex(i);
        List<QJExcelBean> lxjsExcelBeans = ExcelImportUtil.importExcel(file.getInputStream(), QJExcelBean.class,importParams);
        lxjsExcelBeans.forEach(qj->{
            QjVO qjVO = new QjVO();
            BeanUtils.copyProperties(qj,qjVO);
            qjVO.setType("qj_lx");
            qjService.saveQjCover(qjVO);
        });
        count+=lxjsExcelBeans.size();
        return count;
    }
}
