package net.focik.hr.employee.domain.port.secondary;


import net.focik.hr.employee.domain.RateOvertime;

import java.util.List;


public interface RateOvertimeRepository {
    RateOvertime add(RateOvertime rate, Integer idEmployee);

    List<RateOvertime> findRateOvertimeEmployeeId(Integer idEmployee);

    void deleteRateOvertimeByIdEmployee(Integer idEmployee);

    void deleteRateOvertimeById(Integer id);
}