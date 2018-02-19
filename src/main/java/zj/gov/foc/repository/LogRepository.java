package zj.gov.foc.repository;

import org.springframework.data.repository.CrudRepository;
import zj.gov.foc.po.LogBean;

/**
 * Created by User: falling
 * Date: 2018/2/19
 * Time: 下午5:29
 * Description:
 */
public interface LogRepository extends CrudRepository<LogBean, Long> {
}
