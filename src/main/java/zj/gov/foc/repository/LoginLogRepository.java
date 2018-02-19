package zj.gov.foc.repository;

import org.springframework.data.repository.CrudRepository;
import zj.gov.foc.po.LoginBean;

/**
 * Created by User: falling
 * Date: 2018/2/19
 * Time: 下午2:53
 * Description:
 */
public interface LoginLogRepository extends CrudRepository<LoginBean, Long> {
}
