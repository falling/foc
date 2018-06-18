package zj.gov.foc.cotroller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import zj.gov.foc.vo.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by User: falling
 * Date: 2018/2/17
 * Time: 下午2:50
 * Description:
 */
@Controller
public class ExportController {
    @RequestMapping("/exportqj_hq")
    public ResponseEntity<byte[]> exportQJ_HQ(@RequestBody List<QjVO> list) throws IOException, IllegalAccessException {
        String[] title = new String[]{"中文名","性别" ,"民族", "护照号","身份证号",
                "常用电话", "家庭住址","海外亲属姓名","与海外直系亲属关系","海外直系亲属护照号","海外直系亲属旅居国或地区","备注"};
        String[] exclude = new String[]{"qj_id","type","status","info"};
        return exportXLSX(list,"侨眷",title,exclude);
    }

    @RequestMapping("/exportqj_lx")
    public ResponseEntity<byte[]> exportQJ_LX(@RequestBody List<QjVO> list) throws IOException, IllegalAccessException {
        String[] title = new String[]{"中文名","性别" ,"民族", "护照号","身份证号",
                "常用电话", "家庭住址","海外亲属姓名","与海外直系亲属关系","海外直系亲属护照号","海外直系亲属旅居国或地区","备注"};
        String[] exclude = new String[]{"qj_id","type","status","info"};
        return exportXLSX(list,"留学生家属",title,exclude);
    }

    @RequestMapping("/exporthq")
    public ResponseEntity<byte[]> exportHQ(@RequestBody List<HQVO> list) throws IllegalAccessException, IOException {
        String[] title = new String[]{"中文名", "曾用名", "拼音","性别" ,"民族", "护照号",
                "出生年月日", "身份证号", "常用电话", "中国联系电话",
                "微信", "电子邮箱", "QQ", "籍贯","现国籍", "现居住国或地区","现旅居地详细地址","中国居住详细地址", "所从事行业",
                "公司/单位名称", "职务", "社会任职", "备注"};
        String[] exclude = new String[]{"hq_id","registrant_name","reg_date","photo","status","info"};
        return exportXLSX(list,"华侨",title,exclude);
    }

    @RequestMapping("/exportlx")
    public ResponseEntity<byte[]> exportLX(@RequestBody List<LxVO> list) throws IOException, IllegalAccessException {
        String[] title = new String[]{"中文名", "曾用名", "拼音","性别" ,"民族", "护照号",
                "出生年月日", "身份证号", "常用电话", "中国联系电话",
                "微信", "电子邮箱", "QQ", "籍贯", "现国籍","现居住国或地区", "现旅居地详细地址", "中国居住详细地址", "所从事行业",
                "公司/单位名称", "职务", "社会任职","毕业院校英文名","毕业院校中文名","学位","国内直系亲属名字","国内直系亲属联系方式","备注"};
        String[] exclude = new String[]{"lx_id","registrant_name","reg_date","photo","status","info"};
        return exportXLSX(list,"留学",title,exclude);
    }

    private ResponseEntity<byte[]> exportXLSX(List<? extends VO> list,
                                              String sheetName,
                                              String[] title,
                                              String[] exclude) throws IllegalAccessException, IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sh = workbook.createSheet(sheetName);
        //title
        Row titleRow = sh.createRow(0);
        for (int i = 0; i < title.length; i++) {
            CellUtil.createCell(titleRow,i,title[i]);
        }
        //content
        int indexI = 1;
        for (VO t : list) {
            Row row = sh.createRow(indexI++);
            Field[] fields = t.getClass().getDeclaredFields();
            int indexJ = 0;
            for (Field field : fields) {
                field.setAccessible(true);
               if(Stream.of(exclude).anyMatch(e->e.equals(field.getName()))){
                    continue;
                }
                CellUtil.createCell(row, indexJ++, getValue(field.get(t)));
            }

        }

        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayInputStream);
        workbook.dispose();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(byteArrayInputStream.toByteArray(),
                headers, HttpStatus.CREATED);
    }

    private String getValue(Object ob){
        if (ob==null){
            return "";
        }else{
            return String.valueOf(ob);
        }
    }
}
