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
            Sheet sheet = wb.getSheetAt(0);
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row != null){
                    switch (row.getCell(1).toString()){
                        case "华侨华人":
                            toExcelHQ(row);
                            break;
                        case "留学人员":
                            toExcelLX(row);
                            break;
                        case "归侨侨眷":
                            toExcelQJ(row);
                            break;
                        case "留学生家属":
                            toExcelLXJS(row);
                            break;
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void toExcelHQ(Row row){
        HQVO hqvo = new HQVO();
        hqvo.setCh_name(row.getCell(2).toString());//0:序号；1：身份选择；2：姓名；
        hqvo.setPy_name(row.getCell(4).toString());
        hqvo.setUsed_name(row.getCell(6).toString());
        hqvo.setSex(row.getCell(8).toString());
        hqvo.setEthnicity(row.getCell(9).toString());
        hqvo.setPassport_no(row.getCell(12).toString());
        hqvo.setDate_birth(java.sql.Date.valueOf(row.getCell(10).toString()));
        hqvo.setId_num(row.getCell(13).toString());
        hqvo.setO_tel(row.getCell(3).toString());
        hqvo.setCn_tel(row.getCell(14).toString());
        hqvo.setWechat(row.getCell(16).toString());
        hqvo.setMail(row.getCell(18).toString());
        hqvo.setQq_num(row.getCell(20).toString());
        hqvo.setNative_place(row.getCell(22).toString());
        hqvo.setNationality(row.getCell(24).toString());
        hqvo.setResidence(row.getCell(26).toString());
        hqvo.setResidenceDetail(row.getCell(28).toString());
        hqvo.setCn_residence(row.getCell(30).toString());
        hqvo.setPresent_industry(row.getCell(34).toString());
        hqvo.setCom_name(row.getCell(36).toString());
        hqvo.setPosition(row.getCell(38).toString());
        //hqvo.setRegistrant(formBean.getEntry().getCreator_name());
        hqvo.setReg_date(java.sql.Date.valueOf(row.getCell(57).toString()));
        hqvo.setRemarks(row.getCell(55).toString());
        hqvo.setSocial_services(row.getCell(40).toString());
        //hqvo.setDel("0");
    }
    public void toExcelLX(Row row){
        LxVO lxVO = new LxVO();
        lxVO.setCh_name(row.getCell(2).toString());//0:序号；1：身份选择；2：姓名；
        lxVO.setPy_name(row.getCell(5).toString());
        lxVO.setUsed_name(row.getCell(7).toString());
        lxVO.setSex(row.getCell(8).toString());
        lxVO.setEthnicity(row.getCell(9).toString());
        lxVO.setPassport_no(row.getCell(12).toString());
        lxVO.setDate_birth(java.sql.Date.valueOf(row.getCell(11).toString()));
        lxVO.setId_num(row.getCell(13).toString());
        lxVO.setO_tel(row.getCell(3).toString());
        lxVO.setCn_tel(row.getCell(15).toString());
        lxVO.setWechat(row.getCell(17).toString());
        lxVO.setMail(row.getCell(19).toString());
        lxVO.setQq_num(row.getCell(21).toString());
        lxVO.setNative_place(row.getCell(23).toString());
        lxVO.setNationality(row.getCell(25).toString());
        lxVO.setResidence(row.getCell(27).toString());
        lxVO.setResidenceDetail(row.getCell(29).toString());
        lxVO.setCn_residence(row.getCell(31).toString());
        lxVO.setPresent_industry(row.getCell(35).toString());
        lxVO.setCom_name(row.getCell(37).toString());
        lxVO.setPosition(row.getCell(39).toString());
        //lxVO.setRegistrant(formBean.getEntry().getCreator_name());
        lxVO.setReg_date(java.sql.Date.valueOf(row.getCell(57).toString()));
        lxVO.setRemarks(row.getCell(55).toString());
        lxVO.setSocial_services(row.getCell(41).toString());

        lxVO.setEn_cname(row.getCell(43).toString());
        lxVO.setCh_cname(row.getCell(42).toString());
        lxVO.setDegree(row.getCell(44).toString());

        lxVO.setFamily_name(row.getCell(45).toString());
        lxVO.setFamily_tel(row.getCell(46).toString());
    }



    public void toExcelQJ(Row row){
        QjVO qjVO = new QjVO();

        qjVO.setCh_name(row.getCell(2).toString());
        qjVO.setSex(row.getCell(8).toString());
        qjVO.setEthnicity(row.getCell(9).toString());
        qjVO.setPassport_no(row.getCell(12).toString());
        qjVO.setId_num(row.getCell(13).toString());
        qjVO.setO_tel(row.getCell(3).toString());
        qjVO.setFamily_location(row.getCell(32).toString());
        qjVO.setRemarks(row.getCell(55).toString());
        qjVO.setType("归侨侨眷");//用于区分侨眷和留学生家属
        qjVO.setO_name(row.getCell(47).toString());
        qjVO.setO_relation(row.getCell(49).toString());
        qjVO.setO_passport(row.getCell(51).toString());
        qjVO.setO_residence(row.getCell(53).toString());
    }
    public void toExcelLXJS(Row row){
        QjVO qjVO = new QjVO();

        qjVO.setCh_name(row.getCell(2).toString());
        qjVO.setSex(row.getCell(8).toString());
        qjVO.setEthnicity(row.getCell(9).toString());
        qjVO.setPassport_no(row.getCell(12).toString());
        qjVO.setId_num(row.getCell(13).toString());
        qjVO.setO_tel(row.getCell(3).toString());
        qjVO.setFamily_location(row.getCell(33).toString());
        qjVO.setRemarks(row.getCell(55).toString());
        qjVO.setType("留学生家属");
        qjVO.setO_name(row.getCell(48).toString());
        qjVO.setO_relation(row.getCell(50).toString());
        qjVO.setO_passport(row.getCell(52).toString());
        qjVO.setO_residence(row.getCell(54).toString());
    }

}
