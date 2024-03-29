package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.EmployeeNotFoundException;
import net.focik.hr.employee.domain.port.secondary.EmployeeQueryRepository;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.query.EmployeeQueryBasicDto;
import net.focik.hr.employee.query.EmployeeQueryPrintDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
class EmployeeQueryService {
    private EmployeeQueryRepository employeeQueryRepository;

    public EmployeeQueryBasicDto findEmployeeById(Integer id) {
        Optional<EmployeeQueryBasicDto> byId = employeeQueryRepository.findById(id);

        if (byId.isEmpty())
            throw new EmployeeNotFoundException(id);

        return byId.get();
    }

    public List<EmployeeQueryBasicDto> findAll() {
        return employeeQueryRepository.findAll();
    }

    public List<EmployeeQueryBasicDto> findAllByEmploymentStatus(EmploymentStatus status) {
        return employeeQueryRepository.findAllByEmploymentStatus(status);
    }

    public List<EmployeeQueryPrintDto> findAllByEmploymentStatusPrint(EmploymentStatus status) {
        return employeeQueryRepository.findAllByEmploymentStatusPrint(status);
    }
}