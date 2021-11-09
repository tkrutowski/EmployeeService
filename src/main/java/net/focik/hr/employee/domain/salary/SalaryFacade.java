package net.focik.hr.employee.domain.salary;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@Component
public class SalaryFacade {

    SalaryFactory salaryFactory;

    public Salary calculateSalary(Employee employee, LocalDate date){
        return salaryFactory.createSalary(employee, date);
    }

}
