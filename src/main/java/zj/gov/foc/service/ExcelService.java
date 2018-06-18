package zj.gov.foc.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.vo.HQVO;
import zj.gov.foc.vo.LxVO;
import zj.gov.foc.vo.QjVO;
import zj.gov.foc.vo.UserVO;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
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

    @Transactional
    public int saveExcel(Workbook wb) {
        Sheet sheet = wb.getSheetAt(0);
        List<HQVO> hqvoList = new ArrayList<>();
        List<LxVO> lxVOList = new ArrayList<>();
        List<QjVO> hq_qjVOList = new ArrayList<>();
        List<QjVO> lx_qjVOList = new ArrayList<>();
        int count = 0;
        for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row != null) {
                switch (getCellValue(row, 1)) {
                    case "华侨华人":
                        count++;
                        hqvoList.add(toExcelHQ(row));
                        break;
                    case "留学人员":
                        count++;
                        lxVOList.add(toExcelLX(row));
                        break;
                    case "归侨侨眷":
                        count++;
                        hq_qjVOList.add(toExcelQJ(row));
                        break;
                    case "留学生家属":
                        count++;
                        lx_qjVOList.add(toExcelLXJS(row));
                        break;
                }
            }
        }
        Long id = ((UserVO) httpSession.getAttribute("user")).getId();
        hqvoList.forEach(hqvo -> hqService.addHQCover(hqvo,id));
        lxVOList.forEach(lxVO -> lxService.addLXCover(lxVO,id));
        hq_qjVOList.forEach(hq_qj-> qjService.saveQjCover(hq_qj));
        lx_qjVOList.forEach(hq_qj-> qjService.saveQjCover(hq_qj));
        return count;
    }

    private String getCellValue(Row row, int index) {
        Cell cell = row.getCell(index);
        return cell == null ? "" : cell.toString().trim();
    }

    private HQVO toExcelHQ(Row row) {
        HQVO hqvo = new HQVO();
        hqvo.setCh_name(getCellValue(row, 2));//0:序号；1：身份选择；2：姓名；
        hqvo.setPy_name(getCellValue(row, 4));
        hqvo.setUsed_name(getCellValue(row, 6));
        hqvo.setSex(getCellValue(row, 8));
        hqvo.setEthnicity(getCellValue(row, 9));
        hqvo.setPassport_no(getCellValue(row, 12));
        try {
            hqvo.setDate_birth(Date.valueOf(getCellValue(row, 10)));
        } catch (Exception ignored) {
        }
        hqvo.setId_num(getCellValue(row, 13));
        hqvo.setO_tel(getCellValue(row, 3));
        hqvo.setCn_tel(getCellValue(row, 14));
        hqvo.setWechat(getCellValue(row, 16));
        hqvo.setMail(getCellValue(row, 18));
        hqvo.setQq_num(getCellValue(row, 20));
        hqvo.setNative_place(getCellValue(row, 22).replace(" ", "/"));
        hqvo.setNationality(getCellValue(row, 24));
        hqvo.setResidence(getCellValue(row, 26));
        hqvo.setResidenceDetail(getCellValue(row, 28));
        hqvo.setCn_residence(getCellValue(row, 30));
        hqvo.setPresent_industry(getCellValue(row, 34));
        hqvo.setCom_name(getCellValue(row, 36));
        hqvo.setPosition(getCellValue(row, 38));
        try {
            hqvo.setReg_date(Date.valueOf(getCellValue(row, 57).substring(0, 10)));
        } catch (Exception e) {
            hqvo.setReg_date(new Date(System.currentTimeMillis()));
        }
        hqvo.setRemarks(getCellValue(row, 55));
        hqvo.setSocial_services(getCellValue(row, 40));
        return hqvo;

    }

    private LxVO toExcelLX(Row row) {
        LxVO lxVO = new LxVO();
        lxVO.setCh_name(getCellValue(row, 2));//0:序号；1：身份选择；2：姓名；
        lxVO.setPy_name(getCellValue(row, 5));
        lxVO.setUsed_name(getCellValue(row, 7));
        lxVO.setSex(getCellValue(row, 8));
        lxVO.setEthnicity(getCellValue(row, 9));
        lxVO.setPassport_no(getCellValue(row, 12));
        try {
            lxVO.setDate_birth(Date.valueOf(getCellValue(row, 11)));
        } catch (Exception ignored) {
        }
        lxVO.setId_num(getCellValue(row, 13));
        lxVO.setO_tel(getCellValue(row, 3));
        lxVO.setCn_tel(getCellValue(row, 15));
        lxVO.setWechat(getCellValue(row, 17));
        lxVO.setMail(getCellValue(row, 19));
        lxVO.setQq_num(getCellValue(row, 21));
        lxVO.setNative_place(getCellValue(row, 23).replace(" ", "/"));
        lxVO.setNationality(getCellValue(row, 25));
        lxVO.setResidence(getCellValue(row, 27));
        lxVO.setResidenceDetail(getCellValue(row, 29));
        lxVO.setCn_residence(getCellValue(row, 31));
        lxVO.setPresent_industry(getCellValue(row, 35));
        lxVO.setCom_name(getCellValue(row, 37));
        lxVO.setPosition(getCellValue(row, 39));
        try {
            lxVO.setReg_date(Date.valueOf(getCellValue(row, 57).substring(0, 10)));
        } catch (Exception e) {
            lxVO.setReg_date(new Date(System.currentTimeMillis()));
        }
        lxVO.setRemarks(getCellValue(row, 55));
        lxVO.setSocial_services(getCellValue(row, 41));

        lxVO.setEn_cname(getCellValue(row, 43));
        lxVO.setCh_cname(getCellValue(row, 42));
        lxVO.setDegree(getCellValue(row, 44));

        lxVO.setFamily_name(getCellValue(row, 45));
        lxVO.setFamily_tel(getCellValue(row, 46));
        return lxVO;
    }


    private QjVO toExcelQJ(Row row) {
        QjVO qjVO = new QjVO();

        qjVO.setCh_name(getCellValue(row, 2));
        qjVO.setSex(getCellValue(row, 8));
        qjVO.setEthnicity(getCellValue(row, 9));
        qjVO.setPassport_no(getCellValue(row, 12));
        qjVO.setId_num(getCellValue(row, 13));
        qjVO.setO_tel(getCellValue(row, 3));
        qjVO.setFamily_location(getCellValue(row, 32));
        qjVO.setRemarks(getCellValue(row, 55));
        qjVO.setType("qj_hq");//用于区分侨眷和留学生家属
        qjVO.setO_name(getCellValue(row, 47));
        qjVO.setO_relation(getCellValue(row, 49));
        qjVO.setO_passport(getCellValue(row, 51));
        qjVO.setO_residence(getCellValue(row, 53));
        return qjVO;
    }

    private QjVO toExcelLXJS(Row row) {
        QjVO qjVO = new QjVO();

        qjVO.setCh_name(getCellValue(row, 2));
        qjVO.setSex(getCellValue(row, 8));
        qjVO.setEthnicity(getCellValue(row, 9));
        qjVO.setPassport_no(getCellValue(row, 12));
        qjVO.setId_num(getCellValue(row, 13));
        qjVO.setO_tel(getCellValue(row, 3));
        qjVO.setFamily_location(getCellValue(row, 33));
        qjVO.setRemarks(getCellValue(row, 55));
        qjVO.setType("qj_lx");
        qjVO.setO_name(getCellValue(row, 48));
        qjVO.setO_relation(getCellValue(row, 50));
        qjVO.setO_passport(getCellValue(row, 52));
        qjVO.setO_residence(getCellValue(row, 54));
        return qjVO;
    }

}
