package net.focik.hr.employee.application;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.EmployeeFacade;
import net.focik.hr.employee.domain.salary.port.primary.CalculateSalaryUseCase;
import net.focik.hr.employee.domain.salary.Salary;
import net.focik.hr.employee.domain.salary.SalaryFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class CalculateSalaryApiService implements CalculateSalaryUseCase {

    private final EmployeeFacade employeeFacade;
    private final SalaryFacade salaryFacade;

    @Override
    public Salary calculate(int idEmployee, LocalDate salaryDate) {
        Employee employee = employeeFacade.getEmployee(idEmployee);

        return salaryFacade.calculateSalary(employee, salaryDate);
    }
}