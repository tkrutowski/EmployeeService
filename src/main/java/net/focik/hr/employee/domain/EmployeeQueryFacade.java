package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.query.EmployeeQueryBasicDto;
import net.focik.hr.employee.query.EmployeeQueryPrintDto;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class EmployeeQueryFacade {

    EmployeeQueryService employeeQueryService;

    public EmployeeQueryBasicDto getEmployee(Integer id) {
        return employeeQueryService.findEmployeeById(id);
    }

    public List<EmployeeQueryBasicDto> getAllEmployee() {
        return employeeQueryService.findAll();
    }

    public List<EmployeeQueryBasicDto> getAllEmployeesByEmploymentStatus(EmploymentStatus status) {
        return employeeQueryService.findAllByEmploymentStatus(status);
    }

    public List<EmployeeQueryPrintDto> getAllEmployeesByEmploymentStatusPrint(EmploymentStatus status) {
        return employeeQueryService.findAllByEmploymentStatusPrint(status);
    }
}