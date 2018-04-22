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
  private long qj_id;
    private String type;
    private String ch_name;
    private String tel;
    private String sex;
    private String ethnicity;
    private String passport_no;
    private String id_num;
    private String address;
    private String kin_name;
    private String kin_relation;
    private String kin_country;
    private String kin_passport_no;
    private String remark;
    private String del;


    public long getQj_id() {
        return qj_id;
    }

    public void setQj_id(long qj_id) {
        this.qj_id = qj_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCh_name() {
        return ch_name;
    }

    public void setCh_name(String ch_name) {
        this.ch_name = ch_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKin_name() {
        return kin_name;
    }

    public void setKin_name(String kin_name) {
        this.kin_name = kin_name;
    }

    public String getKin_relation() {
        return kin_relation;
    }

    public void setKin_relation(String kin_relation) {
        this.kin_relation = kin_relation;
    }

    public String getKin_country() {
        return kin_country;
    }

    public void setKin_country(String kin_country) {
        this.kin_country = kin_country;
    }

    public String getKin_passport_no() {
        return kin_passport_no;
    }

    public void setKin_passport_no(String kin_passport_no) {
        this.kin_passport_no = kin_passport_no;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
