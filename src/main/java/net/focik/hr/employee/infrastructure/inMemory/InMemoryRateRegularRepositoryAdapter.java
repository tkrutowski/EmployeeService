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
public class InMemoryRateRegularRepositoryAdapter implements RateRegularRepository {
    private Map<Integer, RateRegular> rateRegularHashMap = new HashMap<>();



    @Override
    public Integer add(RateRegular rate) {
        log.info("Try add into inMemoryDb rate: "+rate.toString());
        Integer id = rateRegularHashMap.size() + 1;
        rate.setIdRate(id);
        rateRegularHashMap.put(id, rate);
        log.info("Succssec id = " + rate.getIdRate());
        return rate.getIdRate();
    }

    @Override
    public Optional<RateRegular> findRateRegularById(Integer id) {
        return Optional.ofNullable(rateRegularHashMap.get(id));
    }


    @Override
    public List<RateRegular> findRateRegularByEmployeeId(Integer id) {
        return rateRegularHashMap.entrySet()
                .stream()
                .filter(integerRateEntry -> integerRateEntry.getValue().getIdEmployee().equals(id))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}
