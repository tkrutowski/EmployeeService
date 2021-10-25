package net.focik.hr.employee.domain;

import net.focik.hr.employee.domain.exceptions.EmployeeNotValidException;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.share.WorkTime;
import net.focik.hr.employee.infrastructure.inMemory.db.DataBaseEmployee;
import net.focik.hr.employee.infrastructure.inMemory.InMemoryEmployeeCommandRepositoryAdapter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
//@SpringBootTest
class EmployeeCommandFacadeTest {

  //  @Autowired
    //@Qualifier("inMemory")
    EmployeeCommandRepository employeeCommandRepository = new InMemoryEmployeeCommandRepositoryAdapter();

    EmployeeCommandService employeeCommandService = new EmployeeCommandService(employeeCommandRepository);

    @Test
    void should_add_Employee() {
        int i=0;
        //given
        int ID = DataBaseEmployee.getEmployeeHashMap().size() + 1;

        //when
        Integer resultId = employeeCommandService.addEmployee(createEmployee());

        //then
        assertEquals(ID, resultId);
    }

    @Test
    void should_throw_EmployeeNotValidException_when_name_is_null() {
        int i=0;
        //given
        Employee employee = createEmployeeWithNameNull();

        //then
        assertThrows(EmployeeNotValidException.class, () -> employeeCommandService.addEmployee(employee));
    }
    @Test
    void should_throw_EmployeeNotValidException_when_name_is_empty() {
        int i=0;
        //given
        Employee employee = createEmployeeWithNameEmpty();

        //then
        assertThrows(EmployeeNotValidException.class, () -> employeeCommandService.addEmployee(employee));
    }


    private Employee createEmployee(){
        int i =0;
        Employee employee = new Employee(2,"Tomasz","Krutowski",0.5F, 26,
                "662262662","brak info", "test@test.pl" ,EmploymentStatus.HIRED, LocalDate.of(2007,6,8),
                null,LocalDate.of(2022,1,22),LocalDate.of(2022,3,15),
                WorkTime.FULL_TIME, EmployeeType.WORKER, null,null,null);
        employee.setAddress("Pobiedziska","Armii Poznań 39","62-010" );
        employee.setRateRegular(1, RateType.PER_MONTH,LocalDate.of(2020,5,1), BigDecimal.valueOf(5400.0));
        employee.setRateOvertime(1, LocalDate.of(2020,5,1),BigDecimal.valueOf(35.4));
        return employee;
    }
    private Employee createEmployeeWithNameNull(){
        Employee employee = new Employee(2,null,"Krutowski",0.5F, 26,
                "662262662","brak info", "test@test.pl" ,EmploymentStatus.HIRED, LocalDate.of(2007,6,8),
                null,LocalDate.of(2022,1,22),LocalDate.of(2022,3,15),
                WorkTime.FULL_TIME, EmployeeType.WORKER, null,null,null);
        employee.setAddress("Pobiedziska","Armii Poznań 39","62-010" );
        employee.setRateRegular( 2, RateType.PER_MONTH,LocalDate.of(2020,5,1), BigDecimal.valueOf(5400.0));
        employee.setRateOvertime(2, LocalDate.of(2020,5,1),BigDecimal.valueOf(35.4));
        return employee;
    }

    private Employee createEmployeeWithNameEmpty(){
        Employee employee = new Employee(2,"","Krutowski",0.5F, 26,
                "662262662","brak info", "test@test.pl" ,EmploymentStatus.HIRED, LocalDate.of(2007,6,8),
                null,LocalDate.of(2022,1,22),LocalDate.of(2022,3,15),
                WorkTime.FULL_TIME, EmployeeType.WORKER, null,null,null);
        employee.setAddress("Pobiedziska","Armii Poznań 39","62-010" );
        employee.setRateRegular(2, RateType.PER_MONTH,LocalDate.of(2020,5,1), BigDecimal.valueOf(5400.0));
        employee.setRateOvertime(2, LocalDate.of(2020,5,1),BigDecimal.valueOf(35.4));
        return employee;
    }


}