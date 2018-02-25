package zj.gov.foc.util;

import org.springframework.beans.BeanUtils;
import zj.gov.foc.po.HQBean;
import zj.gov.foc.po.LxBean;
import zj.gov.foc.po.QJBean;
import zj.gov.foc.po.RelationBean;
import zj.gov.foc.repository.HQRepository;
import zj.gov.foc.repository.LXRepository;
import zj.gov.foc.repository.QJRepository;
import zj.gov.foc.vo.RelationVO;

import java.util.List;

/**
 * Created by User: falling
 * Date: 2018/2/17
 * Time: 上午10:22
 * Description:
 */
public class CommonFunction {

    /**
     * 获取留学华侨的家庭成员
     * @param relations
     * @param relationVOList
     * @param qjRepository
     */
    public static void getQJRelationList(List relations,
                                  List<RelationVO> relationVOList,
                                  QJRepository qjRepository) {
        relations.forEach(e->{
            RelationVO relationVO = new RelationVO();
            BeanUtils.copyProperties(e, relationVO);
            QJBean qjBean = qjRepository.getById(relationVO.getQj_id());
            relationVO.setSex(qjBean.getSex());
            relationVO.setPassport_no(qjBean.getPassport_no());
            relationVO.setCh_name(qjBean.getCh_name());
            relationVOList.add(relationVO);
        });
    }

    /**
     * 获取侨眷的家庭成员
     * @param relations
     * @param relationVOList
     * @param hqRepository
     * @param lxRepository
     */
    public static void getQJRelationList(List<RelationBean> relations,
                                         List<RelationVO> relationVOList,
                                         HQRepository hqRepository,
                                         LXRepository lxRepository) {
        relations.forEach(e->{
            RelationVO relationVO = new RelationVO();
            BeanUtils.copyProperties(e,relationVO);
            if (e.getType().equals("留学")){
                LxBean lxBean = lxRepository.getById(e.getO_id());
                relationVO.setSex(lxBean.getSex());
                relationVO.setPassport_no(lxBean.getPassport_no());
                relationVO.setCh_name(lxBean.getCh_name());
                relationVOList.add(relationVO);
            }else if(e.getType().equals("华侨")){
                HQBean hqBean = hqRepository.getById(e.getO_id());
                relationVO.setSex(hqBean.getSex());
                relationVO.setPassport_no(hqBean.getPassport_no());
                relationVO.setCh_name(hqBean.getCh_name());
                relationVOList.add(relationVO);
            }
        });
    }

}
