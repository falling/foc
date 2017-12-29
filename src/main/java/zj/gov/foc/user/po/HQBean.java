package zj.gov.foc.user.po;


import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "hq")
public class HQBean {
  @Id
  @GeneratedValue
  private long hqId;
  private String chName;
  private String usedName;
  private String pyName;
  private String sex;
  private String ethnicity;
  private String passportNo;
  private java.sql.Date dateExpriy;
  private java.sql.Date dateBirth;
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
  private java.sql.Date regDate;
  private String remarks;
  private String del;


  public long getHqId() {
    return hqId;
  }

  public void setHqId(long hqId) {
    this.hqId = hqId;
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


  public java.sql.Date getDateExpriy() {
    return dateExpriy;
  }

  public void setDateExpriy(java.sql.Date dateExpriy) {
    this.dateExpriy = dateExpriy;
  }


  public java.sql.Date getDateBirth() {
    return dateBirth;
  }

  public void setDateBirth(java.sql.Date dateBirth) {
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


  public java.sql.Date getRegDate() {
    return regDate;
  }

  public void setRegDate(java.sql.Date regDate) {
    this.regDate = regDate;
  }


  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }


  public String getDel() {
    return del;
  }

  public void setDel(String del) {
    this.del = del;
  }

}
