package zj.gov.foc.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class LxVO extends BaseVO {
    @Id
    @GeneratedValue
    private Long lx_id;
    private String ch_name;
    private String used_name;
    private String py_name;
    private String sex;
    private String ethnicity;
    private String passport_no;
    private Date date_birth;
    private String id_num;
    private String o_tel;
    private String cn_tel;
    private String wechat;
    private String mail;
    private String qq_num;
    private String native_place;
    private String nationality;
    private String residence;
    private String residenceDetail;
    private String cn_residence;
    private String manager_area;
    private String present_industry;
    private String com_name;
    private String position;
    private String registrant_name;
    private String photo;
    private Date reg_date;
    private String social_services;
    private String en_cname;
    private String ch_cname;
    private String degree;
    private String family_name;
    private String family_tel;
    private String remarks;
//    private String del;


    public String getManager_area() {
        return manager_area;
    }

    public void setManager_area(String manager_area) {
        if (manager_area==null){
            this.manager_area = "浙江省";
        }else {
            manager_area = manager_area.trim();
            if (manager_area.contains(" ")) {
                this.manager_area = manager_area.replaceAll(" ", "/");
            } else {
                this.manager_area = manager_area;
            }
        }
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getFamily_tel() {
        return family_tel;
    }

    public void setFamily_tel(String family_tel) {
        this.family_tel = family_tel;
    }

    public String getResidenceDetail() {
        return residenceDetail;
    }

    public void setResidenceDetail(String residenceDetail) {
        this.residenceDetail = residenceDetail;
    }

    public String getSocial_services() {
        return social_services;
    }

    public void setSocial_services(String social_services) {
        this.social_services = social_services;
    }

    public Long getLx_id() {
        return lx_id;
    }

    public void setLx_id(Long lx_id) {
        this.lx_id = lx_id;
    }

    public String getCh_name() {
        return ch_name;
    }

    public void setCh_name(String ch_name) {
        this.ch_name = ch_name;
    }

    public String getUsed_name() {
        return used_name;
    }

    public void setUsed_name(String used_name) {
        this.used_name = used_name;
    }

    public String getPy_name() {
        return py_name;
    }

    public void setPy_name(String py_name) {
        this.py_name = py_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getPassport_no() {
        return passport_no;
    }

    public void setPassport_no(String passport_no) {
        this.passport_no = passport_no;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public String getO_tel() {
        return o_tel;
    }

    public void setO_tel(String o_tel) {
        this.o_tel = o_tel;
    }

    public String getCn_tel() {
        return cn_tel;
    }

    public void setCn_tel(String cn_tel) {
        this.cn_tel = cn_tel;
    }


    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getQq_num() {
        return qq_num;
    }

    public void setQq_num(String qq_num) {
        this.qq_num = qq_num;
    }

    public String getNative_place() {
        return native_place;
    }

    public void setNative_place(String native_place) {
        if (native_place == null) {
            this.native_place = "";
        } else {
            native_place = native_place.trim();
            if (native_place.contains(" ")) {
                this.native_place = native_place.replaceAll(" ", "/");
            } else {
                this.native_place = native_place;
            }
        }
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getCn_residence() {
        return cn_residence;
    }

    public void setCn_residence(String cn_residence) {
        this.cn_residence = cn_residence;
    }

    public String getPresent_industry() {
        return present_industry;
    }

    public void setPresent_industry(String present_industry) {
        this.present_industry = present_industry;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRegistrant_name() {
        return registrant_name;
    }

    public void setRegistrant_name(String registrant_name) {
        this.registrant_name = registrant_name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEn_cname() {
        return en_cname;
    }

    public void setEn_cname(String en_cname) {
        this.en_cname = en_cname;
    }

    public String getCh_cname() {
        return ch_cname;
    }

    public void setCh_cname(String ch_cname) {
        this.ch_cname = ch_cname;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }


//    public String getDel() {
//        return del;
//    }

//    public void setDel(String del) {
//        this.del = del;
//    }
}
