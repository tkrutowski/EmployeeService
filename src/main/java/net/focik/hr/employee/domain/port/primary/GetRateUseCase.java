package net.focik.hr.employee.domain.port.primary;
import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;

import java.util.List;


public interface GetRateUseCase {
    RateRegular getLastRateRegular(int employeeId);
    RateOvertime getLastRateOvertime(int employeeId);
    List<RateRegular> getAllLastRateRegular(int employeeId);
    List<RateOvertime> getAllLastRateOvertime(int employeeId);
}
