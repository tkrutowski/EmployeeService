package net.focik.hr.employee.domain.port.primary;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface AddNewRateUseCase {
    void changeRateRegular(int idEmployee, LocalDate fromDate, BigDecimal rayeValue);
}
