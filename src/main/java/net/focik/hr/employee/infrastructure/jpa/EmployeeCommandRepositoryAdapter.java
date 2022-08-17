package net.focik.hr.employee.infrastructure.jpa;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import net.focik.hr.employee.infrastructure.dto.EmployeeDbDto;
import net.focik.hr.employee.infrastructure.dto.RateOvertimeDbDto;
import net.focik.hr.employee.infrastructure.dto.RateRegularDbDto;
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

    private final EmployeeDtoRepository employeeDtoRepository;
    private final RateRegularDtoRepository rateRegularDtoRepository;
    private final RateOvertimeDtoRepository rateOvertimeDtoRepository;
    private final JpaEmployeeMapper mapper;

    @Override
    public Integer add(Employee e) {
        EmployeeDbDto employeeDto = employeeDtoRepository.save(mapper.toDto(e));
        return employeeDto.getId();
    }

    @Override
    public Employee update(Employee e) {
        EmployeeDbDto employeeDto = employeeDtoRepository.save(mapper.toDto(e));
        List<RateOvertimeDbDto> ratesOvertime = rateOvertimeDtoRepository.findAllByIdEmployee(e.getId());
        List<RateRegularDbDto> ratesRegular = rateRegularDtoRepository.findAllByIdEmployee(e.getId());
        return mapper.toDomain(employeeDto, ratesRegular, ratesOvertime);
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        Optional<EmployeeDbDto> byId = employeeDtoRepository.findById(id);
        List<RateOvertimeDbDto> rateOvertimeDbDtos = rateOvertimeDtoRepository.findAllByIdEmployee(id);
        List<RateRegularDbDto> rateRegularDbDtos = rateRegularDtoRepository.findAllByIdEmployee(id);
        if (byId.isEmpty())
            return Optional.empty();

        return Optional.of(mapper.toDomain(byId.get(), rateRegularDbDtos, rateOvertimeDbDtos));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        rateRegularDtoRepository.deleteByIdEmployee(id);
        rateOvertimeDtoRepository.deleteByIdEmployee(id);
        employeeDtoRepository.deleteById(id);
    }

}
