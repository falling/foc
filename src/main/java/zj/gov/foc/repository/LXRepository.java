package zj.gov.foc.repository;

import org.springframework.data.repository.Repository;
import zj.gov.foc.po.LxBean;

public interface LXRepository extends Repository<LxBean, Long> {


    LxBean save(LxBean lxBean);

}
