package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class EmployeeFactory {
    private EmployeeService employeeService;
    private RateService rateService;

    Employee createEmployee(Integer id) {
        final Employee employee = employeeService.findEmployeeById(id);
        employee.setRateRegular(findLastRateRegular(employee.getId()));
        employee.setRateOvertime(findLastRateOvertime(employee.getId()));

        return employee;
    }

    private RateRegular findLastRateRegular(Integer id) {
        RateRegular rateRegular = null;
        List<RateRegular> allRateRegular = rateService.findAllRateRegularByEmployee(id);
        int size = allRateRegular.size();
        if (size > 0)
            rateRegular = allRateRegular.get(size - 1);

        return rateRegular;
    }

    private RateOvertime findLastRateOvertime(Integer id) {
        RateOvertime rateOvertime = null;
        List<RateOvertime> allRateOvertime = rateService.findAllRateOvertimeByEmployee(id);
        int size = allRateOvertime.size();
        if (size > 0)
            rateOvertime = allRateOvertime.get(size - 1);

        return rateOvertime;
    }
}
