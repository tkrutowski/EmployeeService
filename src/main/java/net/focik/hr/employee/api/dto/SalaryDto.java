package net.focik.hr.employee.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.javamoney.moneta.Money;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class SalaryDto {
    private String advancesSum;
    private String additionsSum;
    private String loanInstallmentSum;

    private String forRegularRate;
    private String forOvertime50;
    private String forOvertime100;
    private String forDayOff;
    private String forIllness80;
    private String forIllness100;
    private String forAll;
    private String dayOffWorkTimePay;
    private String dayOffWorkTimeFree;
    private String illnessWorkTime80;
    private String illnessWorkTime100;
    private String workRegularWorkTime;
    private String workOvertimeWorkTime50;
    private String workOvertimeWorkTime100;
    private String workTimeAll;

}
