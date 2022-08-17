package net.focik.hr.employee.infrastructure.jpa;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.domain.port.secondary.RateOvertimeRepository;
import net.focik.hr.employee.infrastructure.dto.RateOvertimeDbDto;
import net.focik.hr.employee.infrastructure.dto.RateRegularDbDto;
import net.focik.hr.employee.infrastructure.mapper.JpaRateMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component()
@Log
@AllArgsConstructor
public class RateOvertimeRepositoryAdapter implements  RateOvertimeRepository {
    private final JpaRateMapper jpaMapper;
    private final RateOvertimeDtoRepository rateOvertimeDtoRepository;


    @Override
    public RateOvertime add(RateOvertime rate, Integer idEmployee) {
        RateOvertimeDbDto dto = jpaMapper.toDto(rate, idEmployee);

        RateOvertimeDbDto saved = rateOvertimeDtoRepository.save(dto);

        return jpaMapper.toDomain(saved);
    }

    @Override
    public List<RateOvertime> findRateOvertimeEmployeeId(Integer idEmployee) {
        return rateOvertimeDtoRepository.findAllByIdEmployee(idEmployee).stream()
                .map(dto -> jpaMapper.toDomain(dto))
                .collect(Collectors.toList());
    }
}
