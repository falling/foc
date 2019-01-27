package zj.gov.foc.cotroller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zj.gov.foc.excelBean.HQExcelBean;
import zj.gov.foc.excelBean.LXExcelBean;
import zj.gov.foc.excelBean.QJExcelBean;
import zj.gov.foc.vo.HQVO;
import zj.gov.foc.vo.LxVO;
import zj.gov.foc.vo.QjVO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User: falling
 * Date: 2018/2/17
 * Time: 下午2:50
 * Description:
 */
@RestController
@RequestMapping("/api")
public class ExportController {
    @RequestMapping("/exportqj_hq")
    public ResponseEntity<byte[]> exportQJ_HQ(@RequestBody List<QjVO> list) throws IOException {
        List<QJExcelBean> qjExcelBeans = new ArrayList<>();
        list.forEach(qjVO -> {
            QJExcelBean excelBean = new QJExcelBean();
            BeanUtils.copyProperties(qjVO,excelBean);
            qjExcelBeans.add(excelBean);
        });
        return export("归侨侨眷",QJExcelBean.class,qjExcelBeans);
    }

    @RequestMapping("/exportqj_lx")
    public ResponseEntity<byte[]> exportQJ_LX(@RequestBody List<QjVO> list) throws IOException {
        List<QJExcelBean> qjExcelBeans = new ArrayList<>();
        list.forEach(qjVO -> {
            QJExcelBean excelBean = new QJExcelBean();
            BeanUtils.copyProperties(qjVO,excelBean);
            qjExcelBeans.add(excelBean);
        });
        return export("留学生家属",QJExcelBean.class,qjExcelBeans);
    }

    @RequestMapping("/exporthq")
    public ResponseEntity<byte[]> exportHQ(@RequestBody List<HQVO> list) throws IOException {
        List<HQExcelBean> hqExcelBeanList = new ArrayList<>();
        list.forEach(qjVO -> {
            HQExcelBean excelBean = new HQExcelBean();
            BeanUtils.copyProperties(qjVO,excelBean);
            hqExcelBeanList.add(excelBean);
        });
        return export("华侨华人",HQExcelBean.class,hqExcelBeanList);
    }

    @RequestMapping("/exportlx")
    public ResponseEntity<byte[]> exportLX(@RequestBody List<LxVO> list) throws IOException {
        List<LXExcelBean> lxExcelBeanList = new ArrayList<>();
        list.forEach(qjVO -> {
            LXExcelBean excelBean = new LXExcelBean();
            BeanUtils.copyProperties(qjVO,excelBean);
            lxExcelBeanList.add(excelBean);
        });
        return export("留学人员",LXExcelBean.class,lxExcelBeanList);
    }

    private ResponseEntity<byte[]> export(String sheetName,Class<?> pojoClass,List list) throws IOException {
        ExportParams params = new ExportParams();
        params.setSheetName(sheetName);
        params.setType(ExcelType.XSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params,pojoClass,list);
        Sheet sheet = workbook.getSheet(sheetName);
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            sheet.autoSizeColumn(i,true);
        }
        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayInputStream);
        workbook.close();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(byteArrayInputStream.toByteArray(),
                headers, HttpStatus.CREATED);
    }
}
