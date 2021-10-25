package net.focik.hr.employee.domain.advance;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.AdvanceNotValidException;
import net.focik.hr.employee.domain.advance.port.secondary.AdvanceCommandRepository;
import org.springframework.stereotype.Service;

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
