package net.focik.hr.employee.domain.port.primary;

import net.focik.hr.employee.domain.salary.Salary;

import java.time.LocalDate;

public interface CalculateSalaryUseCase {
    Salary calculate(int idEmployee, LocalDate salaryDate);
}
