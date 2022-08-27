package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.EmployeeNotValidException;
import net.focik.hr.employee.domain.port.secondary.RateOvertimeRepository;
import net.focik.hr.employee.domain.port.secondary.RateRegularRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public List<RateRegular> findRateRegularByEmployeeId(Integer id) {
        return rateRegularRepository.findRateRegularByEmployeeId(id);
    }

    public List<RateOvertime> findRateOvertimeEmployeeId(Integer id) {
        return rateOvertimeRepository.findRateOvertimeEmployeeId(id);
    }


    public void deleteRateRegularByIdEmployee(int idEmployee) {
        rateRegularRepository.deleteRateRegularByIdEmployee(idEmployee);
    }

    public void deleteRateOvertimeByIdEmployee(int idEmployee) {
        rateOvertimeRepository.deleteRateOvertimeByIdEmployee(idEmployee);
    }

    private boolean validateRateRegular(RateRegular r) {
        return (r.getRateType() != null) || (r.getDateFrom() != null) || (r.getRateValue() != null);
    }

    private boolean validateRateOvertime(RateOvertime r) {
        return (r.getDateFrom() != null) || (r.getRateValue() != null);
    }

    public void deleteRateRegularById(int id) {
        rateRegularRepository.deleteRateRegularById(id);
    }

    public void deleteRateOvertimeById(int id) {
        rateOvertimeRepository.deleteRateOvertimeById(id);
    }

    public RateRegular getRateRegularByEmployeeIdAndDate(int employeeId, LocalDate date) {
        return rateRegularRepository.findRateRegularByIdAndDate(employeeId, date);
    }
}
