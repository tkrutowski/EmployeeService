package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.EmployeeNotFoundException;
import net.focik.hr.employee.domain.exceptions.EmployeeNotValidException;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class EmployeeService {

    private final EmployeeCommandRepository employeeCommandRepository;
    // private EmployeeFactory employeeFactory;

    Employee findEmployeeById(Integer id) {
        Optional<Employee> byId = employeeCommandRepository.findById(id);

        if (byId.isEmpty())
            throw new EmployeeNotFoundException(id);

        return byId.get();
    }

    Integer addEmployee(Employee employee) {
        if (!validate(employee))
            throw new EmployeeNotValidException();
        return employeeCommandRepository.add(employee);
    }

    Employee updateEmployee(Employee employee) {
        if (!validate(employee))
            throw new EmployeeNotValidException();
        return employeeCommandRepository.update(employee);
    }

    public void deleteEmployee(int id) {
        employeeCommandRepository.delete(id);
    }


    private boolean validate(Employee e) {
        if (e.getFirstName() == null || e.getFirstName().isEmpty())
            return false;
        if (e.getLastName() == null || e.getLastName().isEmpty())
            return false;
        if (e.getNumberDaysOffLeft() == null)
            return false;
        if (e.getNumberDaysOffAnnually() == null)
            return false;
        if (e.getEmploymentStatus() == null)
            return false;
        if (e.getHiredDate() == null)
            return false;
        if (e.getWorkTime() == null)
            return false;
        if (e.getEmployeeType() == null)
            return false;
        if (e.getCity() == null || e.getCity().isEmpty())
            return false;
        if (e.getStreet() == null || e.getStreet().isEmpty())
            return false;
        if (e.getZip() == null || e.getZip().isEmpty())
            return false;

        return true;
    }

}
