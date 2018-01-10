package zj.gov.foc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.po.HQBean;
import zj.gov.foc.repository.HQRepository;
import zj.gov.foc.util.InputDeal;
import zj.gov.foc.vo.HQVO;


import java.sql.Timestamp;



@Service
public class HQService {

    @Autowired
    HQRepository hqRepository;


    public HQVO addHQ(String chName, String usedName, String pyName, String sex, String ethnicity, String passportNo,
                      Timestamp dateExpriy, Timestamp dateBirth, String idNum, String oTel, String cnTel, String cnTe2,
                      String wechat, String mail, String qqNum, String nativePlace, String nationality,
                      String residence, String cnResidence, String presentIndustry, String comName, String position,
                      String education, String health, String registrant, String photo, Timestamp regDate, String remarks){

        HQVO hqvo = new HQVO() ;
        HQBean hqBean = new HQBean();
        if(!InputDeal.isChineseCharacters(chName)){
            hqvo.setWarning("中文名字为2-20个汉字");
            return hqvo;
        }
        else if (!InputDeal.isPY(pyName)){
            hqvo.setWarning("拼音为1-50个字母");
            return hqvo;
        }
        else  if (!InputDeal.isPassportNO(passportNo)){
            hqvo.setWarning("护照号格式不正确");
            return hqvo;
        }
        else  if (!InputDeal.isIDNum(idNum)){
            hqvo.setWarning("身份证格式不正确");
            return hqvo;
        }
        hqRepository.addHQ( chName, usedName, pyName, sex, ethnicity, passportNo,
                 dateExpriy, dateBirth, idNum, oTel, cnTel, cnTe2, wechat, mail,
                qqNum, nativePlace, nationality, residence, cnResidence, presentIndustry,
                comName, position, education, health, registrant, photo, regDate, remarks);

        hqvo.setWarning("添加成功！");
        return hqvo;
    }


}
