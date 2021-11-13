package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.SalaryDto;
import net.focik.hr.employee.domain.salary.Salary;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiSalaryMapperTest {
    ApiSalaryMapper mapper = new ApiSalaryMapper();

    @Test
    void toDto() {
        //given
        Salary salary = Salary.builder()
                .dayOffMinutesFree(0)
                .dayOffMinutesPay(0)
                .illnessMinutes80(0)
                .illnessMinutes100(480)
                .workRegularMinutes(0)
                .workOvertime50Minutes(483)
                .workOvertime100Minutes(0)
                .forRegularRate(Money.of(10,"PLN"))
                .forOvertime50(Money.of(1123.45,"PLN"))
                .forOvertime100(Money.of(10,"PLN"))
                .forIllness100(Money.of(12456.00,"PLN"))
                .forDayOff(Money.of(10,"PLN"))
                .forIllness80(Money.of(10,"PLN"))
                .advancesSum(Money.of(10,"PLN"))
                .additionsSum(Money.of(10,"PLN"))
                .loanInstallmentSum(Money.of(10,"PLN"))
                .build();

        //when
        SalaryDto salaryDto = mapper.toDto(salary);

        //then
        assertEquals("0:00", salaryDto.getWorkRegularWorkTime());
        assertEquals("8:03", salaryDto.getWorkOvertimeWorkTime50());
        assertEquals("8:00", salaryDto.getIllnessWorkTime100());
//        assertEquals("1 123,45 zł", salaryDto.getForOvertime50());
//        assertEquals("12 456,00 zł", salaryDto.getForIllness100());
    }
}