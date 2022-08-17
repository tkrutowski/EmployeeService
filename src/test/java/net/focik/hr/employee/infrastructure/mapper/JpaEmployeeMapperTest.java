package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.share.WorkTime;
import net.focik.hr.employee.infrastructure.dto.EmployeeDbDto;
import net.focik.hr.employee.infrastructure.dto.RateOvertimeDbDto;
import net.focik.hr.employee.infrastructure.dto.RateRegularDbDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JpaEmployeeMapperTest {

    JpaEmployeeMapper mapper = new JpaEmployeeMapper();

    @Test
    void should_return_size_rates_equals_2() {
        //given
        EmployeeDbDto employeeDto = createEmployeeDto();
        RateRegularDbDto rateRegular1 = new RateRegularDbDto(1, 22, RateType.PER_MONTH, LocalDate.of(2010,5,1), BigDecimal.valueOf(5000));
        RateRegularDbDto rateRegular2 = new RateRegularDbDto(2, 22, RateType.PER_MONTH, LocalDate.of(2015,5,1), BigDecimal.valueOf(6000));
        List<RateRegularDbDto> rateRegularDbDtos = List.of(rateRegular1, rateRegular2);
        RateOvertimeDbDto rateOvertimeDbDto1 = new RateOvertimeDbDto(1,22,LocalDate.of(2010,5,1), BigDecimal.valueOf(25.5));
        RateOvertimeDbDto rateOvertimeDbDto2 = new RateOvertimeDbDto(2,22,LocalDate.of(2015,5,1), BigDecimal.valueOf(35.5));
        List<RateOvertimeDbDto> rateOvertimeDbDtos = List.of(rateOvertimeDbDto1, rateOvertimeDbDto2);

        //when
        Employee employee = mapper.toDomain(employeeDto, rateRegularDbDtos, rateOvertimeDbDtos);

        //then
        assertEquals(2, employee.getRateRegular().size());
        assertEquals(2, employee.getRateOvertime().size());

    }

    private EmployeeDbDto createEmployeeDto(){
        return new EmployeeDbDto(22,"Tomasz","Krutowski",0.5F, 26,
                "662262662","brak info",  EmploymentStatus.HIRED, LocalDate.of(2007,6,8),
                null,LocalDate.of(2022,1,22),LocalDate.of(2022,3,15),
                WorkTime.FULL_TIME, EmployeeType.WORKER,"Pobiedziska", "Armii Poznań 39", "62-010","test@test.pl",
                "76661178712",1);
    }

}