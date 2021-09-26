package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.Rate;
import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.domain.port.secondary.RateRepository;
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
public class InMemoryRateRepositoryAdapter implements RateRepository {
    private Map<Integer, RateRegular> rateRegularHashMap = new HashMap<>();
    private Map<Integer, RateOvertime> rateOvertimeHashMap = new HashMap<>();


    @Override
    public Integer add(Rate rate) {
        int i=0;
    log.info("Try add into inMemoryDb rate: "+rate.toString());
        if(rate == null)
            throw new NullPointerException("Rate cannot be null");
        if(rate instanceof RateRegular){
            Integer id = rateRegularHashMap.size() + 1;
            rate.setId(id);
            rateRegularHashMap.put(id, (RateRegular) rate);
        }
        if(rate instanceof RateOvertime){
            Integer id = rateOvertimeHashMap.size() + 1;
            rate.setId(id);
            rateOvertimeHashMap.put(id, (RateOvertime) rate);
        }

        log.info("Succssec id = " + rate.getIdRate());
        return rate.getIdRate();
    }



    @Override
    public Optional<? extends Rate> findRateRegularById(Integer id) {
        return Optional.ofNullable(rateRegularHashMap.get(id));
    }

    @Override
    public Optional<? extends Rate> findRateOvertimeById(Integer id) {
        return Optional.ofNullable(rateOvertimeHashMap.get(id));
    }

    @Override
    public List<? extends Rate> findRateRegularByEmployeeId(Integer id) {
        return rateRegularHashMap.entrySet()
                .stream()
                .filter(integerRateEntry -> integerRateEntry.getValue().getIdEmployee().equals(id))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public List<? extends Rate> findRateOvertimeEmployeeId(Integer id) {
        return rateOvertimeHashMap.entrySet()
                .stream()
                .filter(integerRateEntry -> integerRateEntry.getValue().getIdEmployee().equals(id))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
