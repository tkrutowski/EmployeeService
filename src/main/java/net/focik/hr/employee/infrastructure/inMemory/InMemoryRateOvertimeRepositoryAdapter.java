package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.domain.port.secondary.RateOvertimeRepository;
import net.focik.hr.employee.domain.port.secondary.RateRegularRepository;
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
    private Map<Integer, RateOvertime> rateOvertimeHashMap = new HashMap<>();


    @Override
    public Integer add(RateOvertime rate) {
        log.info("Try add into inMemoryDb rate: "+rate.toString());
        Integer id = rateOvertimeHashMap.size() + 1;
        rate.setIdRate(id);
        rateOvertimeHashMap.put(id, rate);

        log.info("Succssec id = " + rate.getIdRate());
        return rate.getIdRate();
    }

    @Override
    public Optional<RateOvertime> findRateOvertimeById(Integer id) {
        return Optional.ofNullable(rateOvertimeHashMap.get(id));
    }


    @Override
    public List<RateOvertime> findRateOvertimeEmployeeId(Integer id) {
        return rateOvertimeHashMap.entrySet()
                .stream()
                .filter(integerRateEntry -> integerRateEntry.getValue().getIdEmployee().equals(id))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
