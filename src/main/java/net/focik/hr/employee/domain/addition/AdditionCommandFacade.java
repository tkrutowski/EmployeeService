package net.focik.hr.employee.domain.addition;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AdditionCommandFacade {

    AdditionCommandService additionCommandService;

    public Integer addAddition(Addition addition, Integer idEmployee){
        return additionCommandService.addAddition(addition, idEmployee);
    }
}
