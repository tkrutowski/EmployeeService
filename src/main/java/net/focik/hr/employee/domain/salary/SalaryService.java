package net.focik.hr.employee.domain.salary;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.worktimerecords.DaysToWork;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
class SalaryService {
    private final SalaryFactory salaryFactory;

    String createTimeSheetPdf(List<Employee> employeeList, LocalDate date, DaysToWork daysToWork) {
        Map<String, Salary> salaryMap = new HashMap<>();

        employeeList.forEach(employee -> salaryMap.put(employee.getFirstName() + " " + employee.getLastName(),
                createSalary(employee, date, daysToWork)));

        return SalarySheetPdf.createPdf(date, salaryMap, daysToWork);
    }

    Salary createSalary(Employee employee, LocalDate date, DaysToWork daysToWorkByDate) {
        return salaryFactory.createSalary(employee, date, daysToWorkByDate);
    }
}