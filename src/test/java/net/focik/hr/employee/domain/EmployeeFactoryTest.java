package net.focik.hr.employee.domain;

import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import net.focik.hr.employee.domain.port.secondary.EmployeeQueryRepository;
import net.focik.hr.employee.domain.port.secondary.RateRepository;
import net.focik.hr.employee.infrastructure.inMemory.InMemoryEmployeeCommandRepositoryAdapter;
import net.focik.hr.employee.infrastructure.inMemory.InMemoryEmployeeQueryRepositoryAdapter;
import net.focik.hr.employee.infrastructure.inMemory.InMemoryRateRepositoryAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class EmployeeFactoryTest {

    EmployeeCommandRepository employeeCommandRepository = new InMemoryEmployeeCommandRepositoryAdapter();
    EmployeeQueryRepository employeeQueryRepository=new InMemoryEmployeeQueryRepositoryAdapter();
    EmployeeCommandService employeeCommandService = new EmployeeCommandService(employeeCommandRepository);
    EmployeeQueryService employeeQueryService = new EmployeeQueryService(employeeQueryRepository);

    RateRepository rateRepository = new InMemoryRateRepositoryAdapter();
    RateService rateService = new RateService(rateRepository);
    EmployeeFactory factory = new EmployeeFactory(employeeQueryService,rateService);


    @Test
    void should_create_Employee() {
        //given
        int id = employeeCommandService.addEmployee(createEmployee1());
        Integer idRegular1 = rateService.addRateRegular(createRateRegular1());
        Integer idRegular2 = rateService.addRateRegular(createRateRegular2());
        Integer idOver1 = rateService.addRateOvertime(createRateOvertime1());
        Integer idOver2 = rateService.addRateOvertime(createRateOvertime2());

        //when
        Employee employee = factory.createEmployee(id);

        //then
        Assertions.assertEquals(idRegular2,employee.getRateRegular().getIdRate());
        Assertions.assertEquals(idOver2,employee.getRateOvertime().getIdRate());
    }

    private Employee  createEmployee1(){
        return new Employee(null,"Tomasz","Krutowski",0.5F, 26,
                "662262662","brak info",EmploymentStatus.HIRED, LocalDate.of(2007,6,8),
                null,LocalDate.of(2022,1,22),LocalDate.of(2022,3,15),
                WorkTime.FULL_TIME,EmployeeType.WORKER,new Address("Pobiedziska","Armii Poznań 39","62-010" ),
                null,null );
    }

    private RateRegular createRateRegular1(){
        return new RateRegular(null,1,LocalDate.of(2011,5,15), BigDecimal.valueOf(3000),RateType.PER_MONTH);
    }

    private RateRegular createRateRegular2(){
        return new RateRegular(null,1,LocalDate.of(2017,9,01),BigDecimal.valueOf(4000),RateType.PER_MONTH);
    }

    private RateOvertime createRateOvertime1(){
        return new RateOvertime(null,1,LocalDate.of(2011,5,15),BigDecimal.valueOf(19));
    }

    private RateOvertime createRateOvertime2(){
        return new RateOvertime(null,1,LocalDate.of(2017,9,01),BigDecimal.valueOf(25));
    }
}