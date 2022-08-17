package net.focik.hr.employee.domain.port.primary;
import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;

import java.util.List;

//do usunięcia?
public interface UpdateRateUseCase {
    RateRegular updateRateRegular(int employeeId, RateRegular rateRegular);
    RateOvertime updateRateOvertime(int employeeId, RateOvertime rateOvertime);
}
