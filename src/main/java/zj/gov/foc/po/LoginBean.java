package zj.gov.foc.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "login")
public class LoginBean {
  @Id
  @GeneratedValue
  private Long login_id;
  private Date login_date;
  private Long user_id;
  private String ip_id;

  public Long getLogin_id() {
    return login_id;
  }

  public void setLogin_id(Long login_id) {
    this.login_id = login_id;
  }

  public Date getLogin_date() {
    return login_date;
  }

  public void setLogin_date(Date login_date) {
    this.login_date = login_date;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public String getIp_id() {
    return ip_id;
  }

  public void setIp_id(String ip_id) {
    this.ip_id = ip_id;
  }
}
