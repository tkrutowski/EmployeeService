package net.focik.hr.employee.infrastructure.jpa;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import net.focik.hr.employee.infrastructure.dto.EmployeeDbDto;
import net.focik.hr.employee.infrastructure.mapper.JpaEmployeeMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Primary
@AllArgsConstructor
class EmployeeCommandRepositoryAdapter implements EmployeeCommandRepository {

    private final EmployeeDtoRepository employeeDtoRepository;
    private final JpaEmployeeMapper mapper;

    @Override
    public Integer add(Employee e) {
        EmployeeDbDto employeeDto = employeeDtoRepository.save(mapper.toDto(e));
        return employeeDto.getId();
    }

    @Override
    public Employee update(Employee e) {
        EmployeeDbDto employeeDto = employeeDtoRepository.save(mapper.toDto(e));
        return mapper.toDomain(employeeDto);
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        Optional<EmployeeDbDto> byId = employeeDtoRepository.findById(id);
        if (byId.isEmpty())
            return Optional.empty();

        return Optional.of(mapper.toDomain(byId.get()));
    }

    @Override
    public List<Employee> findAll() {
        return employeeDtoRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        employeeDtoRepository.deleteById(id);
    }

}
