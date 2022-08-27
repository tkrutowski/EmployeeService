package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.EmployeeNotFoundException;
import net.focik.hr.employee.domain.exceptions.EmployeeNotValidException;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
class EmployeeService {

    private final EmployeeCommandRepository employeeCommandRepository;
    private final RateService rateService;

    @Transactional
    Employee findEmployeeById(Integer id) {
        Optional<Employee> byId = employeeCommandRepository.findById(id);
        if (byId.isEmpty())
            throw new EmployeeNotFoundException(id);

        Employee employee = byId.get();
        employee.setRateRegular(rateService.findRateRegularByEmployeeId(id));
        employee.setRateOvertime(rateService.findRateOvertimeEmployeeId(id));

        return byId.get();
    }

    Integer addEmployee(Employee employee) {
        if (isNotValid(employee))
            throw new EmployeeNotValidException();
        return employeeCommandRepository.add(employee);
    }

    Employee updateEmployee(Employee employee) {
        if (isNotValid(employee))
            throw new EmployeeNotValidException();
        return employeeCommandRepository.update(employee);
    }

    @Transactional
    public void deleteEmployee(int id) {
        rateService.deleteRateRegularByIdEmployee(id);
        rateService.deleteRateOvertimeByIdEmployee(id);
        employeeCommandRepository.delete(id);
    }

    private boolean isNotValid(Employee e) {
        return StringUtils.isEmpty(e.getFirstName())
                || StringUtils.isEmpty(e.getLastName())
                || (e.getNumberDaysOffLeft() == null)
                || (e.getNumberDaysOffAnnually() == null)
                ||(e.getEmploymentStatus() == null)
                || (e.getHiredDate() == null)
                || (e.getWorkTime() == null)
                || (e.getEmployeeType() == null)
                || StringUtils.isEmpty(e.getZip())
                || StringUtils.isEmpty(e.getCity())
                || StringUtils.isEmpty(e.getStreet());
    }
}
