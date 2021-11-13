package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.SalaryDto;
import net.focik.hr.employee.domain.salary.Salary;
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
        SalaryDto dto = SalaryDto.builder()
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

                .advancesSum(mapMoneyToString(s.getAdvancesSum()))
                .additionsSum(mapMoneyToString(s.getAdditionsSum()))
                .loanInstallmentSum(mapMoneyToString(s.getLoanInstallmentSum()))
                .build();
        return dto;
    }

    private String mapMinutesToTime(Integer dayOffMinutesPay) {
        StringBuilder result = new StringBuilder();
        result.append(dayOffMinutesPay/60)
                .append(":")
                .append(String.format("%02d", dayOffMinutesPay%60));
        return result.toString();
    }

    private String mapMoneyToString(Money money) {
        MonetaryAmountFormat amountFormat = MonetaryFormats.getAmountFormat(
                AmountFormatQueryBuilder.of(new Locale("pl", "PL"))
//                .set(AmountFormatParams.PATTERN, "###,###.## ¤")
                .set(AmountFormatParams.PATTERN, "###,###.00 zł")
                .build());

        return amountFormat.format(money);
    }
}
