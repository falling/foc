package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zj.gov.foc.po.RelationBean;

import java.util.List;

/**
 * Created by User: falling
 * Date: 2018/2/11
 * Time: 下午2:28
 * Description:
 */

public interface RelationRepository extends CrudRepository<RelationBean, Long> {

    @Query(value = "select * from relationship WHERE qj_id = ?1",nativeQuery = true)
    List<RelationBean> getByQjID(Long qj_id);

    @Modifying
    @Query(value = "DELETE FROM relationship WHERE qj_id = ?1",nativeQuery = true)
    void deleteByqjId(Long qj_id);
}
