package net.focik.hr.employee.application;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.EmployeeFacade;
import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.domain.port.primary.AddNewRateUseCase;
import net.focik.hr.employee.domain.port.primary.DeleteRateUseCase;
import net.focik.hr.employee.domain.port.primary.GetRateUseCase;
import net.focik.hr.employee.domain.port.primary.UpdateRateUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class RateApiService implements AddNewRateUseCase, GetRateUseCase, DeleteRateUseCase, UpdateRateUseCase {
    EmployeeFacade commandFacade;


    //    @Override
    public void changeRateRegular(int idEmployee, LocalDate fromDate, BigDecimal rateValue) {
        //pobrać employee po id

        //sprawdzić czy value z takę datą już istnieje

        //jeżeli tak to aktualizacja

        //jeżeli nie to nowy

    }

    @Override
    public RateRegular getLastRateRegular(int employeeId) {
        return commandFacade.getLastRateRegular(employeeId);
    }

    @Override
    public RateRegular getRateRegularByEmployeeIdAndDate(int employeeId, LocalDate date) {
        return commandFacade.getRateRegularByEmployeeIdAndDate(employeeId, date);
    }

    @Override
    public RateOvertime getLastRateOvertime(int employeeId) {
        return commandFacade.getLastRateOvertime(employeeId);
    }

    @Override
    public List<RateRegular> getAllLastRateRegular(int employeeId) {
        return commandFacade.getAllLastRateRegular(employeeId);
    }

    @Override
    public List<RateOvertime> getAllLastRateOvertime(int employeeId) {
        return commandFacade.getAllLastRateOvertime(employeeId);
    }

    @Override
    public RateRegular addRateRegular(int employeeId, RateRegular rateRegular) {
        return commandFacade.addRateRegular(employeeId, rateRegular);
    }

    @Override
    public RateOvertime addRateOvertime(int employeeId, RateOvertime rateOvertime) {
        return commandFacade.addRateOvertime(employeeId, rateOvertime);
    }

    @Override
    public void deleteRateRegularById(int id) {
        commandFacade.deleteRateRegular(id);
    }

    @Override
    public void deleteRateOvertimeById(int id) {
        commandFacade.deleteRateOvertime(id);
    }

    @Override
    public RateRegular updateRateRegular(int employeeId, RateRegular rateRegular) {
        return commandFacade.updateRateRegular(employeeId, rateRegular);
    }

    @Override
    public RateOvertime updateRateOvertime(int employeeId, RateOvertime rateOvertime) {
        return commandFacade.updateRateOvertime(employeeId, rateOvertime);
    }
}