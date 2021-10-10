package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class EmployeeCommandService {

    private EmployeeCommandRepository employeeCommandRepository;

    public Integer addEmployee(Employee employee) {
         return employeeCommandRepository.add(employee);
    }

}
