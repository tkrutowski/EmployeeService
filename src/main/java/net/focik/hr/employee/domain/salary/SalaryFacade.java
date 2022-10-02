package net.focik.hr.employee.domain.salary;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.worktimerecords.DaysToWork;
import net.focik.hr.employee.domain.worktimerecords.WorkTimeFacade;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@Component
public class SalaryFacade {

    private final SalaryFactory salaryFactory;
    private final WorkTimeFacade workTimeFacade;

    public Salary calculateSalary(Employee employee, LocalDate date) {
        DaysToWork daysToWorkByDate = workTimeFacade.getDaysToWorkByDate(date.getYear(), date.getMonth().getValue());
        return salaryFactory.createSalary(employee, date, daysToWorkByDate);
    }
}