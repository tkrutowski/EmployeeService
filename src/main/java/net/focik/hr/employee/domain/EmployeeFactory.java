package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.EmployeeNotExistException;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import net.focik.hr.employee.domain.port.secondary.RateRegularRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
class EmployeeFactory {
//    private EmployeeCommandService employeeCommandService;
//    private RateOvertimeService rateOvertimeService;
//    private RateRegularService rateRegularService;
    private RateRegularRepository rateRegularRepository;
    private EmployeeCommandRepository employeeCommandRepository;
   // private EmployeeCommandService commandService;
//
    Employee createEmployee(Integer id) {
        Optional<Employee> employee = employeeCommandRepository.findById(id);

        if(employee.isEmpty())
            throw  new EmployeeNotExistException(id);

        employee.get()
                .setRateRegular(new HashSet<>(rateRegularRepository.findRateRegularByEmployeeId(id)));



                return employee.get();
    }
//
//    private RateRegular findLastRateRegular(Integer id) {
//        RateRegular rateRegular = null;
//        List<RateRegular> allRateRegular = rateRegularService.findAllRateRegularByEmployee(id);
//        int size = allRateRegular.size();
//        if (size > 0)
//            rateRegular = allRateRegular.get(size - 1);
//
//        return rateRegular;
//    }
//
//    private RateOvertime findLastRateOvertime(Integer id) {
//        RateOvertime rateOvertime = null;
//        List<RateOvertime> allRateOvertime = rateOvertimeService.findAllRateOvertimeByEmployee(id);
//        int size = allRateOvertime.size();
//        if (size > 0)
//            rateOvertime = allRateOvertime.get(size - 1);
//
//        return rateOvertime;
//    }
}
