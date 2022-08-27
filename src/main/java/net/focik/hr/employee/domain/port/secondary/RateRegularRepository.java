package net.focik.hr.employee.domain.port.secondary;

import net.focik.hr.employee.domain.RateRegular;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public interface RateRegularRepository {
    RateRegular add(RateRegular rateRegular, Integer idEmployee);

    List<RateRegular> findRateRegularByEmployeeId(Integer idEmployee);

    void deleteRateRegularByIdEmployee(Integer idEmployee);

    void deleteRateRegularById(Integer id);

    RateRegular  findRateRegularByIdAndDate(Integer id, LocalDate date);

}