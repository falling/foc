package zj.gov.foc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.*;
import zj.gov.foc.repository.RelationRepository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class FormService {
    public void from(FormBean formBean){
        if (formBean.getEntry().getField_32().equals(""))
    }

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
        //hqBean.setO_tel(formBean.getEntry().getField_11());
        hqBean.setCn_tel(formBean.getEntry().getField_10());
        hqBean.setWechat(formBean.getEntry().getField_11());
        hqBean.setMail(formBean.getEntry().getField_12());
        hqBean.setQq_num(Integer.toString(formBean.getEntry().getField_13()));
        hqBean.setNative_place(formBean.getEntry().getField_14());
        hqBean.setNationality(formBean.getEntry().getField_15());
        hqBean.setResidence(formBean.getEntry().getField_17());
        hqBean.setCn_residence(formBean.getEntry().getField_18().getProvince()+" "
                +formBean.getEntry().getField_18().getCity()+" "
                +formBean.getEntry().getField_18().getDistrict()+" "
                +formBean.getEntry().getField_18().getStreet());
        hqBean.setPresent_industry(formBean.getEntry().getField_19());
        hqBean.setCom_name(formBean.getEntry().getField_20());
        hqBean.setPosition(formBean.getEntry().getField_21());
       /* hqBean.setEducation(formBean.getEntry().getField_24());
        hqBean.setHealth(formBean.getEntry().getField_25());*/
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
        //lxBean.setO_tel(formBean.getEntry().getField_27());
        lxBean.setCn_tel(formBean.getEntry().getField_27());
        lxBean.setWechat(formBean.getEntry().getField_28());
        lxBean.setMail(formBean.getEntry().getField_29());
        lxBean.setQq_num(Integer.toString(formBean.getEntry().getField_30()));
        lxBean.setNative_place(formBean.getEntry().getField_31());
        lxBean.setNationality(formBean.getEntry().getField_32());
        lxBean.setResidence(formBean.getEntry().getField_34());
        lxBean.setCn_residence(formBean.getEntry().getField_35().getProvince()+" "
                +formBean.getEntry().getField_35().getCity()+" "
                +formBean.getEntry().getField_35().getDistrict()+" "
                +formBean.getEntry().getField_35().getStreet());
        lxBean.setPresent_industry(formBean.getEntry().getField_36());
        lxBean.setCom_name(formBean.getEntry().getField_37());
        lxBean.setPosition(formBean.getEntry().getField_38());
       /* lxBean.setEducation(formBean.getEntry().getField_24());
        lxBean.setHealth(formBean.getEntry().getField_25());*/
        //hqBean.setRegistrant(formBean.getEntry().getCreator_name());
        lxBean.setReg_date(java.sql.Date.valueOf(formBean.getEntry().getCreated_at()));
        lxBean.setRemarks(formBean.getEntry().getField_23());
        lxBean.setSocial_services(formBean.getEntry().getField_39());

        lxBean.setEn_cname(formBean.getEntry().getField_41());
        lxBean.setCh_cname(formBean.getEntry().getField_40());
        lxBean.setDegree(formBean.getEntry().getField_42());
        lxBean.setDel("0");


    }


    public void toQJ(FormBean formBean){
        QJBean qjBean = new QJBean();
        qjBean.setCh_name(formBean.getEntry().getField_2());
        qjBean.setSex(formBean.getEntry().getField_6());
        qjBean.setPassport_no(formBean.getEntry().getField_9());//表单中身份证号为必填，护照号未要求
        qjBean.setEthnicity(formBean.getEntry().getField_7());
        qjBean.setTel1(formBean.getEntry().getField_3());

        qjBean.setDel("0");

    }
    public void toLXQJ(FormBean formBean){
        QJBean qjBean = new QJBean();
        qjBean.setCh_name(formBean.getEntry().getField_2());
        qjBean.setSex(formBean.getEntry().getField_6());
        qjBean.setPassport_no(formBean.getEntry().getField_9());//表单中身份证号为必填，护照号未要求
        qjBean.setEthnicity(formBean.getEntry().getField_7());
        qjBean.setTel1(formBean.getEntry().getField_3());

        qjBean.setDel("0");

    }

    @Autowired
    RelationRepository relationRepository;

    public void toRelation(FormBean formBean){
        RelationBean relationBean = new RelationBean();


    }
}
