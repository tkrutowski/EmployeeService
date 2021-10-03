package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.port.secondary.RateRegularRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
class RateRegularService {

    private RateRegularRepository rateRegularRepository;

    Integer addRateRegular(RateRegular rate) {
        return rateRegularRepository.add(rate);
    }



    public List<RateRegular> findAllRateRegularByEmployee(Integer id) {

        List<RateRegular> rateByEmployeeId = rateRegularRepository.findRateRegularByEmployeeId(id);

        return rateByEmployeeId;
    }

}
