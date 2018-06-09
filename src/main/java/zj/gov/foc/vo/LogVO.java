package zj.gov.foc.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by User: falling
 * Date: 2018/2/20
 * Time: 下午12:01
 * Description:
 */
@Entity
public class LogVO{
    @Id
    @GeneratedValue
    private Long log_id;
    private String operating_user;
    private Date log_date;
    private String operating;
    private String old_value;
    private String new_value;

    public Long getLog_id() {
        return log_id;
    }

    public void setLog_id(Long log_id) {
        this.log_id = log_id;
    }

    public String getOperating_user() {
        return operating_user;
    }

    public void setOperating_user(String operating_user) {
        this.operating_user = operating_user;
    }

    public Date getLog_date() {
        return log_date;
    }

    public void setLog_date(Date log_date) {
        this.log_date = log_date;
    }

    public String getOperating() {
        return operating;
    }

    public void setOperating(String operating) {
        this.operating = operating;
    }

    public String getOld_value() {
        return old_value;
    }

    public void setOld_value(String old_value) {
        this.old_value = old_value;
    }

    public String getNew_value() {
        return new_value;
    }

    public void setNew_value(String new_value) {
        this.new_value = new_value;
    }
}
