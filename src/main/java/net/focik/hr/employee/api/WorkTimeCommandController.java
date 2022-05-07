package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.api.dto.DayOffTypeDto;
import net.focik.hr.employee.api.dto.IllnessTypeDto;
import net.focik.hr.employee.api.dto.WorkTimeDto;
import net.focik.hr.employee.api.mapper.ApiDayOffMapper;
import net.focik.hr.employee.api.mapper.ApiIllnessMapper;
import net.focik.hr.employee.api.mapper.ApiWorkTimeMapper;
import net.focik.hr.employee.application.WorkTimeService;
import net.focik.hr.employee.domain.port.primary.AddWorkTimeUseCase;
import net.focik.hr.employee.domain.port.primary.GetWorkTimeRecordsUseCase;
import net.focik.hr.employee.domain.utils.PrivilegeHelper;
import net.focik.hr.employee.domain.workTimeRecords.IWorkTime;
import net.focik.hr.employee.domain.workTimeRecords.share.DayOffType;
import net.focik.hr.employee.domain.workTimeRecords.share.IllnessType;
import net.focik.hr.employee.domain.workTimeRecords.share.WorkType;
import net.focik.hr.team.domain.exceptions.AccessDeniedException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.focik.hr.employee.domain.utils.PrivilegeConstant.AUTHORITIES;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/employee/worktime")
//@CrossOrigin
class WorkTimeCommandController {

    private GetWorkTimeRecordsUseCase getWorkTimeRecordsUseCase;
    private AddWorkTimeUseCase addWorkTimeUseCase;
    private ApiDayOffMapper dayOffMapper;
    private ApiIllnessMapper illnessMapper;
    private ApiWorkTimeMapper workTimeMapper;
    private WorkTimeService workTimeService;

    @PostMapping("")
    public ResponseEntity addWorkTime(@RequestBody Object dto, @RequestParam(required = true) WorkType workType,
                                      @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        int i =0;
        final List<String> accessRole = List.of("HR_WORKTIME_WRITE_ALL","HR_WORKTIME_WRITE");

        if(!PrivilegeHelper.checkAccess(List.of(roles), accessRole)){
            throw new AccessDeniedException();
        }

        addWorkTimeUseCase.addWorkTime(workTimeMapper.toDomain((Map) dto, workType));

        return new ResponseEntity(HttpStatus.CREATED);
    }


    @GetMapping("/dayofftype")
    ResponseEntity<List<DayOffTypeDto>> getDayOffType() {

        DayOffType[] collect = (DayOffType.values());

        List<DayOffTypeDto> dayOffTypeDtos = Arrays.stream(collect)
                .map(dayOffType -> dayOffMapper.toDto(dayOffType))
                .collect(Collectors.toList());
        return new ResponseEntity<>(dayOffTypeDtos, HttpStatus.OK);
    }

    @GetMapping("/{idEmployee}")
    ResponseEntity<List<WorkTimeDto>> getWorkTime(@PathVariable Integer idEmployee, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        List<IWorkTime> iWorkTimes = getWorkTimeRecordsUseCase.getWorkTimeByEmployeeAndDate(idEmployee, date);
        List<WorkTimeDto> workTimeDtos = workTimeService.addEmptyDays(iWorkTimes, date);

        return new ResponseEntity<>(workTimeDtos, HttpStatus.OK);
    }



    @GetMapping("/illnesstype")
    ResponseEntity<List<IllnessTypeDto>> getIllnessType() {

        IllnessType[] collect = (IllnessType.values());

        List<IllnessTypeDto> illnessTypeDtos = Arrays.stream(collect)
                .map(illnessType -> illnessMapper.toDto(illnessType))
                .collect(Collectors.toList());
        return new ResponseEntity<>(illnessTypeDtos, HttpStatus.OK);
    }
//
//    @PostMapping("/rate_regular/")
//    public ResponseEntity<Boolean> addRateRegular(@RequestBody RateRegularDto rateRegularDto){
//        log.info("Try add new rate regular.");
//        modelMapper.map(rateRegularDto, );
//        Integer result = addNewEmployeeUseCase.addEmployee(employee);
//
//        log.info(result > 0 ? "Employee added with id = " + result : "No employee added!");
//
//        if(result <= 0)
//            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
//
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
//    }

}
