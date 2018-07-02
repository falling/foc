package zj.gov.foc;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by User: falling
 * Date: 2018/6/21
 * Time: 下午10:26
 * Description:
 */
public class POITest {
    public static void main(String args[]) throws IOException {
        FileInputStream file = new FileInputStream(new File("ab.xls"));
        Workbook wb = new HSSFWorkbook(file);
        Sheet sheet = wb.getSheetAt(0);
        System.out.println(sheet.getLastRowNum());
        System.out.println(sheet.getPhysicalNumberOfRows());

        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            System.out.println(row.getCell(0));
        }

    }
}
