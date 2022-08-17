package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.EmployeeNotValidException;
import net.focik.hr.employee.domain.port.secondary.RateOvertimeRepository;
import net.focik.hr.employee.domain.port.secondary.RateRegularRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class RateService {

    private final RateRegularRepository rateRegularRepository;
    private final RateOvertimeRepository rateOvertimeRepository;

    RateRegular addRateRegular(RateRegular rateRegular, Integer idEmployee) {
        if (!validateRateRegular(rateRegular))
            throw new EmployeeNotValidException();
        return rateRegularRepository.add(rateRegular, idEmployee);
    }

    public RateOvertime addRateOvertime(RateOvertime rateOvertime, int employeeId) {
        if (!validateRateOvertime(rateOvertime))
            throw new EmployeeNotValidException();
        return rateOvertimeRepository.add(rateOvertime, employeeId);
    }

    private boolean validateRateRegular(RateRegular r) {
            if (r.getRateType() == null)
                return false;
            if (r.getDateFrom() == null)
                return false;
            if (r.getRateValue() == null)
                return false;
        return true;
    }

    private boolean validateRateOvertime(RateOvertime r) {
        if (r.getDateFrom() == null)
            return false;
        if (r.getRateValue() == null)
            return false;
        return true;
    }
}
