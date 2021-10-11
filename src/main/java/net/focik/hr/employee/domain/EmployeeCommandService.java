package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.EmployeeNotValidException;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class EmployeeCommandService {

    private EmployeeCommandRepository employeeCommandRepository;

    Integer addEmployee(Employee employee) {
        if(!validate(employee))
            throw new EmployeeNotValidException();
        return employeeCommandRepository.add(employee);
    }

    boolean validate(Employee e){
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
        if(e.getRateRegularType() == null)
            return false;
        if(e.getRateRegularDateFrom() == null)
            return false;
        if(e.getRateRegularValue() == null)
            return false;
        if(e.getRateOvertimeDateFrom() == null)
            return false;
        if(e.getRateOvertimeValue() == null)
            return false;

        return true;
    }

}
