package zj.gov.foc.po;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relationship")
public class RelationBean {
  @Id
  @GeneratedValue
  private Long id;

  private Long o_id;
  private Long qj_id;
  private String type;
  private String relation;

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getRelation() {
    return relation;
  }

  public void setRelation(String relation) {
    this.relation = relation;
  }
}
