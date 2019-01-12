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

    public long[] statistics(String manager_area) {
        long[] result = new long[3];
        manager_area = manager_area + "%";
        result[0] = hqRepository.countHQ(manager_area);
        result[1] = lxRepository.countLX(manager_area);
        return result;
    }

    public HashMap<String, Long> total(String manager_area) {
        HashMap<String, Long> result = new HashMap<>();
        result.put("华侨", hqRepository.countHQ(manager_area));
        result.put("留学", lxRepository.countLX(manager_area));
        result.put("侨眷", qjRepository.count("qj_hq",manager_area));
        result.put("留学生家属", qjRepository.count("qj_lx",manager_area));
        return result;
    }

    public HashMap<String, Long> sex(String manager_area) {
        HashMap<String, Long> result = new HashMap<>();
        List<String> sexList = hqRepository.getAllSex(manager_area);
        sexList.addAll(lxRepository.getAllSex(manager_area));
        sexList.addAll(qjRepository.getAllSex(manager_area));

        Long maleNumber = sexList.parallelStream().filter(e->e.equals("男")).count();
        Long femaleNumber = sexList.parallelStream().filter(e->e.equals("女")).count();

        result.put("男",maleNumber);
        result.put("女",femaleNumber);

        return result;

    }

    public HashMap<Object, Object> HQCountry(String manager_area) {
        HashMap<Object, Object> hqResult = new HashMap<>();
        hqRepository.groupByCountry(manager_area).forEach(e-> hqResult.put(e[0],e[1]));
        return hqResult;
    }

    public HashMap<Object, Object> LXCountry(String manager_area) {
        HashMap<Object, Object> lxResult = new HashMap<>();
        lxRepository.groupByCountry(manager_area).forEach(e-> lxResult.put(e[0],e[1]));
        return lxResult;
    }

    public HashMap<Object, Object> QJHQCountry(String manager_area) {
        HashMap<Object, Object> lxResult = new HashMap<>();
        qjRepository.groupByCountry("qj_hq",manager_area).forEach(e-> lxResult.put(e[0],e[1]));
        return lxResult;
    }

    public HashMap<Object, Object> QJLXCountry(String manager_area) {
        HashMap<Object, Object> lxResult = new HashMap<>();
        qjRepository.groupByCountry("qj_lx",manager_area).forEach(e-> lxResult.put(e[0],e[1]));
        return lxResult;
    }

    public HashMap<Object, Object> NativePlace(String manager_area) {
        HashMap<Object,Object> result = new HashMap<>();
        HashMap<Object,Object> hqlist = new HashMap<>();
        HashMap<Object,Object> lxlist = new HashMap<>();
        hqRepository.groupByNativePlace(manager_area).forEach(e->hqlist.put(e[0],e[1]));
        lxRepository.groupByNativePlace(manager_area).forEach(e->lxlist.put(e[0],e[1]));
        result.put("hq",hqlist);
        result.put("lx",lxlist);
        return result;
    }
}
