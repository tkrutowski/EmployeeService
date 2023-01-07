package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.SalaryDto;
import net.focik.hr.employee.domain.salary.Salary;
import net.focik.hr.utils.prints.ConvertTimeUtil;
import net.focik.hr.utils.prints.MoneyUtils;
import org.springframework.stereotype.Component;

@Component
public class ApiSalaryMapper {

    public SalaryDto toDto(Salary s) {
        return SalaryDto.builder()
                .dayOffWorkTimePay(ConvertTimeUtil.mapMinutesToTime(s.getDayOffMinutesPay()))
                .dayOffWorkTimeFree(ConvertTimeUtil.mapMinutesToTime(s.getDayOffMinutesFree()))
                .illnessWorkTime80(ConvertTimeUtil.mapMinutesToTime(s.getIllnessMinutes80()))
                .illnessWorkTime100(ConvertTimeUtil.mapMinutesToTime(s.getIllnessMinutes100()))
                .workRegularWorkTime(ConvertTimeUtil.mapMinutesToTime(s.getWorkRegularMinutes()))
                .workOvertimeWorkTime50(ConvertTimeUtil.mapMinutesToTime(s.getWorkOvertime50Minutes()))
                .workOvertimeWorkTime100(ConvertTimeUtil.mapMinutesToTime(s.getWorkOvertime100Minutes()))
                .workTimeAll(s.getWorkTimeAll())

                .forRegularRate(MoneyUtils.mapMoneyToString(s.getForRegularRate()))
                .forOvertime50(MoneyUtils.mapMoneyToString(s.getForOvertime50()))
                .forOvertime100(MoneyUtils.mapMoneyToString(s.getForOvertime100()))
                .forDayOff(MoneyUtils.mapMoneyToString(s.getForDayOff()))
                .forIllness80(MoneyUtils.mapMoneyToString(s.getForIllness80()))
                .forIllness100(MoneyUtils.mapMoneyToString(s.getForIllness100()))
                .forAll(MoneyUtils.mapMoneyToString(s.getAmountForAllWorktime()))

                .hoursToWork(s.getDaysToWork().getHoursToWork().toString())
                .daysOffLeft(String.valueOf(s.getDaysOffLeft()))
                .loansToPay(MoneyUtils.mapMoneyToString(s.getLoansToPay()))
                .advancesSum(MoneyUtils.mapMoneyToString(s.getAdvancesSum()))
                .additionsSum(MoneyUtils.mapMoneyToString(s.getAdditionsSum()))
                .loanInstallmentSum(MoneyUtils.mapMoneyToString(s.getLoanInstallmentSum()))
                .rateRegular(s.getRateRegular().toString())
                .rateOvertime(s.getRateOvertime().toString())
                .paycheckAmount(MoneyUtils.mapMoneyToString(s.getPaycheckAmount()))
                .build();
    }
}