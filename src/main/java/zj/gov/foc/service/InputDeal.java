package zj.gov.foc.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputDeal {
    public  boolean isChinesecCharacters(String str){
        Pattern pattern = Pattern.compile("^[\u4E00-\u9FFF]{2,20}+$");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
    public  boolean isPY(String str){
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    public  boolean isCTel(String str){
        Pattern pattern = Pattern.compile("^(\\d{11})|(\\d{7,8})|(\\d{3,4})[/-](\\d{7,8})&");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    public  boolean isData(String str){
        Pattern pattern = Pattern.compile("^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[1-2]\\d{1}|3[0-1])");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
    public  boolean isIDNum(String str){
        Pattern pattern = Pattern.compile("(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)\n" +
                "\n");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
    public  boolean isPassportNO(String str){
        Pattern pattern = Pattern.compile("^1[45][0-9]{7}|([P|p|S|s]\\d{7})|([S|s|G|g]\\d{8})|([Gg|Tt|Ss|Ll|Qq|Dd|Aa|Ff]\\d{8})|([H|h|M|m]\\d{8,10})$");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }



}

