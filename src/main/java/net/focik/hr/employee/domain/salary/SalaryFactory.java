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
                .dayOffMinutesFree(salaryService.getDayOffMinutesFree())
                .illnessMinutes100(salaryService.getIllnessMinutes100())
                .illnessMinutes80(salaryService.getIllnessMinutes80())
                .workRegularMinutes(salaryService.getWorkRegularMinutes())
                .workOvertime50Minutes(salaryService.getWorkOvertime50Minutes())
                .workOvertime100Minutes(salaryService.getWorkOvertime100Minutes())

                .forDayOff(salaryService.calculateForDayOff(employee.getRateRegularByDate(date).getRateValue(), employee.getRateRegularByDate(date).getRateType(), daysToWork.getHoursToWork()))
                .forIllness80(salaryService.calculateForIllness80(employee.getRateRegularByDate(date).getRateValue(), employee.getRateRegularByDate(date).getRateType(), daysToWork.getHoursToWork()))
                .forIllness100(salaryService.calculateForIllness100(employee.getRateRegularByDate(date).getRateValue(), employee.getRateRegularByDate(date).getRateType(), daysToWork.getHoursToWork()))
                .forRegularRate(salaryService.calculateForWorkRegular(employee.getRateRegularByDate(date).getRateValue(), employee.getRateRegularByDate(date).getRateType(), daysToWork.getHoursToWork()))
                .forOvertime50(salaryService.calculateForWorkOvertime50(employee.getRateOvertimeByDate(date).getRateValue()))
                .forOvertime100(salaryService.calculateForWorkOvertime100(employee.getRateOvertimeByDate(date).getRateValue()))
                .build();
        return salary;
    }

}
