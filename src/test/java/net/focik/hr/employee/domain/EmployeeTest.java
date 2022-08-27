package net.focik.hr.employee.domain;

import net.focik.hr.employee.domain.share.RateType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

        List<RateRegular> rateRegularSet = new ArrayList<>();

    @BeforeEach
    void setUp() {

        rateRegularSet.add(new RateRegular(1, RateType.PER_MONTH,LocalDate.of(2021,10,2),new BigDecimal("4000")));
        rateRegularSet.add(new RateRegular(2, RateType.PER_HOUR,LocalDate.of(2018,10,2),new BigDecimal("5000")));
        rateRegularSet.add(new RateRegular(3, RateType.PER_MONTH,LocalDate.of(2019,5,2),new BigDecimal("2000")));
        rateRegularSet.add(new RateRegular(3, RateType.PER_MONTH,LocalDate.of(2020,3,2),new BigDecimal("3000")));

    }

    @Test
    void should_return_RateRegularValueByDate() {
        //given
        LocalDate date = LocalDate.of(2020,5,4);
        Employee employee = Employee.builder().rateRegular(rateRegularSet).build();

        //when
        BigDecimal result = employee.getRateRegularByDate(date).getRateValue();

        //then
        assertEquals(3000d, result.doubleValue());
    }

    @Test
    void should_return_Zero_RateRegularValueByDate() {
        //given
        LocalDate date = LocalDate.of(2017,5,4);
        Employee employee = Employee.builder().rateRegular(rateRegularSet).build();

        //when
        BigDecimal result = employee.getRateRegularByDate(date).getRateValue();

        //then
        assertEquals(0d, result.doubleValue());
    }
    @Test
    void should_return_last_RateRegularValueByDate() {
        //given
        LocalDate date = LocalDate.of(2025,5,4);
        Employee employee = Employee.builder().rateRegular(rateRegularSet).build();

        //when
        BigDecimal result = employee.getRateRegularByDate(date).getRateValue();

        //then
        assertEquals(4000d, result.doubleValue());
    }

    @Test
    void should_return_LatestRateRegularDateFrom() {
        //given
        LocalDate date = LocalDate.of(2021, 10, 2);
        Employee employee = Employee.builder().rateRegular(rateRegularSet).build();

        //when
        LocalDate result = employee.getLatestRateRegular().getDateFrom();

        //then
        assertEquals(date, result);
    }

    @Test
    void should_return_LatestRateRegularValue() {
        //given
        Employee employee = Employee.builder().rateRegular(rateRegularSet).build();

        //when
        BigDecimal result = employee.getLatestRateRegular().getRateValue();

        //then
        assertEquals(4000d, result.doubleValue());
    }

    @Test
    void should_return_LatestRateRegularType() {
        //given
        Employee employee = Employee.builder().rateRegular(rateRegularSet).build();

        //when
        RateType result = employee.getLatestRateRegular().getRateType();

        //then
        assertEquals(RateType.PER_MONTH, result);
    }


    @Test
    void should_return_null_when_date_is_before() {
        //given
        LocalDate date = LocalDate.of(2001, 10, 2);
        Employee employee = Employee.builder().rateRegular(rateRegularSet).build();

        //when
        RateType result = employee.getRateRegularByDate(date).getRateType();

        //then
        assertEquals(null, result);
    }

    @Test
    void getRateRegularTypeByDate() {
    }

    @Test
    void getLatestRateOvertimeDateFrom() {
    }

    @Test
    void getLatestRateOvertimeValue() {
    }

    @Test
    void getRateOvertimeValueByDate() {
    }
}