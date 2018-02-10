package zj.gov.foc.vo;

/**
 * Created by User: falling
 * Date: 2018/2/10
 * Time: 下午10:29
 * Description:
 */
public class RelationVO extends BaseVO{


    private Long id;
    private Long o_id;
    private Long qj_id;
    private String relation;
    private String type;

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
