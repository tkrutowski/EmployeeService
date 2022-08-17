package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.api.dto.RateOvertimeDto;
import net.focik.hr.employee.api.dto.RateRegularDto;
import net.focik.hr.employee.api.mapper.ApiRateMapper;
import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.domain.port.primary.AddNewRateUseCase;
import net.focik.hr.employee.domain.port.primary.GetRateUseCase;
import net.focik.hr.employee.domain.utils.PrivilegeHelper;
import net.focik.hr.utils.exceptions.AccessDeniedException;
import net.focik.hr.utils.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static net.focik.hr.employee.domain.utils.PrivilegeConstant.AUTHORITIES;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/employee/rate")
@CrossOrigin
class RateController {

    private final AddNewRateUseCase addNewRateUseCase;
    private final ApiRateMapper mapper;
    private final GetRateUseCase getRateUseCase;
    private final ModelMapper modelMapper;


    @GetMapping("/{idEmployee}/regular")
    ResponseEntity<RateRegularDto> getEmployeeRateRegular(@PathVariable int idEmployee,
                                                     @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                     @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get regular rate for employee id: " + idEmployee );

        final List<String> accessRole = List.of("ROLE_ADMIN", "HR_RATE_READ_ALL");

//        if(!PrivilegeHelper.checkAccess(List.of(roles), accessRole)){
//            throw new AccessDeniedException();
//        }

        RateRegular lastRateRegular = getRateUseCase.getLastRateRegular(idEmployee);

        if (lastRateRegular == null)
            throw new ObjectNotFoundException("Pracownik nie ma jeszcze dodanej stawki.");

        return new ResponseEntity<>(modelMapper.map(lastRateRegular, RateRegularDto.class), HttpStatus.OK);
    }

    @GetMapping("/{idEmployee}/overtime")
    ResponseEntity<RateOvertimeDto> getEmployeeRateOvertime(@PathVariable int idEmployee,
                                                          @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                          @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get overtime rate for employee id: " + idEmployee );

        final List<String> accessRole = List.of("ROLE_ADMIN", "HR_RATE_READ_ALL");

//        if(!PrivilegeHelper.checkAccess(List.of(roles), accessRole)){
//            throw new AccessDeniedException();
//        }

        RateOvertime lastRateOvertime = getRateUseCase.getLastRateOvertime(idEmployee);

        if (lastRateOvertime == null)
            throw new ObjectNotFoundException("Pracownik nie ma jeszcze dodanej stawki.");

        return new ResponseEntity<>(modelMapper.map(lastRateOvertime, RateOvertimeDto.class), HttpStatus.OK);
    }


    @GetMapping("/{idEmployee}/regular/all")
    ResponseEntity<List<RateRegularDto>> getEmployeeRateRegularAll(@PathVariable int idEmployee,
                                                          @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                          @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get regular rate for employee id: " + idEmployee );

        final List<String> accessRole = List.of("ADMIN_READ_ALL", "ADMIN_READ", "HR_RATE_READ_ALL");

//        if(!PrivilegeHelper.checkAccess(List.of(roles), accessRole)){
//            throw new AccessDeniedException();
//        }

        List<RateRegular> allLastRateRegular = getRateUseCase.getAllLastRateRegular(idEmployee);

        if (allLastRateRegular.isEmpty())
            throw new ObjectNotFoundException("Pracownik nie ma jeszcze dodanej stawki.");

        return new ResponseEntity<>(allLastRateRegular.stream()
                .map(rateRegular ->  modelMapper.map(rateRegular, RateRegularDto.class))
                .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @GetMapping("/{idEmployee}/overtime/all")
    ResponseEntity<List<RateOvertimeDto>> getEmployeeRateOvertimeAll(@PathVariable int idEmployee,
                                                            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                            @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get overtime rate for employee id: " + idEmployee );

        final List<String> accessRole = List.of("ROLE_ADMIN", "HR_RATE_READ_ALL");

//        if(!PrivilegeHelper.checkAccess(List.of(roles), accessRole)){
//            throw new AccessDeniedException();
//        }

        List<RateOvertime> allLastRateOvertime = getRateUseCase.getAllLastRateOvertime(idEmployee);

        if (allLastRateOvertime.isEmpty())
            throw new ObjectNotFoundException("Pracownik nie ma jeszcze dodanej stawki.");

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
        final List<String> accessRole = List.of("ROLE_ADMIN", "ADMIN_WRITE", "HR_RATE_WRITE_ALL");

//        if(!PrivilegeHelper.checkAccess(List.of(roles), accessRole)){
//            throw new AccessDeniedException();
//        }

        RateRegular rateRegular = addNewRateUseCase.addRateRegular(idEmployee, mapper.toDomain(rateRegularDto));

        return new ResponseEntity<>(modelMapper.map(rateRegular,RateRegularDto.class), HttpStatus.CREATED);
    }

    @PostMapping("/{idEmployee}/overtime")
    public ResponseEntity<RateOvertimeDto> addRateOvertime(@PathVariable int idEmployee,
                                                         @RequestBody RateOvertimeDto rateOvertimeDto,
                                                         @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Try add new rate overtime.");
        final List<String> accessRole = List.of("ROLE_ADMIN", "ADMIN_WRITE", "HR_RATE_WRITE_ALL");

//        if(!PrivilegeHelper.checkAccess(List.of(roles), accessRole)){
//            throw new AccessDeniedException();
//        }

        RateOvertime rateOvertime = addNewRateUseCase.addRateOvertime(idEmployee, mapper.toDomain(rateOvertimeDto));

        return new ResponseEntity<>(modelMapper.map(rateOvertime,RateOvertimeDto.class), HttpStatus.CREATED);
    }

}
