package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import zj.gov.foc.po.HQBean;

import java.util.Date;

public interface HQRepository extends Repository<HQBean, Long> {

    @Modifying
    @Query(value = "insert into hq (ch_Name,used_Name,py_Name,sex,ethnicity,passport_No,\n" +
            "            date_Expriy,date_Birth,id_Num,o_Tel,cn_Tel,cn_Te2,wechat,mail,qq_Num,native_Place,nationality,\n" +
            "            residence,cn_Residence,present_Industry,com_Name,position,education,health,registrant,\n" +
            "            photo,reg_Date,remarks,del) values(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13," +
            "?14,?15,?16,?17,?18,?19,?20,?21,?22,?23,?24,?25,?26,?27,?28,'0')",nativeQuery = true)
    int insertHQ(String chName, String usedName, String pyName, String sex, String ethnicity, String passportNo,
                 Date dateExpriy, Date dateBirth, String idNum, String oTel, String cnTel, String cnTe2,
                 String wechat, String mail, String qqNum, String nativePlace, String nationality,
                 String residence, String cnResidence, String presentIndustry, String comName, String position,
                 String education, String health, Long registrant, String photo, Date regDate, String remarks);

    HQBean save(HQBean hqBean);

    /**
     * deleteHQ
     * @param hqid
     * return ture or false
     * */
    @Modifying
    @Query(value = "update hq set del = '1' where hq_id = ?1 ",nativeQuery = true)
    int deleteHQ(long hqid);


    @Modifying
    @Query(value = "update hq set ch_Name = ?1, used_Name = ?2, py_Name = ?3, sex = ?4, ethnicity = ?5, passport_No = ?6," +
            "date_Expriy = ?7,date_Birth = ?8,id_Num = ?9,o_Tel = ?10,cn_Tel = ?11,cn_Te2 = ?12,wechat = ?13,mail = ?14,qq_Num = ?15,native_Place = ?16,nationality = ?17,\n" +
            "residence = ?18,cn_Residence = ?19,present_Industry = ?20,com_Name = ?21,position = ?22,education = ?23,health = ?24,registrant = ?25,\n" +
            "photo = ?26,reg_Date = ?27,remarks = ?28 where hq_id = ?29 ", nativeQuery = true)
    int modifyHQ(String chName, String usedName, String pyName, String sex, String ethnicity, String passportNo,
                 Date dateExpriy, Date dateBirth, String idNum, String oTel, String cnTel, String cnTe2,
                 String wechat, String mail, String qqNum, String nativePlace, String nationality,
                 String residence, String cnResidence, String presentIndustry, String comName, String position,
                 String education, String health, Long registrant, String photo, Date regDate, String remarks, long hqid);


    @Query(value = "SELECT * FROM hq WHERE passport_no = ?1 and del='0'",nativeQuery = true)
    HQBean loadByPassport(String passport_no);

    @Query(value = "SELECT * FROM hq WHERE hq_id = ?1",nativeQuery = true)
    HQBean getById(Long hq_id);
}
