package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.share.WorkTime;
import net.focik.hr.employee.infrastructure.dto.EmployeeDto;
import net.focik.hr.employee.infrastructure.dto.RateOvertimeDto;
import net.focik.hr.employee.infrastructure.dto.RateRegularDto;
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
        EmployeeDto employeeDto = createEmployeeDto();
        RateRegularDto rateRegular1 = new RateRegularDto(1, 22, RateType.PER_MONTH, LocalDate.of(2010,5,1), BigDecimal.valueOf(5000));
        RateRegularDto rateRegular2 = new RateRegularDto(2, 22, RateType.PER_MONTH, LocalDate.of(2015,5,1), BigDecimal.valueOf(6000));
        List<RateRegularDto> rateRegularDtos = List.of(rateRegular1, rateRegular2);
        RateOvertimeDto rateOvertimeDto1 = new RateOvertimeDto(1,22,LocalDate.of(2010,5,1), BigDecimal.valueOf(25.5));
        RateOvertimeDto rateOvertimeDto2 = new RateOvertimeDto(2,22,LocalDate.of(2015,5,1), BigDecimal.valueOf(35.5));
        List<RateOvertimeDto> rateOvertimeDtos = List.of(rateOvertimeDto1, rateOvertimeDto2);

        //when
        Employee employee = mapper.toDomain(employeeDto, rateRegularDtos, rateOvertimeDtos);

        //then
        assertEquals(2, employee.getRateRegular().size());
        assertEquals(2, employee.getRateOvertime().size());

    }

    private EmployeeDto createEmployeeDto(){
        return new EmployeeDto(22,"Tomasz","Krutowski",0.5F, 26,
                "662262662","brak info",  EmploymentStatus.HIRED, LocalDate.of(2007,6,8),
                null,LocalDate.of(2022,1,22),LocalDate.of(2022,3,15),
                WorkTime.FULL_TIME, EmployeeType.WORKER,"Pobiedziska", "Armii Poznań 39", "62-010","test@test.pl");
    }

}