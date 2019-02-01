package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zj.gov.foc.po.LxBean;

import java.util.List;

public interface LXRepository extends CrudRepository<LxBean, Long> {


    LxBean save(LxBean lxBean);

    @Query(value = "select * FROM lx where passport_no = ?1 and del='0'",nativeQuery = true)
    LxBean loadByPassport(String passport_no);

    @Query(value = "select * FROM lx where passport_no = ?1 and del='0' and manager_area like ?2",nativeQuery = true)
    LxBean loadByPassport(String value, String manager_area);

    @Query(value = "SELECT * FROM lx WHERE lx_id = ?1 and del='0'",nativeQuery = true)
    LxBean getById(Long lx_id);

    @Modifying
    @Query(value = "UPDATE lx SET del = '1' WHERE lx_id = ?1 and del='0'",nativeQuery = true)
    int deleteLx(long id);

    @Query(value = "select * from lx WHERE passport_no = ?1 and lx_id <> ?2 and del='0'",nativeQuery = true)
    LxBean confirmPassport(String passport_no, long id);


    @Query(value = "SELECT count(*) FROM lx WHERE del='0'",nativeQuery = true)
    long countLX();

    @Query(value = "SELECT count(*) FROM lx WHERE del='0' and manager_area like ?1 ",nativeQuery = true)
    Long countLX(String manager_area);

    @Query(value = "SELECT count(DISTINCT nationality) FROM lx WHERE del='0'",nativeQuery = true)
    long countCountry();

    @Query(value = "select * from lx where ch_name = ?1 and o_tel = ?2 and del='0' LIMIT 1",nativeQuery = true)
    LxBean searchIdByName_tel(String ch_name, String o_tel);

    @Query(value = "select sex from lx where sex='男' or sex = '女' and del='0' ",nativeQuery = true)
    List<String> getAllSex();

    @Query(value = "select sex from lx where sex='男' or sex = '女' and del='0' and manager_area like ?1",nativeQuery = true)
    List<String> getAllSex(String manager_area);

    @Query(value = "select residence,count(*) from lx where del='0' and residence<>'' group by residence",nativeQuery = true)
    Iterable<Object[]> groupByCountry();

    @Query(value = "select residence,count(*) from lx where del='0' and residence<>'' and manager_area like ?1 group by residence",nativeQuery = true)
    List<Object[]> groupByCountry(String manager_area);

    @Query(value = "select native_place,count(*) from lx where del='0' and native_place<>'' group by native_place", nativeQuery = true)
    List<Object[]> groupByNativePlace();

    @Query(value = "select native_place,count(*) from lx where del='0' and native_place<>'' and manager_area like ?1 group by native_place", nativeQuery = true)
    List<Object[]> groupByNativePlace(String manager_area);

    @Query(value = "select manager_area,count(*) from lx where del='0' and manager_area like ?1 group by manager_area",nativeQuery = true)
    Iterable<Object[]> groupByManagerArea(String area);

}
