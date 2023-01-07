package net.focik.hr.employee.application;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.EmployeeFacade;
import net.focik.hr.employee.domain.salary.Salary;
import net.focik.hr.employee.domain.salary.SalaryFacade;
import net.focik.hr.employee.domain.salary.port.primary.CalculateSalaryUseCase;
import net.focik.hr.employee.domain.salary.port.primary.PrintSalaryUseCase;
import net.focik.hr.employee.domain.share.PrintTypePdf;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class SalaryApiService implements CalculateSalaryUseCase, PrintSalaryUseCase {

    private final EmployeeFacade employeeFacade;
    private final SalaryFacade salaryFacade;

    @Override
    public Salary calculate(int idEmployee, LocalDate salaryDate) {
        Employee employee = employeeFacade.getEmployee(idEmployee);

        return salaryFacade.calculateSalary(employee, salaryDate);
    }

    @Override
    public String createPdf(List<Integer> idEmployees, LocalDate date, PrintTypePdf printTypePdf) {
        return salaryFacade.createPdf(idEmployees, date, printTypePdf);
    }
}