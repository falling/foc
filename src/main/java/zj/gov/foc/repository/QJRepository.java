package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zj.gov.foc.po.QJBean;

import java.util.List;

public interface QJRepository extends CrudRepository<QJBean, Long> {

    QJBean save(QJBean qjVO);

    @Query(value = "SELECT * FROM qj WHERE passport_no = ?1 AND type=?2 AND del ='0'",nativeQuery = true)
    QJBean loadByPassport(String passport_no,String type);

    @Query(value = "select * from qj WHERE  qj_id = ?1 and del='0'",nativeQuery = true)
    QJBean getById(Long qj_id);

    @Query(value = "SELECT * FROM qj WHERE passport_no = ?1 and qj_id <> ?2 and del='0'",nativeQuery = true)
    QJBean confirmPassport(String passport_no, long id);

    @Modifying
    @Query(value = "update qj set del = '1' where qj_id = ?1 and del='0'",nativeQuery = true)
    int deleteQJ(Long id);

    @Query(value = "select * from qj where ch_name = ?1 and o_tel = ?2 and type= ?3 and del='0' LIMIT 1",nativeQuery = true)
    QJBean searchIdByName_tel(String ch_name, String o_tel,String type);

    @Query(value = "SELECT count(*) FROM qj WHERE del='0' and type = ?1 ",nativeQuery = true)
    Long count(String type);

    @Query(value = "SELECT count(*) FROM qj WHERE del='0' and type = ?1 and manager_area like ?2",nativeQuery = true)
    Long count(String qj_hq, String manager_area);

    @Query(value = "select sex from qj where sex='男' or sex = '女' and del='0' ",nativeQuery = true)
    List<String> getAllSex();

    @Query(value = "select sex from qj where sex='男' or sex = '女' and del='0' and manager_area like ?1",nativeQuery = true)
    List<String> getAllSex(String manager_area);


    @Query(value = "select o_residence,count(*) from qj where del='0' and type= ?1 and o_residence<>'' group by o_residence",nativeQuery = true)
    Iterable<Object[]> groupByCountry(String type);

    @Query(value = "select o_residence,count(*) from qj where del='0' and type= ?1 and o_residence<>'' and manager_area like ?2 group by o_residence",nativeQuery = true)
    List<Object[]> groupByCountry(String qj_hq, String manager_area);
}