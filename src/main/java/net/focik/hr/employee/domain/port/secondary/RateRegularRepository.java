package net.focik.hr.employee.domain.port.secondary;

import net.focik.hr.employee.domain.RateRegular;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface RateRegularRepository {
    Integer add(RateRegular rateRegular, Integer idEmployee);

    List<RateRegular> findRateRegularByEmployeeId(Integer idEmployee);

//    Optional<RateRegular> findRateRegularById(Integer id);
}