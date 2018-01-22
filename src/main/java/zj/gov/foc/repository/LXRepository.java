package zj.gov.foc.repository;

import org.springframework.data.repository.Repository;
import zj.gov.foc.po.LxBean;
import zj.gov.foc.vo.LxVO;

public interface LXRepository extends Repository<LxBean, Long> {


    LxVO save(LxVO lxVO);

}
