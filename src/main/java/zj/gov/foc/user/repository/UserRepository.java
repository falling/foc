package zj.gov.foc.user.repository;

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
    @Query(value = "SELECT * FROM user WHERE user_name=?1 AND pwd=?2 AND del = '0'",nativeQuery = true)
    UserBean login(String username,String password);
}
