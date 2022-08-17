package net.focik.hr.employee.api;

import net.focik.hr.employee.api.dto.EmployeeDto;
import net.focik.hr.employee.api.mapper.ApiEmployeeMapper;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.share.WorkTime;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiEmployeeMapperTest {

    ApiEmployeeMapper mapper = new ApiEmployeeMapper();
    @Test
    void map_EmployeeDto_toDomain() {
        //given
        final EmployeeDto dto = createEmployeeDto();

        //when
        Employee result = mapper.toDomain(dto);

        int i=0;
        //then
        assertEquals(dto.getFirstName(), result.getFirstName());
        assertEquals(dto.getLastName(), result.getLastName());
        assertEquals(dto.getNumberDaysOffLeft(), result.getNumberDaysOffLeft());
        assertEquals(dto.getNumberDaysOffAnnually(), result.getNumberDaysOffAnnually());
        assertEquals(dto.getTelNumber(), result.getPhoneNumber());
        assertEquals(dto.getOtherInfo(), result.getOtherInfo());
        assertEquals(dto.getEmploymentStatus(), result.getEmploymentStatus().toString());
        assertEquals(dto.getHiredDate(), result.getHiredDate());
        assertEquals(dto.getReleaseDate(), result.getReleaseDate());
        assertEquals(dto.getNextMedicalExaminationDate(), result.getNextMedicalExaminationDate());
        assertEquals(dto.getNextBhpTrainingDate(), result.getNextBhpTrainingDate());
        assertEquals(dto.getWorkTime(), result.getWorkTime().toString());
        assertEquals(dto.getEmployeeType(), result.getEmployeeType().toString());
        assertEquals(dto.getCity(), result.getCity());
        assertEquals(dto.getStreet(), result.getStreet());
        assertEquals(dto.getZip(), result.getZip());
//        assertEquals(dto.getRateRegularType(), result.getLatestRateRegular().getRateType().toString());
//        assertEquals(dto.getRateRegularDateFrom(), result.getLatestRateRegular().getDateFrom());
//        assertEquals(BigDecimal.valueOf(dto.getRateRegularValue()), result.getLatestRateRegular().getRateValue());
//        assertEquals(dto.getRateOvertimeDateFrom(), result.getLatestRateOvertime().getDateFrom());
//        assertEquals(BigDecimal.valueOf(dto.getRateOvertimeValue()), result.getLatestRateOvertime().getRateValue());
    }

    @Test
    void map_Employee_toDto() {
        //given
        final Employee e = createEmployee();

        //when
        EmployeeDto result = mapper.toDto(e);

        int i=0;
        //then
        assertEquals(e.getFirstName(), result.getFirstName());
        assertEquals(e.getLastName(), result.getLastName());
        assertEquals(e.getNumberDaysOffLeft(), result.getNumberDaysOffLeft());
        assertEquals(e.getNumberDaysOffAnnually(), result.getNumberDaysOffAnnually());
        assertEquals(e.getPhoneNumber(), result.getTelNumber());
        assertEquals(e.getOtherInfo(), result.getOtherInfo());
        assertEquals(e.getEmploymentStatus(), EmploymentStatus.valueOf(result.getEmploymentStatus()));
        assertEquals(e.getHiredDate(), result.getHiredDate());
        assertEquals(e.getReleaseDate(), result.getReleaseDate());
        assertEquals(e.getNextMedicalExaminationDate(), result.getNextMedicalExaminationDate());
        assertEquals(e.getNextBhpTrainingDate(), result.getNextBhpTrainingDate());
        assertEquals(e.getWorkTime(), WorkTime.valueOf(result.getWorkTime()));
        assertEquals(e.getEmployeeType(),EmployeeType.valueOf(result.getEmployeeType()));
        assertEquals(e.getCity(), result.getCity());
        assertEquals(e.getStreet(), result.getStreet());
        assertEquals(e.getZip(), result.getZip());
//        assertEquals(e.getLatestRateRegular().getRateType().toString(), result.getRateRegularType());
//        assertEquals(e.getLatestRateRegular().getDateFrom(), result.getRateRegularDateFrom());
//        assertEquals(e.getLatestRateRegular().getRateValue().doubleValue(), result.getRateRegularValue());
//        assertEquals(e.getLatestRateOvertime().getDateFrom(), result.getRateOvertimeDateFrom());
//        assertEquals(e.getLatestRateOvertime().getRateValue().doubleValue(), result.getRateOvertimeValue());
    }


    private EmployeeDto createEmployeeDto(){
        return new EmployeeDto(2,"Tomasz","Krutowski",0.5F, 26,
                "662262662","brak info", "test@test.pl", "HIRED", LocalDate.of(2007,6,8),
                null,LocalDate.of(2022,1,22),LocalDate.of(2022,3,15),
                "FULL_TIME", "WORKER","Pobiedziska", "Armii Poznań 39", "62-010", "PER_MONTH",
                LocalDate.of(2022,3,1), 5450.0d,    LocalDate.of(2022,3,1), 34.5d,
                1,"12365478921");
    }

    private Employee  createEmployee(){
        Employee employee = new Employee(2,"Tomasz","Krutowski",0.5F, 26,
                "662262662","brak info", "test@test.pl",EmploymentStatus.HIRED, LocalDate.of(2007,6,8),
                null,LocalDate.of(2022,1,22),LocalDate.of(2022,3,15),
                WorkTime.FULL_TIME, EmployeeType.WORKER, null,0,null,
                null, null
                );
                employee.setAddress("Pobiedziska","Armii Poznań 39","62-010" );
                employee.setRateRegular(1, RateType.PER_MONTH,LocalDate.of(2020,5,1),BigDecimal.valueOf(5400.0));
                employee.setRateOvertime(1, LocalDate.of(2020,5,1),BigDecimal.valueOf(35.4));

                return employee;
    }
}