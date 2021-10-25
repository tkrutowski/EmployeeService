package net.focik.hr.employee.domain.advance.port.secondary;

import net.focik.hr.employee.domain.advance.Advance;
import org.springframework.stereotype.Component;

@Component
public interface AdvanceCommandRepository {
    Integer add(Advance advance, Integer idEmployee);
}
