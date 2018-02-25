package zj.gov.foc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zj.gov.foc.repository.HQRepository;
import zj.gov.foc.repository.LXRepository;

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

    public long[] statistics(){
        long[] result = new long[3];
        result[0] = hqRepository.countHQ();
        result[1] = lxRepository.countLX();
        long countryNumber = hqRepository.countCountry();
        countryNumber += lxRepository.countCountry();
        result[2] = countryNumber;// country
        return result;
    }

}
