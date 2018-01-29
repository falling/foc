package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import zj.gov.foc.po.LxBean;

import java.util.List;

public interface LXRepository extends Repository<LxBean, Long> {


    LxBean save(LxBean lxBean);

    @Query(value = "select * FROM lx where passport_no = ?1 and del='0'",nativeQuery = true)
    LxBean loadByPassport(String passport_no);

    @Query(value = "SELECT * FROM lx WHERE lx_id = ?1",nativeQuery = true)
    LxBean getById(Long lx_id);

    @Modifying
    @Query(value = "UPDATE lx SET del = '1' WHERE lx_id = ?1",nativeQuery = true)
    int delete(Long id);

    @Query(value = "SELECT * FROM lx WHERE ?!",nativeQuery = true)
    List<LxBean> search(String sql);
}
