package zj.gov.foc.excelBean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by User: falling
 * Date: 2019-01-25
 * Time: 20:07
 * Description:
 */
public class LXExcelBean extends BaseRowModel {
    @NotEmpty
    @NotNull
    @ExcelProperty(index = 0,value="姓名")
    private String ch_name;
    @ExcelProperty(index = 1,value="常用电话")
    private String o_tel;
    @ExcelProperty(index = 2,value="拼音/外文名")
    private String py_name;
    @ExcelProperty(index = 3,value="曾用名")
    private String used_name;
    @ExcelProperty(index = 4,value="性别")
    private String sex;
    @ExcelProperty(index = 5,value="民族")
    private String ethnicity;
    @ExcelProperty(index = 6,value="出生年月",format = "yyyy-MM-dd")
    private Date date_birth;
    @ExcelProperty(index = 7,value="护照号")
    private String passport_no;
    @ExcelProperty(index = 8,value="身份证号")
    private String id_num;
    @ExcelProperty(index = 9,value="中国联系电话")
    private String cn_tel;
    @ExcelProperty(index = 10,value="微信号")
    private String wechat;
    @ExcelProperty(index = 11,value="邮箱")
    private String mail;
    @ExcelProperty(index = 12,value="QQ")
    private String qq_num;
    @ExcelProperty(index = 13,value="籍贯")
    private String native_place;
    @ExcelProperty(index = 14,value="现国籍")
    private String nationality;
    @ExcelProperty(index = 15,value="现居住国")
    private String residence;
    @ExcelProperty(index = 16,value="现旅居地详细地址")
    private String residenceDetail;
    @ExcelProperty(index = 17,value="中国居住地详细地址/原籍地")
    private String cn_residence;
    @ExcelProperty(index = 18,value="所从事行业")
    private String present_industry;
    @ExcelProperty(index = 19,value="公司/单位名称")
    private String com_name;
    @ExcelProperty(index = 20,value="职务")
    private String position;
    @ExcelProperty(index = 21,value="社会任职")
    private String social_services;
    @ExcelProperty(index = 22,value="毕业院校中文名")
    private String ch_cname;
    @ExcelProperty(index = 23,value="毕业院校英文名")
    private String en_cname;
    @ExcelProperty(index = 24,value="学位")
    private String degree;
    @ExcelProperty(index = 25,value="国内直系亲属姓名")
    private String family_name;
    @ExcelProperty(index = 26,value="国内直系亲属联系方式")
    private String family_tel;
    @ExcelProperty(index = 27,value="所属区/县侨联")
    private String manager_area;
    @ExcelProperty(index = 28,value="备注")
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
