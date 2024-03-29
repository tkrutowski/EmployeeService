package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.domain.port.secondary.RateRegularRepository;
import net.focik.hr.employee.infrastructure.dto.RateRegularDbDto;
import net.focik.hr.employee.infrastructure.mapper.JpaRateMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Qualifier("inMemory")
@Profile({"test"})
@Log
public class InMemoryRateRegularRepositoryAdapter implements RateRegularRepository {
    private Map<Integer, RateRegularDbDto> rateRegularHashMap = new HashMap<>();
    private JpaRateMapper jpaMapper = new JpaRateMapper();
    public int getSize(){
        return rateRegularHashMap.size();
    }

   @Override
    public RateRegular add(RateRegular rate, Integer idEmployee) {
        log.info("Try add into inMemoryDb rate: "+rate.toString());
        Integer id = rateRegularHashMap.size() + 1;
//        rate.setIdRate(id);
        rateRegularHashMap.put(id, jpaMapper.toDto(rate, idEmployee));
        log.info("Succssec id = " + rate.getIdRate());
        return rate;
    }


    @Override
    public List<RateRegular> findRateRegularByEmployeeId(Integer idEmployee) {
        return null;
    }

    @Override
    public void deleteRateRegularByIdEmployee(Integer idEmployee) {

    }

    @Override
    public void deleteRateRegularById(Integer id) {

    }

    @Override
    public RateRegular findRateRegularByIdAndDate(Integer id, LocalDate date) {
        return null;
    }

    // @Override
    public Optional<RateRegularDbDto> findRateRegularById(Integer id) {
        return Optional.ofNullable(rateRegularHashMap.get(id));
    }


//   // @Override
//    public List<RateRegularDto> findRateRegularByEmployeeId(Integer id) {
//        return rateRegularHashMap.entrySet()
//                .stream()
//                .filter(integerRateEntry -> integerRateEntry.getValue().getIdEmployee().equals(id))
//                .map(Map.Entry::getValue)
//                .collect(Collectors.toList());
//    }

    //@Override
    public Optional<RateRegularDbDto> findRateRegularByDate(LocalDate date) {
        return Optional.empty();
    }

}
