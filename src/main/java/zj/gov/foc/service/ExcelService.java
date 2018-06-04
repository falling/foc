package zj.gov.foc.service;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import zj.gov.foc.po.QJBean;
import zj.gov.foc.vo.HQVO;
import zj.gov.foc.vo.LxVO;
import zj.gov.foc.vo.QjVO;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelService {

    public void outputExcel( String path ) {
        try {
            Workbook wb = new HSSFWorkbook(new FileInputStream(path));
            DataFormatter formatter = new DataFormatter();
            Sheet sheet1 = wb.getSheetAt(0);
            toExcelHQ(sheet1);
            Sheet sheet2 = wb.getSheetAt(1);
            toExcelLX(sheet2);
            Sheet sheet3 = wb.getSheetAt(2);
            toExcelQJ(sheet3);
            Sheet sheet4 = wb.getSheetAt(3);
            toExcelLXJS(sheet4);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void toExcelHQ(Sheet sheet){
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row != null){
                HQVO hqvo = new HQVO();
                hqvo.setCh_name(row.getCell(2).toString());//0:序号；1：身份选择；2：姓名；
                hqvo.setPy_name(row.getCell(4).toString());
                hqvo.setUsed_name(row.getCell(5).toString());
                hqvo.setSex(row.getCell(6).toString());
                hqvo.setEthnicity(row.getCell(7).toString());
                hqvo.setPassport_no(row.getCell(9).toString());
                hqvo.setDate_birth(java.sql.Date.valueOf(row.getCell(8).toString()));
                hqvo.setId_num(row.getCell(10).toString());
                hqvo.setO_tel(row.getCell(3).toString());
                hqvo.setCn_tel(row.getCell(11).toString());
                hqvo.setWechat(row.getCell(12).toString());
                hqvo.setMail(row.getCell(13).toString());
                hqvo.setQq_num(row.getCell(14).toString());
                hqvo.setNative_place(row.getCell(15).toString());
                hqvo.setNationality(row.getCell(16).toString());
                hqvo.setResidence(row.getCell(17).toString());
                hqvo.setResidenceDetail(row.getCell(18).toString());
                hqvo.setCn_residence(row.getCell(19).toString());
                hqvo.setPresent_industry(row.getCell(20).toString());
                hqvo.setCom_name(row.getCell(21).toString());
                hqvo.setPosition(row.getCell(22).toString());
                //hqvo.setRegistrant(formBean.getEntry().getCreator_name());
                hqvo.setReg_date(java.sql.Date.valueOf(row.getCell(26).toString()));
                hqvo.setRemarks(row.getCell(24).toString());
                hqvo.setSocial_services(row.getCell(23).toString());
                //hqvo.setDel("0");

            }
        }
    }
    public void toExcelLX(Sheet sheet){
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row != null){
                LxVO lxVO = new LxVO();
                lxVO.setCh_name(row.getCell(2).toString());//0:序号；1：身份选择；2：姓名；
                lxVO.setPy_name(row.getCell(4).toString());
                lxVO.setUsed_name(row.getCell(5).toString());
                lxVO.setSex(row.getCell(6).toString());
                lxVO.setEthnicity(row.getCell(7).toString());
                lxVO.setPassport_no(row.getCell(9).toString());
                lxVO.setDate_birth(java.sql.Date.valueOf(row.getCell(8).toString()));
                lxVO.setId_num(row.getCell(10).toString());
                lxVO.setO_tel(row.getCell(3).toString());
                lxVO.setCn_tel(row.getCell(11).toString());
                lxVO.setWechat(row.getCell(12).toString());
                lxVO.setMail(row.getCell(13).toString());
                lxVO.setQq_num(row.getCell(14).toString());
                lxVO.setNative_place(row.getCell(15).toString());
                lxVO.setNationality(row.getCell(16).toString());
                lxVO.setResidence(row.getCell(17).toString());
                lxVO.setResidenceDetail(row.getCell(18).toString());
                lxVO.setCn_residence(row.getCell(19).toString());
                lxVO.setPresent_industry(row.getCell(20).toString());
                lxVO.setCom_name(row.getCell(21).toString());
                lxVO.setPosition(row.getCell(22).toString());
                //lxVO.setRegistrant(formBean.getEntry().getCreator_name());
                lxVO.setReg_date(java.sql.Date.valueOf(row.getCell(31).toString()));
                lxVO.setRemarks(row.getCell(29).toString());
                lxVO.setSocial_services(row.getCell(23).toString());

                lxVO.setEn_cname(row.getCell(25).toString());
                lxVO.setCh_cname(row.getCell(24).toString());
                lxVO.setDegree(row.getCell(26).toString());

                lxVO.setFamily_name(row.getCell(27).toString());
                lxVO.setFamily_tel(row.getCell(28).toString());

            }
        }
    }



    public void toExcelQJ(Sheet sheet){
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row != null){
                QjVO qjVO = new QjVO();

                qjVO.setCh_name(row.getCell(2).toString());
                qjVO.setSex(row.getCell(4).toString());
                qjVO.setEthnicity(row.getCell(5).toString());
                qjVO.setPassport_no(row.getCell(6).toString());
                qjVO.setId_num(row.getCell(7).toString());
                qjVO.setO_tel(row.getCell(3).toString());
                qjVO.setFamily_location(row.getCell(8).toString());
                qjVO.setRemarks(row.getCell(13).toString());
                qjVO.setType("1");
                qjVO.setO_name(row.getCell(9).toString());
                qjVO.setO_relation(row.getCell(10).toString());
                qjVO.setO_passport(row.getCell(11).toString());
                qjVO.setO_residence(row.getCell(12).toString());
            }
        }
    }
    public void toExcelLXJS(Sheet sheet){
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row != null){
                QjVO qjVO = new QjVO();

                qjVO.setCh_name(row.getCell(2).toString());
                qjVO.setSex(row.getCell(4).toString());
                qjVO.setEthnicity(row.getCell(5).toString());
                qjVO.setPassport_no(row.getCell(6).toString());
                qjVO.setId_num(row.getCell(7).toString());
                qjVO.setO_tel(row.getCell(3).toString());
                qjVO.setFamily_location(row.getCell(8).toString());
                qjVO.setRemarks(row.getCell(13).toString());
                qjVO.setType("0");
                qjVO.setO_name(row.getCell(9).toString());
                qjVO.setO_relation(row.getCell(10).toString());
                qjVO.setO_passport(row.getCell(11).toString());
                qjVO.setO_residence(row.getCell(12).toString());
            }
        }
    }

}
