package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import zj.gov.foc.po.HQBean;


import java.sql.Timestamp;
import java.util.List;

public interface HQRepository extends Repository<HQBean, Integer> {
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
    @Query(value = "insert into hq (ch_Name,used_Name,py_Name,sex,ethnicity,passport_No,\n" +
            "            date_Expriy,date_Birth,id_Num,o_Tel,cn_Tel,cn_Te2,wechat,mail,qq_Num,native_Place,nationality,\n" +
            "            residence,cn_Residence,present_Industry,com_Name,position,education,health,registrant,\n" +
            "            photo,reg_Date,remarks,del) values(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13," +
            "?14,?15,?16,?17,?18,?19,?20,?21,?22,?23,?24,?25,?26,?27,?28,'0')",nativeQuery = true)
    void addHQ(String chName, String usedName, String pyName, String sex, String ethnicity, String passportNo,
               Timestamp dateExpriy, Timestamp dateBirth, String idNum, String oTel, String cnTel, String cnTe2,
               String wechat, String mail, String qqNum, String nativePlace, String nationality,
               String residence, String cnResidence, String presentIndustry, String comName, String position,
               String education, String health, String registrant, String photo, Timestamp regDate, String remarks);

    /**
     * AddHQLog
     * @param fieldName
     * @param value
     * @return List<HQBean>
     * */

    /**
     * deleteHQ
     * @param hqid
     * return ture or false
     * */
    @Query(value = "update hq set del = 1 where hq_id = ?1 ",nativeQuery = true)
    boolean deleteHQ(String hqid);

    /**
     * modifyHQ
     * @param hqid
     * @param fieldName
     * @param newValue
     *
     * */
    @Query(value = "update hq set ?1 = ?2 where hq_id = ?3 ", nativeQuery = true)
    boolean modifyHQ(String fieldName,String newValue,String hqid);

    /**
     * searchHQ
     * @param fieldName
     * @param value
     * @return List<HQBean>
     * */
    @Query(value = "select * from hq where ?1 like %?2%",nativeQuery = true)
    List<HQBean> searchHQ(String fieldName, String  value);


    /**
     * searchHQ
     * @param fieldName
     * @param value
     * @return List<HQBean>
     * */
}
