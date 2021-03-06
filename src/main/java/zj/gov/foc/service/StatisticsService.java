package zj.gov.foc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.repository.HQRepository;
import zj.gov.foc.repository.LXRepository;
import zj.gov.foc.repository.QJRepository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User: falling
 * Date: 2018/2/25
 * Time: 下午7:13
 * Description:
 */
@Service
public class StatisticsService {
    @Autowired
    LXRepository lxRepository;

    @Autowired
    HQRepository hqRepository;

    @Autowired
    QJRepository qjRepository;

    public long[] statistics(String manager_area) {
        long[] result = new long[3];
        manager_area = manager_area + "%";
        result[0] = hqRepository.countHQ(manager_area);
        result[1] = lxRepository.countLX(manager_area);
        return result;
    }

    public HashMap<String, Long> total() {
        HashMap<String, Long> result = new HashMap<>();
        result.put("华侨", hqRepository.countHQ());
        result.put("留学", lxRepository.countLX());
        result.put("侨眷", qjRepository.count("qj_hq"));
        result.put("留学生家属", qjRepository.count("qj_lx"));
        return result;
    }

    public HashMap<String, Long> sex() {
        HashMap<String, Long> result = new HashMap<>();
        List<String> sexList = hqRepository.getAllSex();
        sexList.addAll(lxRepository.getAllSex());
        sexList.addAll(qjRepository.getAllSex());

        Long maleNumber = sexList.parallelStream().filter(e->e.equals("男")).count();
        Long femaleNumber = sexList.parallelStream().filter(e->e.equals("女")).count();

        result.put("男",maleNumber);
        result.put("女",femaleNumber);

        return result;

    }

    public HashMap<Object, Long> HQCountry() {
        HashMap<Object, Long> hqResult = new HashMap<>();
        hqRepository.groupByCountry().forEach(e-> hqResult.put(e[0],((BigInteger) e[1]).longValue()));
        return hqResult;
    }

    public HashMap<Object, Long> LXCountry() {
        HashMap<Object, Long> lxResult = new HashMap<>();
        lxRepository.groupByCountry().forEach(e-> lxResult.put(e[0],((BigInteger) e[1]).longValue()));
        return lxResult;
    }

    public HashMap<Object, Long> QJHQCountry() {
        HashMap<Object, Long> lxResult = new HashMap<>();
        qjRepository.groupByCountry("qj_hq").forEach(e-> lxResult.put(e[0],((BigInteger) e[1]).longValue()));
        return lxResult;
    }

    public HashMap<Object, Long> QJLXCountry() {
        HashMap<Object, Long> lxResult = new HashMap<>();
        qjRepository.groupByCountry("qj_lx").forEach(e-> lxResult.put(e[0],((BigInteger) e[1]).longValue()));
        return lxResult;
    }

    public HashMap<Object, Object> NativePlace() {
        HashMap<Object,Object> result = new HashMap<>();
        HashMap<Object,Object> hqlist = new HashMap<>();
        HashMap<Object,Object> lxlist = new HashMap<>();
        hqRepository.groupByNativePlace().forEach(e->hqlist.put(e[0],e[1]));
        lxRepository.groupByNativePlace().forEach(e->lxlist.put(e[0],e[1]));
        result.put("hq",hqlist);
        result.put("lx",lxlist);
        return result;
    }

    public HashMap<Object, Long> getHQAreaCount(String area) {
        HashMap<Object, Long> result = new HashMap<>();
        area = getManagerArea(area);
        hqRepository.groupByManagerArea(area).forEach(e-> result.put(e[0], ((BigInteger) e[1]).longValue()));
        return result;
    }

    public HashMap<Object, Long> getLXAreaCount(String area) {
        HashMap<Object, Long> result = new HashMap<>();
        area = getManagerArea(area);
        lxRepository.groupByManagerArea(area).forEach(e-> result.put(e[0], ((BigInteger) e[1]).longValue()));
        return result;
    }

    public HashMap<Object, Long> getQJLXAreaCount(String area) {
        HashMap<Object, Long> result = new HashMap<>();
        area = getManagerArea(area);
        qjRepository.groupByManagerArea("qj_lx",area).forEach(e-> result.put(e[0],((BigInteger) e[1]).longValue()));
        return result;
    }

    public HashMap<Object, Long> getQJHQAreaCount(String area) {
        HashMap<Object, Long> result = new HashMap<>();
        area = getManagerArea(area);
        qjRepository.groupByManagerArea("qj_hq",area).forEach(e-> result.put(e[0],((BigInteger) e[1]).longValue()));
        return result;
    }

    private String getManagerArea(String area){
        if (area.equals("浙江省")){
            area = area + "%";
        }else {
            area = "浙江省/"+area + "%";
        }

        return area;
    }
}
