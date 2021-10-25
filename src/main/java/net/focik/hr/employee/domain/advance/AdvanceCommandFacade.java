package net.focik.hr.employee.domain.advance;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AdvanceCommandFacade {

    AdvanceCommandService advanceCommandService;

    public Integer addAdvice(Advance advance, Integer idEmployee){
        return advanceCommandService.addAdvance(advance, idEmployee);
    }
}
