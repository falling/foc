package zj.gov.foc.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log")
public class LogBean {
  @Id
  @GeneratedValue
  private Long logId;
  private java.sql.Timestamp logDate;
  private long oId;
  private long operatingUser;
  private String fields;
  private String operating;
  private String oldValue;
  private String newValue;
  private String identity;


  public long getLogId() {
    return logId;
  }

  public void setLogId(long logId) {
    this.logId = logId;
  }


  public java.sql.Timestamp getLogDate() {
    return logDate;
  }

  public void setLogDate(java.sql.Timestamp logDate) {
    this.logDate = logDate;
  }


  public long getOId() {
    return oId;
  }

  public void setOId(long oId) {
    this.oId = oId;
  }


  public long getOperatingUser() {
    return operatingUser;
  }

  public void setOperatingUser(long operatingUser) {
    this.operatingUser = operatingUser;
  }


  public String getFields() {
    return fields;
  }

  public void setFields(String fields) {
    this.fields = fields;
  }


  public String getOperating() {
    return operating;
  }

  public void setOperating(String operating) {
    this.operating = operating;
  }


  public String getOldValue() {
    return oldValue;
  }

  public void setOldValue(String oldValue) {
    this.oldValue = oldValue;
  }


  public String getNewValue() {
    return newValue;
  }

  public void setNewValue(String newValue) {
    this.newValue = newValue;
  }


  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }

}
