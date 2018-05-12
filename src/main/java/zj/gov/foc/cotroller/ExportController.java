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
    @RequestMapping("/exportqj")
    public ResponseEntity<byte[]> exportQJ(@RequestBody List<QJExcel> list) throws IOException, IllegalAccessException {
        String[] title = new String[]{"中文名","性别" ,"民族", "护照号或身份证号",
                "联系电话1", "联系电话2","家庭成员"};
        String[] exclude = new String[]{"relationList","qj_id"};
        return exportXLSX(list,"侨眷",title,exclude);
    }

    @RequestMapping("/exporthq")
    public ResponseEntity<byte[]> exportHQ(@RequestBody List<HQExcel> list) throws IllegalAccessException, IOException {
        String[] title = new String[]{"中文名", "曾用名", "拼音","性别" ,"民族", "护照号",
                "护照有效期", "出生年月日", "身份证号", "海外联系电话", "中国联系电话1",
                "中国联系电话2", "微信", "电子邮箱", "QQ", "籍贯", "现国籍", "旅居地", "中国居住地", "所从事行业",
                "公司/单位名称", "职务", "文化程度", "健康状态", "社会任职", "主要成就"};
        String[] exclude = new String[]{"relationList","hq_id","registrant_name","reg_date","photo"};
        return exportXLSX(list,"华侨",title,exclude);
    }

    @RequestMapping("/exportlx")
    public ResponseEntity<byte[]> exportLX(@RequestBody List<LXExcel> list) throws IOException, IllegalAccessException {
        String[] title = new String[]{"中文名", "曾用名", "拼音","性别" ,"民族", "护照号",
                "护照有效期", "出生年月日", "身份证号", "海外联系电话", "中国联系电话1",
                "中国联系电话2", "微信", "电子邮箱", "QQ", "籍贯", "现国籍", "旅居地", "中国居住地", "所从事行业",
                "公司/单位名称", "职务", "文化程度", "健康状态", "社会任职", "主要成就","毕业院校英文名","毕业院校中文名","学位","毕业时间"};
        String[] exclude = new String[]{"relationList","lx_id","registrant_name","reg_date","photo"};
        return exportXLSX(list,"留学",title,exclude);
    }

    private ResponseEntity<byte[]> exportXLSX(List<? extends Excel> list,
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
        for (Excel t : list) {
            Row row = sh.createRow(indexI++);
            Field[] fields = t.getClass().getDeclaredFields();
            int indexJ = 0;
            for (Field field : fields) {
                field.setAccessible(true);
               if(Stream.of(exclude).anyMatch(e->e.equals(field.getName()))){
                    continue;
                }
                CellUtil.createCell(row, indexJ++, String.valueOf(field.get(t)));
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
}
