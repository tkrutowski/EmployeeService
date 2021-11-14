package net.focik.hr.employee.infrastructure.jpa;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import net.focik.hr.employee.infrastructure.dto.EmployeeDto;
import net.focik.hr.employee.infrastructure.dto.RateOvertimeDto;
import net.focik.hr.employee.infrastructure.dto.RateRegularDto;
import net.focik.hr.employee.infrastructure.mapper.JpaEmployeeMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Primary
@AllArgsConstructor
class EmployeeCommandRepositoryAdapter implements EmployeeCommandRepository {

    EmployeeDtoRepository employeeDtoRepository;
    RateRegularDtoRepository rateRegularDtoRepository;
    RateOvertimeDtoRepository rateOvertimeDtoRepository;
    JpaEmployeeMapper mapper;

    @Override
    @Transactional
    public Integer add(Employee e) {
        EmployeeDto employeeDto = employeeDtoRepository.save(mapper.toDto(e));
        RateRegularDto rateRegularDto = new RateRegularDto(null, employeeDto.getId(), e.getLatestRateRegularType(),e.getLatestRateRegularDateFrom(),e.getLatestRateRegularValue());
        rateRegularDtoRepository.save(rateRegularDto);
        RateOvertimeDto rateOvertimeDto = new RateOvertimeDto(null, employeeDto.getId(),e.getLatestRateOvertimeDateFrom(),e.getLatestRateOvertimeValue());
        rateOvertimeDtoRepository.save(rateOvertimeDto);
        return employeeDto.getId();
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        Optional<EmployeeDto> byId = employeeDtoRepository.findById(id);
        List<RateOvertimeDto> rateOvertimeDtos = rateOvertimeDtoRepository.findAllByIdEmployee(id);
        List<RateRegularDto> rateRegularDtos = rateRegularDtoRepository.findAllByIdEmployee(id);
        if(byId.isEmpty())
            return Optional.empty();


        return Optional.of(mapper.toDomain(byId.get(), rateRegularDtos, rateOvertimeDtos));
    }

}
