package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.port.secondary.RateOvertimeRepository;
import net.focik.hr.employee.domain.port.secondary.RateRegularRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
class RateOvertimeService {

    private RateOvertimeRepository rateOvertimeRepository;


    Integer addRateOvertime(RateOvertime rate) {
        return rateOvertimeRepository.add(rate);
    }

    public List<RateOvertime> findAllRateOvertimeByEmployee(Integer id) {
        List<RateOvertime> rateOvertimeEmployeeId = rateOvertimeRepository.findRateOvertimeEmployeeId(id);

        return (List<RateOvertime>) rateOvertimeEmployeeId;
    }
}
