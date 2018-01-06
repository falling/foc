package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import zj.gov.foc.po.UserBean;

import java.sql.Timestamp;

public interface UserRepository extends Repository<UserBean, Integer> {

    /**
     * login
     * @param username
     * @param password
     * @return user info or null
     */
    @Query(value = "SELECT * FROM user WHERE user_name=?1 AND pwd=?2 AND del = '0'",nativeQuery = true)
    UserBean login(String username,String password);

    /**
     * reg
     * @param username
     * @param pwd
     * @param name
     * @param power
     * @param reg_date
     * @param remarks
     * @return user
     * */
    @Query(value = "insert into user(user_name,pwd,name,power,reg_date,remarks) values(?1,?2,?3,?4,?5,?6,'1')",nativeQuery = true)
    void  reg(String username, String pwd, String name, String power, Timestamp reg_date, String remarks);

    /**
     * search user
     * @param username
     * @return user info or null */
    @Query(value = "SELECT * FROM user WHERE user_name=?1 AND del = '0'",nativeQuery = true)
    UserBean searchUser(String username);

    /**
     * search changePwd
     * @param username
     * @param newPwd*/
    @Query(value = "update user set pwd = ?1 where username = ?2",nativeQuery = true)
    void changePwd(String username,String newPwd);
}
