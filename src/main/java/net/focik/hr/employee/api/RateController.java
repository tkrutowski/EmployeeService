package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.api.dto.RateOvertimeDto;
import net.focik.hr.employee.api.dto.RateRegularDto;
import net.focik.hr.employee.api.mapper.ApiRateMapper;
import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.domain.exceptions.RateNotFoundException;
import net.focik.hr.employee.domain.port.primary.AddNewRateUseCase;
import net.focik.hr.employee.domain.port.primary.DeleteRateUseCase;
import net.focik.hr.employee.domain.port.primary.GetRateUseCase;
import net.focik.hr.employee.domain.port.primary.UpdateRateUseCase;
import net.focik.hr.utils.exceptions.AccessDeniedException;
import net.focik.hr.utils.exceptions.HttpResponse;
import net.focik.hr.utils.privileges.PrivilegeHelper;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static net.focik.hr.utils.privileges.PrivilegeHelper.*;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/employee/rate")
//@CrossOrigin
class RateController {

    public static final String RATE_NOT_SET = "Pracownik nie ma jeszcze dodanej stawki.";
    private final AddNewRateUseCase addNewRateUseCase;
    private final GetRateUseCase getRateUseCase;
    private final DeleteRateUseCase deleteRateUseCase;
    private final UpdateRateUseCase updateNewRateUseCase;
    private final ApiRateMapper mapper;
    private final ModelMapper modelMapper;


    @GetMapping("/{idEmployee}/regular")
    ResponseEntity<RateRegularDto> getEmployeeRateRegular(@PathVariable int idEmployee,
                                                          @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get regular rate for employee id: " + idEmployee);

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_RATE_READ_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        RateRegular lastRateRegular = getRateUseCase.getLastRateRegular(idEmployee);

        if (lastRateRegular == null)
            throw new RateNotFoundException(RATE_NOT_SET);

        return new ResponseEntity<>(modelMapper.map(lastRateRegular, RateRegularDto.class), HttpStatus.OK);
    }

    @GetMapping("/{idEmployee}/overtime")
    ResponseEntity<RateOvertimeDto> getEmployeeRateOvertime(@PathVariable int idEmployee,
                                                            @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get overtime rate for employee id: " + idEmployee);

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_RATE_READ_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        RateOvertime lastRateOvertime = getRateUseCase.getLastRateOvertime(idEmployee);

        if (lastRateOvertime == null)
            throw new RateNotFoundException(RATE_NOT_SET);

        return new ResponseEntity<>(modelMapper.map(lastRateOvertime, RateOvertimeDto.class), HttpStatus.OK);
    }


    @GetMapping("/{idEmployee}/regular/all")
    ResponseEntity<List<RateRegularDto>> getEmployeeRateRegularAll(@PathVariable int idEmployee,
                                                                   @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get regular rate for employee id: " + idEmployee);

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_RATE_READ_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        List<RateRegular> allLastRateRegular = getRateUseCase.getAllLastRateRegular(idEmployee);

        if (allLastRateRegular.isEmpty())
            throw new RateNotFoundException(RATE_NOT_SET);

        return new ResponseEntity<>(allLastRateRegular.stream()
                .map(rateRegular -> modelMapper.map(rateRegular, RateRegularDto.class))
                .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @GetMapping("/{idEmployee}/overtime/all")
    ResponseEntity<List<RateOvertimeDto>> getEmployeeRateOvertimeAll(@PathVariable int idEmployee,
                                                                     @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get overtime rate for employee id: " + idEmployee);

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_RATE_READ_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        List<RateOvertime> allLastRateOvertime = getRateUseCase.getAllLastRateOvertime(idEmployee);

        if (allLastRateOvertime.isEmpty())
            throw new RateNotFoundException(RATE_NOT_SET);

        return new ResponseEntity<>(allLastRateOvertime.stream()
                .map(rateOvertime -> modelMapper.map(rateOvertime, RateOvertimeDto.class))
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @PostMapping("/{idEmployee}/regular")
    public ResponseEntity<RateRegularDto> addRateRegular(@PathVariable int idEmployee,
                                                         @RequestBody RateRegularDto rateRegularDto,
                                                         @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Try add new rate regular.");
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_RATE_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        RateRegular rateToCheck = getRateUseCase.getRateRegularByEmployeeIdAndDate(idEmployee, LocalDate.parse(rateRegularDto.getDateFrom()));
        if (rateToCheck != null)
            return new ResponseEntity<>(modelMapper.map(rateToCheck, RateRegularDto.class), HttpStatus.CONFLICT);

        RateRegular rateRegular = addNewRateUseCase.addRateRegular(idEmployee, mapper.toDomain(rateRegularDto));

        return new ResponseEntity<>(modelMapper.map(rateRegular, RateRegularDto.class), HttpStatus.CREATED);
    }

    @PostMapping("/{idEmployee}/overtime")
    public ResponseEntity<RateOvertimeDto> addRateOvertime(@PathVariable int idEmployee,
                                                           @RequestBody RateOvertimeDto rateOvertimeDto,
                                                           @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Try add new rate overtime.");
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_RATE_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        RateOvertime rateOvertime = addNewRateUseCase.addRateOvertime(idEmployee, mapper.toDomain(rateOvertimeDto));

        return new ResponseEntity<>(modelMapper.map(rateOvertime, RateOvertimeDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{idEmployee}/regular")
    public ResponseEntity<RateRegularDto> editRateRegular(@PathVariable int idEmployee,
                                                          @RequestBody RateRegularDto rateRegularDto,
                                                          @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Try update rate regular.");
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_RATE_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        RateRegular rateRegular = updateNewRateUseCase.updateRateRegular(idEmployee, mapper.toDomain(rateRegularDto));

        return new ResponseEntity<>(modelMapper.map(rateRegular, RateRegularDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{idEmployee}/overtime")
    public ResponseEntity<RateRegularDto> editRateOvertime(@PathVariable int idEmployee,
                                                           @RequestBody RateOvertimeDto rateOvertimeDto,
                                                           @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Try update rate overtime.");
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_RATE_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        RateOvertime rateOvertime = updateNewRateUseCase.updateRateOvertime(idEmployee, mapper.toDomain(rateOvertimeDto));

        return new ResponseEntity<>(modelMapper.map(rateOvertime, RateRegularDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/regular/{idRate}")
    public ResponseEntity<HttpResponse> deleteRateRegular(@PathVariable int idRate,
                                                          @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try delete rate regular with id: " + idRate);
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_RATE_DELETE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        deleteRateUseCase.deleteRateRegularById(idRate);

        log.info("Deleted rate regular with id = " + idRate);

        return response(HttpStatus.NO_CONTENT, "Stawka podstawowa usunięta.");
    }

    @DeleteMapping("/overtime/{idRate}")
    public ResponseEntity<HttpResponse> deleteRateOvertime(@PathVariable int idRate,
                                                           @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try delete rate overtime with id: " + idRate);
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_RATE_DELETE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        deleteRateUseCase.deleteRateOvertimeById(idRate);

        log.info("Deleted rate overtime with id = " + idRate);

        return response(HttpStatus.NO_CONTENT, "Stawka nadgodzinowa usunięta.");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase(), message);
        return new ResponseEntity<>(body, status);
    }
}