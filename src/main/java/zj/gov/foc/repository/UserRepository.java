package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import zj.gov.foc.po.UserBean;

import java.sql.Timestamp;
import java.util.Date;

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
    @Query(value = "update user set pwd = ?1 where user_name = ?2",nativeQuery = true)
    void changePwd(String username,String newPwd);

    @Modifying
    @Query(value = "INSERT INTO user(user_name, pwd, name, power, reg_date, remarks, del) VALUES (?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    int insert(String username, String password, String name, String power, Date date, String remarks, String del);

    @Modifying
    @Query(value = "update user set name= ?1,power = ?2 where user_id = ?3",nativeQuery = true)
    int updateUserInfo(String name,String power,Long id);

    @Modifying
    @Query(value = "update user set del = 1 where user_id =?1",nativeQuery = true)
    int deleteUser(Long id);
}
