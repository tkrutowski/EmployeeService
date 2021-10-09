package net.focik.hr.employee.api;

import net.focik.hr.employee.domain.Address;
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

import static org.junit.jupiter.api.Assertions.*;

class ApiMapperTest {

    ApiMapper mapper = new ApiMapper();
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
        assertEquals(dto.getTelNumber(), result.getTelNumber());
        assertEquals(dto.getOtherInfo(), result.getOtherInfo());
        assertEquals(dto.getEmploymentStatus(), result.getEmploymentStatus().toString());
        assertEquals(dto.getHiredDate(), result.getHiredDate());
        assertEquals(dto.getReleaseDate(), result.getReleaseDate());
        assertEquals(dto.getNextMedicalExaminationDate(), result.getNextMedicalExaminationDate());
        assertEquals(dto.getNextBhpTrainingDate(), result.getNextBhpTrainingDate());
        assertEquals(dto.getWorkTime(), result.getWorkTime().toString());
        assertEquals(dto.getEmployeeType(), result.getEmployeeType().toString());
        assertEquals(dto.getCity(), result.getAddress().getCity());
        assertEquals(dto.getStreet(), result.getAddress().getStreet());
        assertEquals(dto.getZip(), result.getAddress().getZip());
        assertEquals(dto.getRateRegularType(), result.getRateRegular().getRateType().toString());
        assertEquals(dto.getRateRegularDateFrom(), result.getRateRegular().getDateFrom());
        assertEquals(BigDecimal.valueOf(dto.getRateRegularValue()), result.getRateRegular().getRateValue());
        assertEquals(dto.getRateOvertimeDateFrom(), result.getRateOvertime().getDateFrom());
        assertEquals(BigDecimal.valueOf(dto.getRateOvertimeValue()), result.getRateOvertime().getRateValue());
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
        assertEquals(e.getTelNumber(), result.getTelNumber());
        assertEquals(e.getOtherInfo(), result.getOtherInfo());
        assertEquals(e.getEmploymentStatus(), EmploymentStatus.valueOf(result.getEmploymentStatus()));
        assertEquals(e.getHiredDate(), result.getHiredDate());
        assertEquals(e.getReleaseDate(), result.getReleaseDate());
        assertEquals(e.getNextMedicalExaminationDate(), result.getNextMedicalExaminationDate());
        assertEquals(e.getNextBhpTrainingDate(), result.getNextBhpTrainingDate());
        assertEquals(e.getWorkTime(), WorkTime.valueOf(result.getWorkTime()));
        assertEquals(e.getEmployeeType(),EmployeeType.valueOf(result.getEmployeeType()));
        assertEquals(e.getAddress().getCity(), result.getCity());
        assertEquals(e.getAddress().getStreet(), result.getStreet());
        assertEquals(e.getAddress().getZip(), result.getZip());
        assertEquals(e.getRateRegular().getRateType().toString(), result.getRateRegularType());
        assertEquals(e.getRateRegular().getDateFrom(), result.getRateRegularDateFrom());
        assertEquals(e.getRateRegular().getRateValue().doubleValue(), result.getRateRegularValue());
        assertEquals(e.getRateOvertime().getDateFrom(), result.getRateOvertimeDateFrom());
        assertEquals(e.getRateOvertime().getRateValue().doubleValue(), result.getRateOvertimeValue());
    }


    private EmployeeDto createEmployeeDto(){
        return new EmployeeDto(2,"Tomasz","Krutowski",0.5F, 26,
                "662262662","brak info", "HIRED", LocalDate.of(2007,6,8),
                null,LocalDate.of(2022,1,22),LocalDate.of(2022,3,15),
                "FULL_TIME", "WORKER","Pobiedziska", "Armii Poznań 39", "62-010", "PER_MONTH",
                LocalDate.of(2022,3,1), 5450.0d,    LocalDate.of(2022,3,1), 34.5d);
    }
    private Employee  createEmployee(){
        return new Employee(2,"Tomasz","Krutowski",0.5F, 26,
                "662262662","brak info", EmploymentStatus.HIRED, LocalDate.of(2007,6,8),
                null,LocalDate.of(2022,1,22),LocalDate.of(2022,3,15),
                WorkTime.FULL_TIME, EmployeeType.WORKER,new Address("Pobiedziska","Armii Poznań 39","62-010" ),
                new RateRegular(1,2,  RateType.PER_MONTH,LocalDate.of(2020,5,1),BigDecimal.valueOf(5400.0)),
                new RateOvertime(1,2,LocalDate.of(2020,5,1),BigDecimal.valueOf(35.4)));
    }
}