package zj.gov.foc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.repository.HQRepository;
import zj.gov.foc.util.InputDeal;
import zj.gov.foc.vo.HQVO;

import javax.transaction.Transactional;


@Service
public class HQService {

    @Autowired
    HQRepository hqRepository ;

    @Transactional
    public HQVO addHQ(HQVO hqvo){

        if(!InputDeal.isChineseCharacters(hqvo.getChName())){
            hqvo.setInfo("中文名字为2-20个汉字");
            return hqvo;
        }
        else if (!InputDeal.isPY(hqvo.getPyName())){
            hqvo.setInfo("拼音为1-50个字母");
            return hqvo;
        }
        else  if (!InputDeal.isPassportNO(hqvo.getPassportNo())){
            hqvo.setInfo("护照号格式不正确");
            return hqvo;
        }
        else  if (!InputDeal.isIDNum(hqvo.getIdNum())){
            hqvo.setInfo("身份证格式不正确");
            return hqvo;
        }
        int rs= hqRepository.insertHQ( hqvo.getChName(), hqvo.getUsedName(), hqvo.getPyName(), hqvo.getSex(), hqvo.getEthnicity(), hqvo.getPassportNo(),
                 hqvo.getDateExpriy(), hqvo.getDateBirth(),hqvo.getIdNum(), hqvo.getoTel(), hqvo.getCnTel(), hqvo.getCnTe2(), hqvo.getWechat(), hqvo.getMail(),
                hqvo.getQqNum(), hqvo.getNativePlace(), hqvo.getNationality(), hqvo.getResidence(), hqvo.getCnResidence(), hqvo.getPresentIndustry(),
                hqvo.getComName(), hqvo.getPosition(), hqvo.getEducation(), hqvo.getHealth(), hqvo.getRegistrant(), hqvo.getPhoto(), hqvo.getRegDate(),hqvo.getRemarks());
        if(rs > 0){
            hqvo.setInfo("创建成功");
        }else{
            hqvo.setInfo("创建失败");
        }
        return hqvo;
    }

    @Transactional
    public int  modifyHQ(HQVO hqvo){

        int rs = hqRepository.modifyHQ(hqvo.getChName(), hqvo.getUsedName(), hqvo.getPyName(), hqvo.getSex(), hqvo.getEthnicity(), hqvo.getPassportNo(),
                hqvo.getDateExpriy(), hqvo.getDateBirth(), hqvo.getIdNum(), hqvo.getoTel(), hqvo.getCnTel(), hqvo.getCnTe2(), hqvo.getWechat(), hqvo.getMail(),
                hqvo.getQqNum(), hqvo.getNativePlace(), hqvo.getNationality(), hqvo.getResidence(), hqvo.getCnResidence(), hqvo.getPresentIndustry(),
                hqvo.getComName(), hqvo.getPosition(), hqvo.getEducation(), hqvo.getHealth(), hqvo.getRegistrant(), hqvo.getPhoto(), hqvo.getRegDate(), hqvo.getRemarks(),hqvo.getHqId());
        return rs;

    }
    @Transactional
    public int  deleteHQ(long hqid){
        return hqRepository.deleteHQ(hqid);
    }


}
