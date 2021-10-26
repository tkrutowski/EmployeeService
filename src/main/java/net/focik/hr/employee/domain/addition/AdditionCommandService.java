package net.focik.hr.employee.domain.addition;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.addition.port.secondary.AdditionCommandRepository;
import net.focik.hr.employee.domain.exceptions.AdvanceNotValidException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
class AdditionCommandService {

    private AdditionCommandRepository additionCommandRepository;

    Integer addAddition(Addition addition, Integer idEmployee) {
        if(!validate(addition))
            throw new AdvanceNotValidException();
        return additionCommandRepository.add(addition, idEmployee);
    }

    private boolean validate(Addition a){
//        boolean isValid = false;

        if( a.getAmount() == BigDecimal.ZERO)
            return false;
        if(a.getDate() == null )
            return false;
        if(a.getAdditionType() == null )
            return false;

        return true;
    }

}
