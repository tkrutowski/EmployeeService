package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.EmployeeNotExistException;
import net.focik.hr.employee.domain.port.secondary.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class EmployeeService {
    private EmployeeRepository employeeRepository;

    public Integer addEmployee(Employee employee) {
        return employeeRepository.add(employee);
    }

    public Employee findEmployeeById(Integer id) {
        Optional<Employee> byId = employeeRepository.findById(id);

        if (byId.isEmpty())
            throw new EmployeeNotExistException(id);

        return byId.get();
    }
}
