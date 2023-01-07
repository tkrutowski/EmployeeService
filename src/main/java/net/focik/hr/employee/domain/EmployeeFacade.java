package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EmployeeFacade {

    private final EmployeeService employeeService;
    private final RateService rateService;

    public Employee getEmployee(Integer id) {
        return employeeService.findEmployeeById(id);
    }

    public List<Employee> getAllBy(EmploymentStatus employmentStatus, Boolean isGetRate, EmployeeType employeeType) {
        return employeeService.findByAll(employmentStatus, isGetRate, employeeType);
    }

    public Integer addEmployee(Employee employee) {
        return employeeService.addEmployee(employee);
    }

    public RateRegular getLastRateRegular(int employeeId) {
        return employeeService.findEmployeeById(employeeId).getLatestRateRegular();
    }

    public RateOvertime getLastRateOvertime(int employeeId) {
        return employeeService.findEmployeeById(employeeId).getLatestRateOvertime();
    }

    public List<RateRegular> getAllLastRateRegular(int employeeId) {
        return employeeService.findEmployeeById(employeeId).getRateRegular().stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    public List<RateOvertime> getAllLastRateOvertime(int employeeId) {
        return employeeService.findEmployeeById(employeeId).getRateOvertime().stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    public void updateEmploymentStatus(int idEmployee, EmploymentStatus employmentStatus) {
        Employee employeeById = employeeService.findEmployeeById(idEmployee);
        employeeById.changeEmploymentStatus(employmentStatus);

        employeeService.updateEmployee(employeeById);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    public RateRegular addRateRegular(int employeeId, RateRegular rateRegular) {
        return rateService.addRateRegular(rateRegular, employeeId);
    }

    public RateOvertime addRateOvertime(int employeeId, RateOvertime rateOvertime) {
        return rateService.addRateOvertime(rateOvertime, employeeId);
    }

    public void deleteEmployee(int id) {
        employeeService.deleteEmployee(id);
    }

    public void deleteRateRegular(int id) {
        rateService.deleteRateRegularById(id);
    }

    public void deleteRateOvertime(int id) {
        rateService.deleteRateOvertimeById(id);
    }

    public RateRegular getRateRegularByEmployeeIdAndDate(int employeeId, LocalDate date) {
        return rateService.getRateRegularByEmployeeIdAndDate(employeeId, date);
    }

    public RateRegular updateRateRegular(int employeeId, RateRegular rateRegular) {
        return rateService.addRateRegular(rateRegular, employeeId);
    }

    public RateOvertime updateRateOvertime(int employeeId, RateOvertime rateOvertime) {
        return rateService.addRateOvertime(rateOvertime, employeeId);
    }
}