package net.focik.hr.employee.domain.salary;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.EmployeeFacade;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.PrintTypePdf;
import net.focik.hr.employee.domain.worktimerecords.DaysToWork;
import net.focik.hr.employee.domain.worktimerecords.WorkTimeFacade;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class SalaryFacade {


    private final WorkTimeFacade workTimeFacade;
    private final EmployeeFacade employeeFacade;
    private final SalaryService salaryService;

    public Salary calculateSalary(Employee employee, LocalDate date) {
        DaysToWork daysToWorkByDate = workTimeFacade.getDaysToWorkByDate(date.getYear(), date.getMonth().getValue());
        return salaryService.createSalary(employee, date, daysToWorkByDate);
    }

    public String createPdf(List<Integer> idEmployees, LocalDate date, PrintTypePdf printTypePdf) {
        DaysToWork daysToWorkByDate = workTimeFacade.getDaysToWorkByDate(date.getYear(), date.getMonth().getValue());
        List<Employee> employees = employeeFacade.getAllBy(EmploymentStatus.HIRED, true, null).stream()
                .filter(dto -> idEmployees.contains(dto.getId()))
                .collect(Collectors.toList());
        return salaryService.createTimeSheetPdf(employees, date, daysToWorkByDate);
    }
}