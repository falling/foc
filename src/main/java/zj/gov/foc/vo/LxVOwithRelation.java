package zj.gov.foc.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User: falling
 * Date: 2018/2/11
 * Time: 下午10:14
 * Description:
 */
public class LxVOwithRelation extends BaseVO{
    private List<RelationVO> relationList = new ArrayList<>();
    private LxVO value;

    public List<RelationVO> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<RelationVO> relationList) {
        this.relationList = relationList;
    }

    public LxVO getValue() {
        return value;
    }

    public void setValue(LxVO value) {
        this.value = value;
    }
}
