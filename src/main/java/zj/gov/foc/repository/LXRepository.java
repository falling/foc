package zj.gov.foc.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import zj.gov.foc.po.LXBean;

import java.util.Date;

public interface LXRepository extends Repository<LXBean, Integer> {


}
