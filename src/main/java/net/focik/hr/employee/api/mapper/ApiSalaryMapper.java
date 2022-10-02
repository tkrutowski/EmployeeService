package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.SalaryDto;
import net.focik.hr.employee.domain.salary.Salary;
import net.focik.hr.employee.domain.share.RateType;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.format.AmountFormatParams;
import org.springframework.stereotype.Component;

import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.util.Locale;

@Component
public class ApiSalaryMapper {

    public SalaryDto toDto(Salary s){
        return SalaryDto.builder()
                .dayOffWorkTimePay(mapMinutesToTime(s.getDayOffMinutesPay()))
                .dayOffWorkTimeFree(mapMinutesToTime(s.getDayOffMinutesFree()))
                .illnessWorkTime80(mapMinutesToTime(s.getIllnessMinutes80()))
                .illnessWorkTime100(mapMinutesToTime(s.getIllnessMinutes100()))
                .workRegularWorkTime(mapMinutesToTime(s.getWorkRegularMinutes()))
                .workOvertimeWorkTime50(mapMinutesToTime(s.getWorkOvertime50Minutes()))
                .workOvertimeWorkTime100(mapMinutesToTime(s.getWorkOvertime100Minutes()))
                .workTimeAll(s.getWorkTimeAll())

                .forRegularRate(mapMoneyToString(s.getForRegularRate()))
                .forOvertime50(mapMoneyToString(s.getForOvertime50()))
                .forOvertime100(mapMoneyToString(s.getForOvertime100()))
                .forDayOff(mapMoneyToString(s.getForDayOff()))
                .forIllness80(mapMoneyToString(s.getForIllness80()))
                .forIllness100(mapMoneyToString(s.getForIllness100()))
                .forAll(mapMoneyToString(s.getAmountForAllWorktime()))

                .hoursToWork(s.getDaysToWork().getHoursToWork().toString())
                .daysOffLeft(String.valueOf(s.getDaysOffLeft()))
                .loansToPay(mapMoneyToString(s.getLoansToPay()))
                .advancesSum(mapMoneyToString(s.getAdvancesSum()))
                .additionsSum(mapMoneyToString(s.getAdditionsSum()))
                .loanInstallmentSum(mapMoneyToString(s.getLoanInstallmentSum()))
                .rateRegular(String.format("%.2f %s" ,s.getRateRegular().getRateValue(),
                                s.getRateRegular().getRateType().equals(RateType.PER_HOUR) ? "zł/h" : "zł/mc"))
                .rateOvertime(String.format("%.2f", s.getRateOvertime().getRateValue()))
                .paycheckAmount(mapMoneyToString(s.getPaycheckAmount()))
                .build();
    }

    private String mapMinutesToTime(Integer dayOffMinutesPay) {
        return dayOffMinutesPay / 60 +
                ":" +
                String.format("%02d", dayOffMinutesPay % 60);
    }

    private String mapMoneyToString(Money money) {
        MonetaryAmountFormat amountFormat = MonetaryFormats.getAmountFormat(
                AmountFormatQueryBuilder.of(new Locale("pl", "PL"))
//                .set(AmountFormatParams.PATTERN, "###,###.## ¤")
                .set(AmountFormatParams.PATTERN, "###,##0.00 zł")
                .build());

        return amountFormat.format(money);
    }
}