package net.focik.hr.employee.domain.finances;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.exceptions.AdvanceNotValidException;
import net.focik.hr.employee.domain.exceptions.EmployeeNotValidException;
import net.focik.hr.employee.domain.finances.port.secondary.AdvanceCommandRepository;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;

import javax.money.Monetary;
import java.math.BigDecimal;

@Service
@AllArgsConstructor
class AdvanceCommandService {

    private AdvanceCommandRepository advanceCommandRepository;

    Integer addAdvance(Advance advance, Integer idEmployee) {
        if(!validate(advance))
            throw new AdvanceNotValidException();
        return advanceCommandRepository.add(advance, idEmployee);
    }

    private boolean validate(Advance a){
//        boolean isValid = false;

        if( a.getAmount() == BigDecimal.ZERO)
            return false;
        if(a.getDate() == null )
            return false;

        return true;
    }

}
