package zj.gov.foc.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User: falling
 * Date: 2018/2/11
 * Time: 下午10:02
 * Description:
 */
public class HQVOWithRelation extends BaseVO{
    private List<RelationVO> relationList = new ArrayList<>();
    private HQVO value;

    public List<RelationVO> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<RelationVO> relationList) {
        this.relationList = relationList;
    }

    public HQVO getValue() {
        return value;
    }

    public void setValue(HQVO value) {
        this.value = value;
    }
}
