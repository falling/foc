package zj.gov.foc.po;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;

@Entity
@Table(name = "lx")
public class LXBean {

  @Id
  @GeneratedValue
  private long lxId;
  private String chName;
  private String usedName;
  private String pyName;
  private String sex;
  private String ethnicity;
  private String passportNo;
  private java.sql.Timestamp dateExpriy;
  private java.sql.Timestamp dateBirth;
  private String idNum;
  private String oTel;
  private String cnTel;
  private String cnTe2;
  private String wechat;
  private String mail;
  private String qqNum;
  private String nativePlace;
  private String nationality;
  private String residence;
  private String cnResidence;
  private String presentIndustry;
  private String comName;
  private String position;
  private String education;
  private String health;
  private String registrant;
  private String photo;
  private java.sql.Timestamp regDate;
  private String remarks;
  private String enCname;
  private String chCname;
  private String degree;
  private java.sql.Timestamp graDate;
  private String del;


  public long getLxId() {
    return lxId;
  }

  public void setLxId(long lxId) {
    this.lxId = lxId;
  }


  public String getChName() {
    return chName;
  }

  public void setChName(String chName) {
    this.chName = chName;
  }


  public String getUsedName() {
    return usedName;
  }

  public void setUsedName(String usedName) {
    this.usedName = usedName;
  }


  public String getPyName() {
    return pyName;
  }

  public void setPyName(String pyName) {
    this.pyName = pyName;
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


  public String getPassportNo() {
    return passportNo;
  }

  public void setPassportNo(String passportNo) {
    this.passportNo = passportNo;
  }


  public java.sql.Timestamp getDateExpriy() {
    return dateExpriy;
  }

  public void setDateExpriy(java.sql.Timestamp dateExpriy) {
    this.dateExpriy = dateExpriy;
  }


  public java.sql.Timestamp getDateBirth() {
    return dateBirth;
  }

  public void setDateBirth(java.sql.Timestamp dateBirth) {
    this.dateBirth = dateBirth;
  }


  public String getIdNum() {
    return idNum;
  }

  public void setIdNum(String idNum) {
    this.idNum = idNum;
  }


  public String getOTel() {
    return oTel;
  }

  public void setOTel(String oTel) {
    this.oTel = oTel;
  }


  public String getCnTel() {
    return cnTel;
  }

  public void setCnTel(String cnTel) {
    this.cnTel = cnTel;
  }


  public String getCnTe2() {
    return cnTe2;
  }

  public void setCnTe2(String cnTe2) {
    this.cnTe2 = cnTe2;
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


  public String getQqNum() {
    return qqNum;
  }

  public void setQqNum(String qqNum) {
    this.qqNum = qqNum;
  }


  public String getNativePlace() {
    return nativePlace;
  }

  public void setNativePlace(String nativePlace) {
    this.nativePlace = nativePlace;
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


  public String getCnResidence() {
    return cnResidence;
  }

  public void setCnResidence(String cnResidence) {
    this.cnResidence = cnResidence;
  }


  public String getPresentIndustry() {
    return presentIndustry;
  }

  public void setPresentIndustry(String presentIndustry) {
    this.presentIndustry = presentIndustry;
  }


  public String getComName() {
    return comName;
  }

  public void setComName(String comName) {
    this.comName = comName;
  }


  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }


  public String getEducation() {
    return education;
  }

  public void setEducation(String education) {
    this.education = education;
  }


  public String getHealth() {
    return health;
  }

  public void setHealth(String health) {
    this.health = health;
  }


  public String getRegistrant() {
    return registrant;
  }

  public void setRegistrant(String registrant) {
    this.registrant = registrant;
  }


  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }


  public java.sql.Timestamp getRegDate() {
    return regDate;
  }

  public void setRegDate(java.sql.Timestamp regDate) {
    this.regDate = regDate;
  }


  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }


  public String getEnCname() {
    return enCname;
  }

  public void setEnCname(String enCname) {
    this.enCname = enCname;
  }


  public String getChCname() {
    return chCname;
  }

  public void setChCname(String chCname) {
    this.chCname = chCname;
  }


  public String getDegree() {
    return degree;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }


  public java.sql.Timestamp getGraDate() {
    return graDate;
  }

  public void setGraDate(java.sql.Timestamp graDate) {
    this.graDate = graDate;
  }


  public String getDel() {
    return del;
  }

  public void setDel(String del) {
    this.del = del;
  }

}
