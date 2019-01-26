package zj.gov.foc.excelBean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by User: falling
 * Date: 2019-01-25
 * Time: 20:07
 * Description:
 */
public class LXExcelBean {
    @NotEmpty
    @NotNull
    @Excel(orderNum = "0",name="姓名")
    private String ch_name;
    @Excel(orderNum = "1",name="常用电话")
    private String o_tel;
    @Excel(orderNum = "2",name="拼音/外文名")
    private String py_name;
    @Excel(orderNum = "3",name="曾用名")
    private String used_name;
    @Excel(orderNum = "4",name="性别")
    private String sex;
    @Excel(orderNum = "5",name="民族")
    private String ethnicity;
    @Excel(orderNum = "6",name="出生年月",format = "yyyy-MM-dd")
    private Date date_birth;
    @Excel(orderNum = "7",name="护照号")
    private String passport_no;
    @Excel(orderNum = "8",name="身份证号")
    private String id_num;
    @Excel(orderNum = "9",name="中国联系电话")
    private String cn_tel;
    @Excel(orderNum = "10",name="微信号")
    private String wechat;
    @Excel(orderNum = "11",name="邮箱")
    private String mail;
    @Excel(orderNum = "12",name="QQ")
    private String qq_num;
    @Excel(orderNum = "13",name="籍贯")
    private String native_place;
    @Excel(orderNum = "14",name="现国籍")
    private String nationality;
    @Excel(orderNum = "15",name="现居住国")
    private String residence;
    @Excel(orderNum = "16",name="现旅居地详细地址")
    private String residenceDetail;
    @Excel(orderNum = "17",name="中国居住地详细地址/原籍地")
    private String cn_residence;
    @Excel(orderNum = "18",name="所从事行业")
    private String present_industry;
    @Excel(orderNum = "19",name="公司/单位名称")
    private String com_name;
    @Excel(orderNum = "20",name="职务")
    private String position;
    @Excel(orderNum = "21",name="社会任职")
    private String social_services;
    @Excel(orderNum = "22",name="毕业院校中文名")
    private String ch_cname;
    @Excel(orderNum = "23",name="毕业院校英文名")
    private String en_cname;
    @Excel(orderNum = "24",name="学位")
    private String degree;
    @Excel(orderNum = "25",name="国内直系亲属姓名")
    private String family_name;
    @Excel(orderNum = "26",name="国内直系亲属联系方式")
    private String family_tel;
    @Excel(orderNum = "27",name="所属区/县侨联")
    private String manager_area;
    @Excel(orderNum = "28",name="备注")
    private String remarks;

    public String getCh_name() {
        return ch_name;
    }

    public void setCh_name(String ch_name) {
        this.ch_name = ch_name;
    }

    public String getO_tel() {
        return o_tel;
    }

    public void setO_tel(String o_tel) {
        this.o_tel = o_tel;
    }

    public String getPy_name() {
        return py_name;
    }

    public void setPy_name(String py_name) {
        this.py_name = py_name;
    }

    public String getUsed_name() {
        return used_name;
    }

    public void setUsed_name(String used_name) {
        this.used_name = used_name;
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

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public String getPassport_no() {
        return passport_no;
    }

    public void setPassport_no(String passport_no) {
        this.passport_no = passport_no;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
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
        this.native_place = native_place;
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

    public String getResidenceDetail() {
        return residenceDetail;
    }

    public void setResidenceDetail(String residenceDetail) {
        this.residenceDetail = residenceDetail;
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

    public String getSocial_services() {
        return social_services;
    }

    public void setSocial_services(String social_services) {
        this.social_services = social_services;
    }

    public String getCh_cname() {
        return ch_cname;
    }

    public void setCh_cname(String ch_cname) {
        this.ch_cname = ch_cname;
    }

    public String getEn_cname() {
        return en_cname;
    }

    public void setEn_cname(String en_cname) {
        this.en_cname = en_cname;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
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

    public String getManager_area() {
        return manager_area;
    }

    public void setManager_area(String manager_area) {
        this.manager_area = manager_area;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
