package zj.gov.foc.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "qj")
public class QJBean {

  @Id
  @GeneratedValue
  private Long oId;
  private Long qjId;
  private String chName;
  private String sex;
  private String ethnicity;
  private String idNum;
  private String relation;
  private String tel;
  private String te2;
  private String del;


  public long getOId() {
    return oId;
  }

  public void setOId(long oId) {
    this.oId = oId;
  }


  public long getQjId() {
    return qjId;
  }

  public void setQjId(long qjId) {
    this.qjId = qjId;
  }


  public String getChName() {
    return chName;
  }

  public void setChName(String chName) {
    this.chName = chName;
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


  public String getIdNum() {
    return idNum;
  }

  public void setIdNum(String idNum) {
    this.idNum = idNum;
  }


  public String getRelation() {
    return relation;
  }

  public void setRelation(String relation) {
    this.relation = relation;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getTe2() {
    return te2;
  }

  public void setTe2(String te2) {
    this.te2 = te2;
  }


  public String getDel() {
    return del;
  }

  public void setDel(String del) {
    this.del = del;
  }

}
