package net.focik.hr.employee.domain.salary;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.addition.AdditionFacade;
import net.focik.hr.employee.domain.advance.AdvanceFacade;
import net.focik.hr.employee.domain.loans.LoanFacade;
import net.focik.hr.employee.domain.workTimeRecords.DaysToWork;
import net.focik.hr.employee.domain.workTimeRecords.WorkTimeFacade;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
class SalaryFactory {
    private AdvanceFacade advanceFacade;
    private AdditionFacade additionFacade;
    private LoanFacade loanFacade;
    private WorkTimeFacade workTimeFacade;
private SalaryService salaryService;

    Salary createSalary(Employee employee, LocalDate date, DaysToWork daysToWork) {

        salaryService.calculateMinutes(workTimeFacade.getAllByEmployeeIdAndDate(employee.getId(), date));

        Salary salary = Salary.builder()
                .advancesSum(advanceFacade.getAdvancesSumByIdEmployeeAndDate(employee.getId(), date))
                .additionsSum(additionFacade.getAdditionsSumByIdEmployeeAndDate(employee.getId(), date))
                .loanInstallmentSum(loanFacade.getInstallmentLoansSumByIdEmployeeAndDate(employee.getId(), date))
                .dayOffMinutesPay(salaryService.getDayOffMinutesPay())
                .illnessMinutes80(salaryService.getIllnessMinutes80())
                .workRegularMinutes(salaryService.getWorkRegularMinutes())
                .workOvertime50Minutes(salaryService.getWorkOvertime50Minutes())
                .workOvertime100Minutes(salaryService.getWorkOvertime100Minutes())

                .forDayOff(salaryService.calculateForDayOff(employee.getRateRegularValueByDate(date), employee.getRateRegularTypeByDate(date), daysToWork.getHoursToWork()))
                .forIllness80(salaryService.calculateForIllness80(employee.getRateRegularValueByDate(date), employee.getRateRegularTypeByDate(date), daysToWork.getHoursToWork()))
                .forIllness100(salaryService.calculateForIllness80(employee.getRateRegularValueByDate(date), employee.getRateRegularTypeByDate(date), daysToWork.getHoursToWork()))
                .forRegularRate(salaryService.calculateForWorkRegular(employee.getRateRegularValueByDate(date), employee.getRateRegularTypeByDate(date), daysToWork.getHoursToWork()))
                .forOvertime50(salaryService.calculateForWorkOvertime50(employee.getRateOvertimeValueByDate(date)))
                .forOvertime100(salaryService.calculateForWorkOvertime50(employee.getRateOvertimeValueByDate(date)))
                .build();
        return salary;
    }

}
