package zj.gov.foc.service;

import zj.gov.foc.po.FormBean;
import zj.gov.foc.po.HQBean;
import zj.gov.foc.po.LxBean;
import zj.gov.foc.po.QJBean;

public class FormService {
    public void toHQ(FormBean formBean){
        HQBean hqBean = new HQBean();

        hqBean.setCh_name(formBean.getEntry().getField_2());
        hqBean.setPy_name(formBean.getEntry().getField_4());
        hqBean.setUsed_name(formBean.getEntry().getField_5());
        hqBean.setSex(formBean.getEntry().getField_6());
        hqBean.setEthnicity(formBean.getEntry().getField_7());
        hqBean.setPassport_no(formBean.getEntry().getField_9());
        hqBean.setDate_birth(java.sql.Date.valueOf(formBean.getEntry().getField_8()));
        hqBean.setId_num(formBean.getEntry().getField_53());
        hqBean.setO_tel(formBean.getEntry().getField_3());
        hqBean.setCn_tel(formBean.getEntry().getField_10());
        hqBean.setWechat(formBean.getEntry().getField_11());
        hqBean.setMail(formBean.getEntry().getField_12());
        hqBean.setQq_num(Integer.toString(formBean.getEntry().getField_13()));
        hqBean.setNative_place(formBean.getEntry().getField_56().getProvince()+" "+formBean.getEntry().getField_56().getCity());
        hqBean.setNationality(formBean.getEntry().getField_15());
        hqBean.setResidence(formBean.getEntry().getField_16());
        hqBean.setResidenceDetail(formBean.getEntry().getField_17());
        hqBean.setCn_residence(formBean.getEntry().getField_18().getProvince()+" "
                +formBean.getEntry().getField_18().getCity()+" "
                +formBean.getEntry().getField_18().getDistrict()+" "
                +formBean.getEntry().getField_18().getStreet());
        hqBean.setPresent_industry(formBean.getEntry().getField_19());
        hqBean.setCom_name(formBean.getEntry().getField_20());
        hqBean.setPosition(formBean.getEntry().getField_21());
        //hqBean.setRegistrant(formBean.getEntry().getCreator_name());
        hqBean.setReg_date(java.sql.Date.valueOf(formBean.getEntry().getCreated_at()));
        hqBean.setRemarks(formBean.getEntry().getField_23());
        hqBean.setSocial_services(formBean.getEntry().getField_22());
        hqBean.setDel("0");
    }

    public void toLX(FormBean formBean){
        LxBean lxBean = new LxBean();

        lxBean.setCh_name(formBean.getEntry().getField_2());
        lxBean.setPy_name(formBean.getEntry().getField_24());
        lxBean.setUsed_name(formBean.getEntry().getField_25());
        lxBean.setSex(formBean.getEntry().getField_6());
        lxBean.setEthnicity(formBean.getEntry().getField_7());
        lxBean.setPassport_no(formBean.getEntry().getField_9());
        lxBean.setDate_birth(java.sql.Date.valueOf(formBean.getEntry().getField_26()));
        lxBean.setId_num(formBean.getEntry().getField_53());
        lxBean.setO_tel(formBean.getEntry().getField_3());
        lxBean.setCn_tel(formBean.getEntry().getField_27());
        lxBean.setWechat(formBean.getEntry().getField_28());
        lxBean.setMail(formBean.getEntry().getField_29());
        lxBean.setQq_num(Integer.toString(formBean.getEntry().getField_30()));
        lxBean.setNative_place(formBean.getEntry().getField_57().getProvince()+" "+formBean.getEntry().getField_57().getCity());
        lxBean.setNationality(formBean.getEntry().getField_32());
        lxBean.setResidence(formBean.getEntry().getField_33());
        lxBean.setResidenceDetail(formBean.getEntry().getField_34());
        lxBean.setCn_residence(formBean.getEntry().getField_35().getProvince()+" "
                +formBean.getEntry().getField_35().getCity()+" "
                +formBean.getEntry().getField_35().getDistrict()+" "
                +formBean.getEntry().getField_35().getStreet());
        lxBean.setPresent_industry(formBean.getEntry().getField_36());
        lxBean.setCom_name(formBean.getEntry().getField_37());
        lxBean.setPosition(formBean.getEntry().getField_38());
        //lxBean.setRegistrant(formBean.getEntry().getCreator_name());
        lxBean.setReg_date(java.sql.Date.valueOf(formBean.getEntry().getCreated_at()));
        lxBean.setRemarks(formBean.getEntry().getField_23());
        lxBean.setSocial_services(formBean.getEntry().getField_39());

        lxBean.setEn_cname(formBean.getEntry().getField_41());
        lxBean.setCh_cname(formBean.getEntry().getField_40());
        lxBean.setDegree(formBean.getEntry().getField_42());

        lxBean.setFamily_name(formBean.getEntry().getField_54());
        lxBean.setFamily_tel(formBean.getEntry().getField_55());

        lxBean.setDel("0");


    }

    public void toQJ(FormBean formBean){
        QJBean qjBean = new QJBean();

        qjBean.setCh_name(formBean.getEntry().getField_2());
        qjBean.setSex(formBean.getEntry().getField_6());
        qjBean.setEthnicity(formBean.getEntry().getField_7());
        qjBean.setPassport_no(formBean.getEntry().getField_9());
        qjBean.setId_num(formBean.getEntry().getField_53());
        qjBean.setO_tel(formBean.getEntry().getField_3());
        qjBean.setFamily_location(formBean.getEntry().getField_43().getProvince()+" "
                +formBean.getEntry().getField_43().getCity()+" "
                +formBean.getEntry().getField_43().getDistrict()+" "
                +formBean.getEntry().getField_43().getStreet());
        qjBean.setRemarks(formBean.getEntry().getField_23());
        qjBean.setO_name(formBean.getEntry().getField_45());
        qjBean.setO_relation(formBean.getEntry().getField_47());
        qjBean.setO_passport(formBean.getEntry().getField_49());
        qjBean.setO_residence(formBean.getEntry().getField_51());
        qjBean.setDel("0");

    }

    public void toLXJS(FormBean formBean){
        QJBean qjBean = new QJBean();

        qjBean.setCh_name(formBean.getEntry().getField_2());
        qjBean.setSex(formBean.getEntry().getField_6());
        qjBean.setEthnicity(formBean.getEntry().getField_7());
        qjBean.setPassport_no(formBean.getEntry().getField_9());
        qjBean.setId_num(formBean.getEntry().getField_53());
        qjBean.setO_tel(formBean.getEntry().getField_3());
        qjBean.setFamily_location(formBean.getEntry().getField_44().getProvince()+" "
                +formBean.getEntry().getField_44().getCity()+" "
                +formBean.getEntry().getField_44().getDistrict()+" "
                +formBean.getEntry().getField_44().getStreet());
        qjBean.setRemarks(formBean.getEntry().getField_23());
        qjBean.setO_name(formBean.getEntry().getField_46());
        qjBean.setO_relation(formBean.getEntry().getField_48());
        qjBean.setO_passport(formBean.getEntry().getField_50());
        qjBean.setO_residence(formBean.getEntry().getField_52());
        qjBean.setDel("0");
    }


}
