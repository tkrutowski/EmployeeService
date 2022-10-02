package net.focik.hr.employee.application;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.EmployeeFacade;
import net.focik.hr.employee.domain.port.primary.AddNewEmployeeUseCase;
import net.focik.hr.employee.domain.port.primary.DeleteEmployeeUseCase;
import net.focik.hr.employee.domain.port.primary.UpdateEmployeUseCase;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmployeeApiService implements AddNewEmployeeUseCase, UpdateEmployeUseCase, DeleteEmployeeUseCase {
    EmployeeFacade employeeFacade;

    @Override
    public Integer addEmployee(Employee employee) {
        return employeeFacade.addEmployee(employee);
    }

    @Override
    public void updateEmploymentStatus(int idEmployee, EmploymentStatus employmentStatus) {
        employeeFacade.updateEmploymentStatus(idEmployee, employmentStatus);

    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeFacade.updateEmployee(employee);
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeFacade.deleteEmployee(id);
    }
}