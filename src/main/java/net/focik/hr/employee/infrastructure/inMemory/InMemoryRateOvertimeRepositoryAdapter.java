package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.port.secondary.RateOvertimeRepository;
import net.focik.hr.employee.infrastructure.dto.RateOvertimeDto;
import net.focik.hr.employee.infrastructure.mapper.JpaRateMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component()
@Profile({"dev", "test"})
@Log
public class InMemoryRateOvertimeRepositoryAdapter implements  RateOvertimeRepository {
    private Map<Integer, RateOvertimeDto> rateOvertimeHashMap = new HashMap<>();
    private JpaRateMapper jpaMapper = new JpaRateMapper();

    @Override
    public Integer add(RateOvertime rate, Integer idEmployee) {
        log.info("Try add into inMemoryDb rate: "+rate.toString());
        Integer id = rateOvertimeHashMap.size() + 1;
        //rate.setIdRate(id);
        rateOvertimeHashMap.put(id, jpaMapper.toDto(rate, idEmployee));

        log.info("Succssec id = " + rate.getIdRate());
        return rate.getIdRate();
    }

    //@Override
    public Optional<RateOvertimeDto> findRateOvertimeById(Integer id) {
        return Optional.ofNullable(rateOvertimeHashMap.get(id));
    }


    @Override
    public List<RateOvertime> findRateOvertimeEmployeeId(Integer id) {
        return rateOvertimeHashMap.entrySet()
                .stream()
                .filter(integerRateEntry -> integerRateEntry.getValue().getIdEmployee().equals(id))
                .map(Map.Entry::getValue)
                .map(rateOvertimeDto -> jpaMapper.toDomain(rateOvertimeDto))
                .collect(Collectors.toList());
    }
}
