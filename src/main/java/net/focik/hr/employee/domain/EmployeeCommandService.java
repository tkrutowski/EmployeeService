package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.EmployeeNotExistException;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class EmployeeCommandService {
    private EmployeeCommandRepository employeeCommandRepository;

    public Integer addEmployee(Employee employee) {
        return employeeCommandRepository.add(employee);
    }


}
