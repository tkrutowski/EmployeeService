package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.EmployeeNotExistException;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import net.focik.hr.employee.domain.port.secondary.EmployeeQueryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class EmployeeQueryService {
    private EmployeeQueryRepository employeeQueryRepository;

    public Employee findEmployeeById(Integer id) {
        Optional<Employee> byId = employeeQueryRepository.findById(id);

        if (byId.isEmpty())
            throw new EmployeeNotExistException(id);

        return byId.get();
    }

}
