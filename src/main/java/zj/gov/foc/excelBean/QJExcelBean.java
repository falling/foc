package zj.gov.foc.excelBean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class QJExcelBean extends BaseRowModel {
  @NotEmpty
  @NotNull
  @ExcelProperty(index = 0,value="姓名")
  private String ch_name;
  @ExcelProperty(index = 1,value="常用电话")
  private String o_tel;
  @ExcelProperty(index = 2,value="性别")
  private String sex;
  @ExcelProperty(index = 3,value="民族")
  private String ethnicity;
  @ExcelProperty(index = 4,value="护照号")
  private String passport_no;
  @ExcelProperty(index = 5,value="身份证号")
  private String id_num;
  @ExcelProperty(index = 6,value="家庭住址")
  private String family_location;
  @ExcelProperty(index = 7,value="海外亲属姓名")
  private String o_name;
  @ExcelProperty(index = 8,value="与海外直系亲属关系")
  private String o_relation;
  @ExcelProperty(index = 9,value="海外直系亲属护照号")
  private String o_passport;
  @ExcelProperty(index = 10,value="海外直系亲属旅居国")
  private String o_residence;
  @ExcelProperty(index = 11,value="所属区/县侨联")
  private String manager_area;
  @ExcelProperty(index = 12,value="备注")
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
