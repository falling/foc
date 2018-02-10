package zj.gov.foc.vo;

import java.util.ArrayList;
import java.util.List;

public class QjVOwithRelation extends BaseVO{
  private List<RelationVO> relationList = new ArrayList<>();
  private QjVO value;

  public QjVO getValue() {
    return value;
  }

  public void setValue(QjVO value) {
    this.value = value;
  }

  public List<RelationVO> getRelationList() {
    return relationList;
  }

  public void setRelationList(List<RelationVO> relationList) {
    this.relationList = relationList;
  }
}
