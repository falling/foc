package zj.gov.foc.vo;

/**
 * Created by User: falling
 * Date: 2018/2/10
 * Time: 下午10:29
 * Description:
 */
public class RelationVO{


    private String ch_name;

    private String passport_no;

    private String sex;

    private Long id;
    private Long o_id;
    private Long qj_id;
    private String relation;
    private String type;


    public String getCh_name() {
        return ch_name;
    }

    public void setCh_name(String ch_name) {
        this.ch_name = ch_name;
    }

    public String getPassport_no() {
        return passport_no;
    }

    public void setPassport_no(String passport_no) {
        this.passport_no = passport_no;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getO_id() {
        return o_id;
    }

    public void setO_id(Long o_id) {
        this.o_id = o_id;
    }

    public Long getQj_id() {
        return qj_id;
    }

    public void setQj_id(Long qj_id) {
        this.qj_id = qj_id;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
