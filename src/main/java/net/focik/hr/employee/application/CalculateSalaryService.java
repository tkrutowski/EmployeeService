package net.focik.hr.employee.application;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.EmployeeCommandFacade;
import net.focik.hr.employee.domain.port.primary.CalculateSalaryUseCase;
import net.focik.hr.employee.domain.salary.Salary;
import net.focik.hr.employee.domain.salary.SalaryFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class CalculateSalaryService implements CalculateSalaryUseCase {

    EmployeeCommandFacade employeeCommandFacade;
    SalaryFacade salaryFacade;


    @Override
    public Salary calculate(int idEmployee, LocalDate salaryDate) {
        Employee employee = employeeCommandFacade.getEmployee(idEmployee);

        return salaryFacade.calculateSalary(employee, salaryDate);

    }
}
