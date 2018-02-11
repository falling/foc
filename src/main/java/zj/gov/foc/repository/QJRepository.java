package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import zj.gov.foc.po.QJBean;

public interface QJRepository extends Repository<QJBean, Long> {

    QJBean save(QJBean qjVO);

    @Query(value = "SELECT * FROM qj WHERE passport_no = ?1 AND del ='0'",nativeQuery = true)
    QJBean loadByPassport(String passport_no);

    @Query(value = "select * from qj WHERE  qj_id = ?1 and del='0'",nativeQuery = true)
    QJBean getById(Long qj_id);

    @Query(value = "SELECT * FROM qj WHERE passport_no = ?1 and qj_id <> ?2 and del='0'",nativeQuery = true)
    QJBean confirmPassport(String passport_no, long id);

    @Modifying
    @Query(value = "update qj set del = '1' where qj_id = ?1 and del='0'",nativeQuery = true)
    int delete(Long id);
}