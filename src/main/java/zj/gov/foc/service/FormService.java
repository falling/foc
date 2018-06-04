package zj.gov.foc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.FormBean;
import zj.gov.foc.vo.HQVO;
import zj.gov.foc.vo.LxVO;
import zj.gov.foc.vo.QjVO;

import java.sql.Date;

@Service
public class FormService {

    @Autowired
    QJService qjService;

    @Autowired
    HQService hqService;

    @Autowired
    LXService lxService;

    public String save(FormBean bean) {
        FormBean.EntryBean entryBean = bean.getEntry();

        switch (entryBean.getField_1()){
            case "华侨华人":
                saveAsHQ(entryBean); break;
            case "留学人员":
                saveAsLX(entryBean); break;
            case "归侨侨眷":
                saveAsQJ(entryBean); break;
            case "留学生家属":
                saveAsLXJS(entryBean); break;
        }

        return "success";
    }

    private void saveAsHQ(FormBean.EntryBean entryBean){
        HQVO hqvo = new HQVO();
        hqvo.setCh_name(entryBean.getField_2());
        hqvo.setPy_name(entryBean.getField_4());
        hqvo.setUsed_name(entryBean.getField_5());
        hqvo.setSex(entryBean.getField_6());
        hqvo.setEthnicity(entryBean.getField_7());
        hqvo.setPassport_no(entryBean.getField_9());
        try {
            hqvo.setDate_birth(Date.valueOf(entryBean.getField_8()));
        }catch (Exception ignored){
        }
        hqvo.setId_num(entryBean.getField_53());
        hqvo.setO_tel(entryBean.getField_3());
        hqvo.setCn_tel(entryBean.getField_10());
        hqvo.setWechat(entryBean.getField_11());
        hqvo.setMail(entryBean.getField_12());
        hqvo.setQq_num(Integer.toString(entryBean.getField_13()));
        hqvo.setNative_place(entryBean.getField_56().getProvince()+"/"+entryBean.getField_56().getCity());
        hqvo.setNationality(entryBean.getField_15());
        hqvo.setResidence(entryBean.getField_16());
        hqvo.setResidenceDetail(entryBean.getField_17());
        hqvo.setCn_residence(entryBean.getField_18().getProvince()+" "
                +entryBean.getField_18().getCity()+" "
                +entryBean.getField_18().getDistrict()+" "
                +entryBean.getField_18().getStreet());
        hqvo.setPresent_industry(entryBean.getField_19());
        hqvo.setCom_name(entryBean.getField_20());
        hqvo.setPosition(entryBean.getField_21());
        try {
            hqvo.setReg_date(Date.valueOf(entryBean.getCreated_at().substring(0,10)));
        }catch (Exception e){
            hqvo.setReg_date(new Date(System.currentTimeMillis()));
        }
        hqvo.setRemarks(entryBean.getField_23());
        hqvo.setSocial_services(entryBean.getField_22());

        hqService.addHQ(hqvo,2L);
    }

    private void saveAsLX(FormBean.EntryBean entryBean){
        LxVO lxVO = new LxVO();

        lxVO.setCh_name(entryBean.getField_2());
        lxVO.setPy_name(entryBean.getField_24());
        lxVO.setUsed_name(entryBean.getField_25());
        lxVO.setSex(entryBean.getField_6());
        lxVO.setEthnicity(entryBean.getField_7());
        lxVO.setPassport_no(entryBean.getField_9());
        try {
            lxVO.setDate_birth(Date.valueOf(entryBean.getField_26()));
        }catch (Exception ignored){
        }
        lxVO.setId_num(entryBean.getField_53());
        lxVO.setO_tel(entryBean.getField_3());
        lxVO.setCn_tel(entryBean.getField_27());
        lxVO.setWechat(entryBean.getField_28());
        lxVO.setMail(entryBean.getField_29());
        lxVO.setQq_num(Integer.toString(entryBean.getField_30()));
        lxVO.setNative_place(entryBean.getField_57().getProvince()+"/"+entryBean.getField_57().getCity());
        lxVO.setNationality(entryBean.getField_32());
        lxVO.setResidence(entryBean.getField_33());
        lxVO.setResidenceDetail(entryBean.getField_34());
        lxVO.setCn_residence(entryBean.getField_35().getProvince()+" "
                +entryBean.getField_35().getCity()+" "
                +entryBean.getField_35().getDistrict()+" "
                +entryBean.getField_35().getStreet());
        lxVO.setPresent_industry(entryBean.getField_36());
        lxVO.setCom_name(entryBean.getField_37());
        lxVO.setPosition(entryBean.getField_38());
        try {
            lxVO.setReg_date(java.sql.Date.valueOf(entryBean.getCreated_at().substring(0,10)));
        }catch (Exception e){
            lxVO.setReg_date(new Date(System.currentTimeMillis()));
        }
        lxVO.setRemarks(entryBean.getField_23());
        lxVO.setSocial_services(entryBean.getField_39());

        lxVO.setEn_cname(entryBean.getField_41());
        lxVO.setCh_cname(entryBean.getField_40());
        lxVO.setDegree(entryBean.getField_42());

        lxVO.setFamily_name(entryBean.getField_54());
        lxVO.setFamily_tel(entryBean.getField_55());
        lxService.addLX(lxVO,2L);

    }

    private void saveAsQJ(FormBean.EntryBean entryBean){
        QjVO qjVO = new QjVO();
        qjVO.setCh_name(entryBean.getField_2());
        qjVO.setSex(entryBean.getField_6());
        qjVO.setEthnicity(entryBean.getField_7());
        qjVO.setPassport_no(entryBean.getField_9());
        qjVO.setId_num(entryBean.getField_53());
        qjVO.setO_tel(entryBean.getField_3());
        qjVO.setFamily_location(entryBean.getField_43().getProvince()+" "
                +entryBean.getField_43().getCity()+" "
                +entryBean.getField_43().getDistrict()+" "
                +entryBean.getField_43().getStreet());
        qjVO.setRemarks(entryBean.getField_23());
        qjVO.setO_name(entryBean.getField_45());
        qjVO.setO_relation(entryBean.getField_47());
        qjVO.setO_passport(entryBean.getField_49());
        qjVO.setO_residence(entryBean.getField_51());
        qjVO.setType("qj_hq");
        qjService.saveQj(qjVO);
    }

    private void saveAsLXJS(FormBean.EntryBean entryBean){
        QjVO qjVO = new QjVO();
        qjVO.setCh_name(entryBean.getField_2());
        qjVO.setSex(entryBean.getField_6());
        qjVO.setEthnicity(entryBean.getField_7());
        qjVO.setPassport_no(entryBean.getField_9());
        qjVO.setId_num(entryBean.getField_53());
        qjVO.setO_tel(entryBean.getField_3());
        qjVO.setFamily_location(entryBean.getField_44().getProvince()+" "
                +entryBean.getField_44().getCity()+" "
                +entryBean.getField_44().getDistrict()+" "
                +entryBean.getField_44().getStreet());
        qjVO.setRemarks(entryBean.getField_23());
        qjVO.setO_name(entryBean.getField_46());
        qjVO.setO_relation(entryBean.getField_48());
        qjVO.setO_passport(entryBean.getField_50());
        qjVO.setO_residence(entryBean.getField_52());
        qjVO.setType("qj_lx");
        qjService.saveQj(qjVO);
    }
}
