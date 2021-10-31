package net.focik.hr.employee.domain.workTimeRecords;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.exceptions.EmployeeNotValidException;
import net.focik.hr.employee.domain.workTimeRecords.port.WorkTimeCommandRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class WorkTimeCommandService {

    private WorkTimeCommandRepository workTimeCommandRepository;
   // private EmployeeFactory employeeFactory;

    Integer addWorkTime(IWorkTime employee) {
        int i=0;
//        if(!validate(employee))
//            throw new EmployeeNotValidException();
        return workTimeCommandRepository.add(employee);
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
