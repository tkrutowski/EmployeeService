package net.focik.hr.employee.domain.port.primary;

import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface AddNewRateUseCase {
    RateRegular addRateRegular(int employeeId, RateRegular rateRegular);
    RateOvertime addRateOvertime(int employeeId, RateOvertime rateOvertime);
}
