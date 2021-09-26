package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.port.secondary.RateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
class RateService {

    private RateRepository rateRepository;

    Integer addRateRegular(RateRegular rate) {
        return rateRepository.add(rate);
    }

    Integer addRateOvertime(RateOvertime rate) {
        return rateRepository.add(rate);
    }


    public List<RateRegular> findAllRateRegularByEmployee(Integer id) {

        List<? extends Rate> rateByEmployeeId = rateRepository.findRateRegularByEmployeeId(id);

        return (List<RateRegular>) rateByEmployeeId;
    }

    public List<RateOvertime> findAllRateOvertimeByEmployee(Integer id) {
        List<? extends Rate> rateOvertimeEmployeeId = rateRepository.findRateOvertimeEmployeeId(id);

        return (List<RateOvertime>) rateOvertimeEmployeeId;
    }
}
