package zj.gov.foc.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
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
import java.io.IOException;
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

    public void saveExcel(MultipartFile file){
        Long id = ((UserVO) httpSession.getAttribute("user")).getId();
        new Thread(() -> {
            long startT = System.currentTimeMillis();
            ExcelListener<HQExcelBean> hqListener = new ExcelListener<>();
            try {
                EasyExcelFactory.readBySax(file.getInputStream(), new Sheet(1, 1, HQExcelBean.class), hqListener);
                List<HQExcelBean> hqExcelBeanList = hqListener.getData();
                hqExcelBeanList.parallelStream().forEach(hqExcelBean -> {
                    HQVO hqvo = new HQVO();
                    BeanUtils.copyProperties(hqExcelBean, hqvo);
                    hqService.addHQCover(hqvo, id);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            hqListener.clearData();
            System.out.println("HQ: " + (System.currentTimeMillis()-startT));
        }).start();

        new Thread(() -> {
            long startT = System.currentTimeMillis();
            ExcelListener<LXExcelBean> lxListener = new ExcelListener<>();
            try {
                EasyExcelFactory.readBySax(file.getInputStream(), new Sheet(2, 1, LXExcelBean.class), lxListener);
                List<LXExcelBean> lxExcelBeanList = lxListener.getData();
                lxExcelBeanList.parallelStream().forEach(lxExcelBean -> {
                    LxVO lxVO = new LxVO();
                    BeanUtils.copyProperties(lxExcelBean, lxVO);
                    lxService.addLXCover(lxVO, id);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            lxListener.clearData();
            System.out.println("LX: " + (System.currentTimeMillis()-startT));
        }).start();

        new Thread(() -> {
            long startT = System.currentTimeMillis();
            ExcelListener<QJExcelBean> qjListener = new ExcelListener<>();
            try {
                EasyExcelFactory.readBySax(file.getInputStream(), new Sheet(3, 1, QJExcelBean.class), qjListener);
                List<QJExcelBean> qjExcelBeans = qjListener.getData();
                qjExcelBeans.parallelStream().forEach(qj -> {
                    QjVO qjVO = new QjVO();
                    BeanUtils.copyProperties(qj, qjVO);
                    qjVO.setType("qj_hq");
                    qjService.saveQjCover(qjVO,id);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("qj_hq: " + (System.currentTimeMillis()-startT));
            qjListener.clearData();
        }).start();
//        i = workbook.getSheetIndex("留学生家属");

        new Thread(() -> {
            long startT = System.currentTimeMillis();
            ExcelListener<QJExcelBean> lxjsListener = new ExcelListener<>();
            try {
                EasyExcelFactory.readBySax(file.getInputStream(), new Sheet(4, 1, QJExcelBean.class), lxjsListener);
                List<QJExcelBean> lxjsExcelBeans = lxjsListener.getData();
                lxjsExcelBeans.parallelStream().forEach(qj -> {
                    QjVO qjVO = new QjVO();
                    BeanUtils.copyProperties(qj, qjVO);
                    qjVO.setType("qj_lx");
                    qjService.saveQjCover(qjVO,id);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            lxjsListener.clearData();
            System.out.println("qj_lx: " + (System.currentTimeMillis()-startT));
        }).start();
    }
}
