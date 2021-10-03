package net.focik.hr.employee.domain.port.secondary;

import net.focik.hr.employee.domain.RateRegular;

import java.util.List;
import java.util.Optional;


public interface RateRegularRepository {
    Integer add(RateRegular rate);

    Optional<RateRegular> findRateRegularById(Integer id);

    List<RateRegular> findRateRegularByEmployeeId(Integer id);
}