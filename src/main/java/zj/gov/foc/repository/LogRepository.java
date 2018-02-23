package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zj.gov.foc.po.LogBean;

import java.util.List;

/**
 * Created by User: falling
 * Date: 2018/2/19
 * Time: 下午5:29
 * Description:
 */
public interface LogRepository extends CrudRepository<LogBean, Long> {

    @Query(value = "SELECT * FROM log WHERE o_id = ?1 AND identity = ?2 ORDER BY log_date DESC",nativeQuery = true)
    List<LogBean> getLogByO_idAndType(Long o_id, String type);
}
