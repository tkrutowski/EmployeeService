package net.focik.hr.employee.domain.port.secondary;

//import net.focik.hr.employee.domain.RateOvertime;

import net.focik.hr.employee.domain.RateOvertime;

import java.util.List;
import java.util.Optional;


public interface RateOvertimeRepository {
    Integer add(RateOvertime rate, Integer idEmployee);
//
//    Optional<RateOvertime> findRateOvertimeById(Integer id);
    List<RateOvertime> findRateOvertimeEmployeeId(Integer idEmployee);
}