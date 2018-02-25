package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import zj.gov.foc.po.LxBean;

public interface LXRepository extends Repository<LxBean, Long> {


    LxBean save(LxBean lxBean);

    @Query(value = "select * FROM lx where passport_no = ?1 and del='0'",nativeQuery = true)
    LxBean loadByPassport(String passport_no);

    @Query(value = "SELECT * FROM lx WHERE lx_id = ?1 and del='0'",nativeQuery = true)
    LxBean getById(Long lx_id);

    @Modifying
    @Query(value = "UPDATE lx SET del = '1' WHERE lx_id = ?1 and del='0'",nativeQuery = true)
    int delete(Long id);

    @Query(value = "select * from lx WHERE passport_no = ?1 and lx_id <> ?2 and del='0'",nativeQuery = true)
    LxBean confirmPassport(String passport_no, long id);


    @Query(value = "SELECT count(*) FROM lx WHERE del='0'",nativeQuery = true)
    long countLX();

    @Query(value = "SELECT count(DISTINCT nationality) FROM lx WHERE del='0'",nativeQuery = true)
    long countCountry();
}
