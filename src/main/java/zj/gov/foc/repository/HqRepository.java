package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;

public interface HqRepository {
    /**
     * addHQ
     * @param chName
     * @param usedName
     * @param pyName
     * @param sex
     * @param ethnicity
     * @param passportNo
     * @param dateExpriy
     * @param dateBirth
     * @param idNum
     * @param oTel
     * @param cnTel
     * @param cnTe2
     * @param wechat
     * @param mail
     * @param qqNum
     * @param nativePlace
     * @param nationality
     * @param residence
     * @param cnResidence
     * @param presentIndustry
     * @param comName
     * @param position
     * @param education
     * @param health
     * @param registrant
     * @param photo
     * @param regDate
     * @param remarks
     * */
    @Query(value = "insert into hq (chName,usedName,pyName,sex,ethnicity,passportNo" +
            "dateExpriy,dateBirth,idNum,oTel,cnTel,cnTe2,wechat,mail,qqNum,nativePlace,nationality" +
            "residence,cnResidence,presentIndustry,comName,position,education,health,registrant," +
            "photo,regDate,remarks) values(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13," +
            "?14,?15,?16,?17,?18,?19,?20,?21,?22,?23,?24,?25,?26,?27,?28)",nativeQuery = true)
    void addHQ(String chName, String usedName, String pyName, String sex, String ethnicity, String passportNo,
               Timestamp dateExpriy, Timestamp dateBirth, String idNum, String oTel, String cnTel, String cnTe2,
               String wechat, String mail, String qqNum, String nativePlace, String nationality,
               String residence, String cnResidence, String presentIndustry, String comName, String position,
               String education, String health, String registrant, String photo, Timestamp regDate, String remarks);


    /**
     * deleteHQ
     * @param */

    /**
     * modifyHQ
     * @param */

    /**
     * searchHQ*/
}
