package net.focik.hr.employee.domain.addition.port.secondary;

import net.focik.hr.employee.domain.addition.Addition;
import org.springframework.stereotype.Component;

@Component
public interface AdditionCommandRepository {
    Integer add(Addition advance, Integer idEmployee);
}
