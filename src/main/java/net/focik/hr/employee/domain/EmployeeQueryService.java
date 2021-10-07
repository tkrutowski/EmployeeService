package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.EmployeeNotExistException;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import net.focik.hr.employee.domain.port.secondary.EmployeeQueryRepository;
import net.focik.hr.employee.query.EmployeeQueryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
class EmployeeQueryService {
    private EmployeeQueryRepository employeeQueryRepository;

    public EmployeeQueryDto findEmployeeById(Integer id) {
        Optional<EmployeeQueryDto> byId = employeeQueryRepository.findById(id);

        if (byId.isEmpty())
            throw new EmployeeNotExistException(id);

        return byId.get();
    }

    public List<EmployeeQueryDto> findAll() {
        return employeeQueryRepository.findAll();
    }
}
