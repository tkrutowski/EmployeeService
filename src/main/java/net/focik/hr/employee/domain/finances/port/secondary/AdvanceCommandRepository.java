package net.focik.hr.employee.domain.finances.port.secondary;

import net.focik.hr.employee.domain.finances.Advance;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public interface AdvanceCommandRepository {
    Integer add(Advance advance, Integer idEmployee);
}
