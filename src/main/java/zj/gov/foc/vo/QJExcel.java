package zj.gov.foc.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User: falling
 * Date: 2018/2/18
 * Time: 上午10:53
 * Description: just for excel export
 */
public class QJExcel  implements Excel{
    private Long qj_id;

    private String ch_name;
    private String sex;
    private String ethnicity;
    private String passport_no;
    private String tel1;
    private String tel2;
    private List<RelationVO> relationList = new ArrayList<>();

    public Long getQj_id() {
        return qj_id;
    }

    public void setQj_id(Long qj_id) {
        this.qj_id = qj_id;
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

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public List<RelationVO> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<RelationVO> relationList) {
        this.relationList = relationList;
    }
}
