package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.port.secondary.RateRegularRepository;
import net.focik.hr.employee.infrastructure.dto.RateRegularDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component()
@Profile({"dev", "test"})
@Log
public class InMemoryRateRegularRepositoryAdapter implements RateRegularRepository {
    private Map<Integer, RateRegularDto> rateRegularHashMap = new HashMap<>();

   // @Override
    public Integer add(RateRegularDto rate) {
        log.info("Try add into inMemoryDb rate: "+rate.toString());
        Integer id = rateRegularHashMap.size() + 1;
        rate.setIdRate(id);
        rateRegularHashMap.put(id, rate);
        log.info("Succssec id = " + rate.getIdRate());
        return rate.getIdRate();
    }

   // @Override
    public Optional<RateRegularDto> findRateRegularById(Integer id) {
        return Optional.ofNullable(rateRegularHashMap.get(id));
    }


   // @Override
    public List<RateRegularDto> findRateRegularByEmployeeId(Integer id) {
        return rateRegularHashMap.entrySet()
                .stream()
                .filter(integerRateEntry -> integerRateEntry.getValue().getIdEmployee().equals(id))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    //@Override
    public Optional<RateRegularDto> findRateRegularByDate(LocalDate date) {
        return Optional.empty();
    }

}
