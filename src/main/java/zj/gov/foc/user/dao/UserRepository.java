package zj.gov.foc.user.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import zj.gov.foc.user.po.UserBean;

public interface UserRepository extends Repository<UserBean, Integer> {

    /**
     * login
     * @param username
     * @param password
     * @return user info or null
     */
    @Query(value = "SELECT * FROM user WHERE username=?1 AND pwd=?2 AND del = '1'",nativeQuery = true)
    UserBean login(String username,String password);
}
