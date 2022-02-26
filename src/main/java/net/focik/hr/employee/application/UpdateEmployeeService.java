package net.focik.hr.employee.application;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.EmployeeCommandFacade;
import net.focik.hr.employee.domain.port.primary.AddNewEmployeeUseCase;
import net.focik.hr.employee.domain.port.primary.AddNewRateUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@Service
public class UpdateEmployeeService implements AddNewRateUseCase {
    EmployeeCommandFacade commandFacade;


    @Override
    public void changeRateRegular(int idEmployee, LocalDate fromDate, BigDecimal rateValue) {
        //pobrać employee po id

        //sprawdzić czy value z takę datą już istnieje

        //jeżeli tak to aktualizacja

        //jeżeli nie to nowy

    }
}
