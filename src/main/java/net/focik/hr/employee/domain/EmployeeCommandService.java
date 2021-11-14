package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.EmployeeNotExistException;
import net.focik.hr.employee.domain.exceptions.EmployeeNotValidException;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import net.focik.hr.employee.query.EmployeeQueryDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class EmployeeCommandService {

    private EmployeeCommandRepository employeeCommandRepository;
   // private EmployeeFactory employeeFactory;

    Employee findEmployeeById(Integer id) {
        Optional<Employee> byId = employeeCommandRepository.findById(id);

        if (byId.isEmpty())
            throw new EmployeeNotExistException(id);

        return byId.get();
    }

    Integer addEmployee(Employee employee) {
        int i=0;
        if(!validate(employee))
            throw new EmployeeNotValidException();
        return employeeCommandRepository.add(employee);
    }

//    Integer updateRateRegular(int idEmployee, RateRegular rateRegular){
//        Employee employee = employeeFactory.createEmployee(idEmployee);
//
//    }

    private boolean validate(Employee e){
//        boolean isValid = false;

        if( e.getFirstName() == null || e.getFirstName().isEmpty())
            return false;
        if(e.getLastName() == null || e.getLastName().isEmpty() )
            return false;
        if(e.getNumberDaysOffLeft() == null)
            return false;
        if(e.getNumberDaysOffAnnually() == null)
            return false;
        if(e.getEmploymentStatus() == null)
            return false;
        if(e.getHiredDate() == null)
            return false;
        if(e.getWorkTime() == null)
            return false;
        if(e.getEmployeeType() == null)
            return false;
        if(e.getCity() == null || e.getCity().isEmpty() )
            return false;
        if(e.getStreet() == null || e.getStreet().isEmpty() )
            return false;
        if(e.getZip() == null || e.getZip().isEmpty())
            return false;
        if(e.getLatestRateRegularType() == null)
            return false;
        if(e.getLatestRateRegularDateFrom() == null)
            return false;
        if(e.getLatestRateRegularValue() == null)
            return false;
        if(e.getLatestRateOvertimeDateFrom() == null)
            return false;
        if(e.getLatestRateOvertimeValue() == null)
            return false;

        return true;
    }

}
