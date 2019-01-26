package zj.gov.foc.excelBean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class QJExcelBean {
  @NotEmpty
  @NotNull
  @Excel(orderNum = "0",name="姓名")
  private String ch_name;
  @Excel(orderNum = "1",name="常用电话")
  private String o_tel;
  @Excel(orderNum = "2",name="性别")
  private String sex;
  @Excel(orderNum = "3",name="民族")
  private String ethnicity;
  @Excel(orderNum = "4",name="护照号")
  private String passport_no;
  @Excel(orderNum = "5",name="身份证号")
  private String id_num;
  @Excel(orderNum = "6",name="家庭住址")
  private String family_location;
  @Excel(orderNum = "7",name="海外亲属姓名")
  private String o_name;
  @Excel(orderNum = "8",name="与海外直系亲属关系")
  private String o_relation;
  @Excel(orderNum = "9",name="海外直系亲属护照号")
  private String o_passport;
  @Excel(orderNum = "10",name="海外直系亲属旅居国")
  private String o_residence;
  @Excel(orderNum = "11",name="所属区/县侨联")
  private String manager_area;
  @Excel(orderNum = "12",name="备注")
  private String remarks;
  private String type;



  public String getManager_area() {
    return manager_area;
  }

  public void setManager_area(String manager_area) {
    this.manager_area = manager_area;
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

  public String getFamily_location() {
    return family_location;
  }

  public void setFamily_location(String family_location) {
    this.family_location = family_location;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getO_name() {
    return o_name;
  }

  public void setO_name(String o_name) {
    this.o_name = o_name;
  }

  public String getO_relation() {
    return o_relation;
  }

  public void setO_relation(String o_relation) {
    this.o_relation = o_relation;
  }

  public String getO_residence() {
    return o_residence;
  }

  public void setO_residence(String o_residence) {
    this.o_residence = o_residence;
  }

  public String getO_passport() {
    return o_passport;
  }

  public void setO_passport(String o_passport) {
    this.o_passport = o_passport;
  }

  public String getCh_name() {
    return ch_name;
  }

  public void setCh_name(String ch_name) {
    this.ch_name = ch_name;
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

}
