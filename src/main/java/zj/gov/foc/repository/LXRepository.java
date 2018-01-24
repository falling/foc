package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import zj.gov.foc.po.LxBean;

public interface LXRepository extends Repository<LxBean, Long> {


    LxBean save(LxBean lxBean);

    @Query(value = "select * FROM lx where passport_no = ?1 and del='0'",nativeQuery = true)
    LxBean loadByPassport(String passport_no);
}
