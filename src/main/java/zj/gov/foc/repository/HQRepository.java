package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zj.gov.foc.po.HQBean;

import java.util.List;

public interface HQRepository extends CrudRepository<HQBean, Long> {


    /**
     * deleteHQ
     * @param hqid
     * return
     * */
    @Modifying
    @Query(value = "update hq set del = '1' where hq_id = ?1 AND del='0'",nativeQuery = true)
    int deleteHQ(long hqid);

    @Query(value = "SELECT * FROM hq WHERE passport_no = ?1 and del='0'",nativeQuery = true)
    HQBean loadByPassport(String passport_no);

    @Query(value = "SELECT * FROM hq WHERE passport_no = ?1 and del='0' and manager_area like ?2",nativeQuery = true)
    HQBean loadByPassport(String value, String manager_area);

    @Query(value = "SELECT * FROM hq WHERE hq_id = ?1 and del='0'",nativeQuery = true)
    HQBean getById(Long hq_id);

    @Query(value = "SELECT * FROM hq WHERE passport_no = ?1 and hq_id <> ?2 and del='0'",nativeQuery = true)
    HQBean confirmPassport(String passport_no, long id);


    @Query(value = "SELECT count(*) FROM hq WHERE del='0'",nativeQuery = true)
    long countHQ();

    @Query(value = "SELECT count(*) FROM hq WHERE del='0' and manager_area like ?1",nativeQuery = true)
    Long countHQ(String manager_area);

    @Query(value = "select * from hq where ch_name = ?1 and o_tel = ?2 and del='0' LIMIT 1",nativeQuery = true)
    HQBean searchIdByName_tel(String ch_name, String o_tel);

    @Query(value = "select sex from hq where sex='男' or sex = '女' and del='0' ",nativeQuery = true)
    List<String> getAllSex();

    @Query(value = "select sex from hq where sex='男' or sex = '女' and del='0' and manager_area like ?1 ",nativeQuery = true)
    List<String> getAllSex(String manager_area);

    @Query(value = "select residence,count(*) from hq where del='0' and residence<>'' group by residence",nativeQuery = true)
    List<Object[]> groupByCountry();

    @Query(value = "select residence,count(*) from hq where del='0' and residence<>'' and manager_area like ?1 group by residence",nativeQuery = true)
    List<Object[]> groupByCountry(String manager_area);

    @Query(value = "select native_place,count(*) from hq where del='0' and native_place<>'' group by native_place", nativeQuery = true)
    List<Object[]> groupByNativePlace();

    @Query(value = "select native_place,count(*) from hq where del='0' and native_place<>'' and manager_area like ?1 group by native_place", nativeQuery = true)
    List<Object[]> groupByNativePlace(String manager_area);

    @Query(value = "select manager_area,count(*) from hq where del='0' and manager_area like ?1 group by manager_area",nativeQuery = true)
    Iterable<Object[]> groupByManagerArea(String area);
}
