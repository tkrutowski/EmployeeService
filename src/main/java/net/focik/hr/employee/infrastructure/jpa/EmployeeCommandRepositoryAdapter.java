package net.focik.hr.employee.infrastructure.jpa;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import net.focik.hr.employee.infrastructure.dto.EmployeeDto;
import net.focik.hr.employee.infrastructure.dto.JpaMapper;
import net.focik.hr.employee.infrastructure.dto.RateOvertimeDto;
import net.focik.hr.employee.infrastructure.dto.RateRegularDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Primary
@AllArgsConstructor
public class EmployeeCommandRepositoryAdapter implements EmployeeCommandRepository {

    EmployeeDtoRepository employeeDtoRepository;
    RateRegularDtoRepository rateRegularDtoRepository;
    RateOvertimeDtoRepository rateOvertimeDtoRepository;
    JpaMapper mapper;

    @Override
    @Transactional
    public Integer add(Employee e) {
        EmployeeDto employeeDto = employeeDtoRepository.save(mapper.toDto(e));
        RateRegularDto rateRegularDto = new RateRegularDto(null, employeeDto.getId(), e.getRateRegularType(),e.getRateRegularDateFrom(),e.getRateRegularValue());
        rateRegularDtoRepository.save(rateRegularDto);
        RateOvertimeDto rateOvertimeDto = new RateOvertimeDto(null, employeeDto.getId(),e.getRateOvertimeDateFrom(),e.getRateOvertimeValue());
        rateOvertimeDtoRepository.save(rateOvertimeDto);
        return employeeDto.getId();
    }

}
