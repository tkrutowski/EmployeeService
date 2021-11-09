package net.focik.hr.employee.domain.salary;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.addition.AdditionFacade;
import net.focik.hr.employee.domain.advance.AdvanceFacade;
import net.focik.hr.employee.domain.loans.LoanQueryFacade;
import net.focik.hr.employee.domain.workTimeRecords.WorkTimeFacade;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
class SalaryFactory {
    private AdvanceFacade advanceFacade;
    private AdditionFacade additionFacade;
    private LoanQueryFacade loanQueryFacade;
    private WorkTimeFacade workTimeFacade;
    private int hoursToWork;
private SalaryService salaryService;

    Salary createSalary(Employee employee, LocalDate date) {

        salaryService.calculateMinutes(workTimeFacade.getAllByEmployeeIdAndDate(employee.getId(), date));

        Salary salary = Salary.builder()
                .advancesSum(advanceFacade.getAdvancesSumByIdEmployeeAndDate(employee.getId(), date))
                .additionsSum(additionFacade.getAdditionsSumByIdEmployeeAndDate(employee.getId(), date))
                .loanInstallmentSum(loanQueryFacade.getInstallmentLoansSumByIdEmployeeAndDate(employee.getId(), date))
                .dayOffMinutesPay(salaryService.getDayOffMinutesPay())
                .illnessMinutes80(salaryService.getIllnessMinutes80())
                .workRegularMinutes(salaryService.getWorkRegularMinutes())
                .workOvertime50Minutes(salaryService.getWorkOvertime50Minutes())
                .workOvertime100Minutes(salaryService.getWorkOvertime100Minutes())

                .forDayOff(salaryService.calculateForDayOff(employee.getRateRegularValueByDate(date), employee.getRateRegularTypeByDate(date), hoursToWork))
                .forIllness80(salaryService.calculateForIllness80(employee.getRateRegularValueByDate(date), employee.getRateRegularTypeByDate(date), hoursToWork))
                .forIllness100(salaryService.calculateForIllness80(employee.getRateRegularValueByDate(date), employee.getRateRegularTypeByDate(date), hoursToWork))
                .forRegularRate(salaryService.calculateForWorkRegular(employee.getRateRegularValueByDate(date), employee.getRateRegularTypeByDate(date), hoursToWork))
                .forOvertime50(salaryService.calculateForWorkOvertime50(employee.getRateOvertimeValueByDate(date)))
                .forOvertime100(salaryService.calculateForWorkOvertime50(employee.getRateOvertimeValueByDate(date)))
                .build();
        return salary;
    }

}
