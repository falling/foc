package zj.gov.foc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.repository.HQRepository;
import zj.gov.foc.repository.LXRepository;
import zj.gov.foc.repository.QJRepository;

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

    public long[] statistics() {
        long[] result = new long[3];
        result[0] = hqRepository.countHQ();
        result[1] = lxRepository.countLX();
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

        Long maleNumber = sexList.stream().filter(e->e.equals("男")).count();
        Long femaleNumber = sexList.stream().filter(e->e.equals("女")).count();

        result.put("男",maleNumber);
        result.put("女",femaleNumber);

        return result;

    }

    public HashMap<Object, Object> HQCountry() {
        HashMap<Object, Object> hqResult = new HashMap<>();
        hqRepository.groupByCountry().forEach(e-> hqResult.put(e[0],e[1]));
        return hqResult;
    }

    public HashMap<Object, Object> LXCountry() {
        HashMap<Object, Object> lxResult = new HashMap<>();
        lxRepository.groupByCountry().forEach(e-> lxResult.put(e[0],e[1]));
        return lxResult;
    }

    public HashMap<Object, Object> QJHQCountry() {
        HashMap<Object, Object> lxResult = new HashMap<>();
        qjRepository.groupByCountry("qj_hq").forEach(e-> lxResult.put(e[0],e[1]));
        return lxResult;
    }

    public HashMap<Object, Object> QJLXCountry() {
        HashMap<Object, Object> lxResult = new HashMap<>();
        qjRepository.groupByCountry("qj_lx").forEach(e-> lxResult.put(e[0],e[1]));
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
}
