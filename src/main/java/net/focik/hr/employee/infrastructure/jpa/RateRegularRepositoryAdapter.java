package net.focik.hr.employee.infrastructure.jpa;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.domain.port.secondary.RateRegularRepository;
import net.focik.hr.employee.infrastructure.dto.RateRegularDbDto;
import net.focik.hr.employee.infrastructure.mapper.JpaRateMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log
@AllArgsConstructor
public class RateRegularRepositoryAdapter implements RateRegularRepository {
    private final JpaRateMapper jpaMapper;
    private final RateRegularDtoRepository regularDtoRepository;

    @Override
    public RateRegular add(RateRegular rate, Integer idEmployee) {
        RateRegularDbDto dto = jpaMapper.toDto(rate, idEmployee);

        RateRegularDbDto saved = regularDtoRepository.save(dto);

        return jpaMapper.toDomain(saved);
    }

    @Override
    public List<RateRegular> findRateRegularByEmployeeId(Integer idEmployee) {
        return regularDtoRepository.findAllByIdEmployee(idEmployee).stream()
                .map(jpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRateRegularByIdEmployee(Integer idEmployee) {
        regularDtoRepository.deleteByIdEmployee(idEmployee);
    }

    @Override
    public void deleteRateRegularById(Integer id) {
        regularDtoRepository.deleteById(id);
    }

    @Override
    public RateRegular findRateRegularByIdAndDate(Integer id, LocalDate date) {
        List<RateRegularDbDto> rates = regularDtoRepository.findByIdEmployeeAndAndDateFromOrderByIdRateDesc(id, date);

        if (rates.isEmpty()) {
            return null;
        }
        return jpaMapper.toDomain(rates.get(0));
    }

}
