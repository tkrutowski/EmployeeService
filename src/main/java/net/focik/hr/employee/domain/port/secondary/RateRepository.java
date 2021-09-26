package net.focik.hr.employee.domain.port.secondary;

import net.focik.hr.employee.domain.Rate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface RateRepository {
    Integer add(Rate rate);

    Optional<? extends Rate> findRateRegularById(Integer id);

    Optional<? extends Rate> findRateOvertimeById(Integer id);

    List<? extends Rate> findRateRegularByEmployeeId(Integer id);
    List<? extends Rate> findRateOvertimeEmployeeId(Integer id);
}