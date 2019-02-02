package zj.gov.foc.cotroller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
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
            BeanUtils.copyProperties(qjVO, excelBean);
            qjExcelBeans.add(excelBean);
        });
        return export("归侨侨眷", qjExcelBeans);
    }

    @RequestMapping("/exportqj_lx")
    public ResponseEntity<byte[]> exportQJ_LX(@RequestBody List<QjVO> list) throws IOException {
        List<QJExcelBean> qjExcelBeans = new ArrayList<>();
        list.forEach(qjVO -> {
            QJExcelBean excelBean = new QJExcelBean();
            BeanUtils.copyProperties(qjVO, excelBean);
            qjExcelBeans.add(excelBean);
        });
        return export("留学生家属", qjExcelBeans);
    }

    @RequestMapping("/exporthq")
    public ResponseEntity<byte[]> exportHQ(@RequestBody List<HQVO> list) throws IOException {
        List<HQExcelBean> hqExcelBeanList = new ArrayList<>();
        list.forEach(qjVO -> {
            HQExcelBean excelBean = new HQExcelBean();
            BeanUtils.copyProperties(qjVO, excelBean);
            hqExcelBeanList.add(excelBean);
        });
        return export("华侨华人", hqExcelBeanList);
    }

    @RequestMapping("/exportlx")
    public ResponseEntity<byte[]> exportLX(@RequestBody List<LxVO> list) throws IOException {
        List<LXExcelBean> lxExcelBeanList = new ArrayList<>();
        list.forEach(qjVO -> {
            LXExcelBean excelBean = new LXExcelBean();
            BeanUtils.copyProperties(qjVO, excelBean);
            lxExcelBeanList.add(excelBean);
        });
        return export("留学人员", lxExcelBeanList);
    }

    private <T extends BaseRowModel> ResponseEntity<byte[]> export(String sheetName, List<T> list) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ExcelWriter writer = EasyExcelFactory.getWriter(out);
        Sheet sheet = new Sheet(1, 1, list.get(0).getClass());
        sheet.setSheetName(sheetName);
        writer.write(list, sheet);
        writer.finish();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(out.toByteArray(),
                headers, HttpStatus.CREATED);
    }
}
