package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import zj.gov.foc.po.HQBean;

public interface HQRepository extends Repository<HQBean, Long> {


    HQBean save(HQBean hqBean);

    /**
     * deleteHQ
     * @param hqid
     * return ture or false
     * */
    @Modifying
    @Query(value = "update hq set del = '1' where hq_id = ?1 ",nativeQuery = true)
    int deleteHQ(long hqid);


    @Query(value = "SELECT * FROM hq WHERE passport_no = ?1 and del='0'",nativeQuery = true)
    HQBean loadByPassport(String passport_no);

    @Query(value = "SELECT * FROM hq WHERE hq_id = ?1",nativeQuery = true)
    HQBean getById(Long hq_id);
}
