package net.focik.hr.employee.domain.salary;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.workTimeRecords.DaysToWork;
import net.focik.hr.employee.domain.workTimeRecords.WorkTimeFacade;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@Component
public class SalaryFacade {

    SalaryFactory salaryFactory;
    WorkTimeFacade workTimeFacade;

    public Salary calculateSalary(Employee employee, LocalDate date){
        DaysToWork daysToWorkByDate = workTimeFacade.getDaysToWorkByDate(date.getYear(), date.getMonth().getValue());
        return salaryFactory.createSalary(employee, date, daysToWorkByDate);
    }

}
