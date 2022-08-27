package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.api.dto.DayOffTypeDto;
import net.focik.hr.employee.api.dto.IllnessTypeDto;
import net.focik.hr.employee.api.dto.WorkTimeDto;
import net.focik.hr.employee.api.mapper.ApiDayOffMapper;
import net.focik.hr.employee.api.mapper.ApiIllnessMapper;
import net.focik.hr.employee.api.mapper.ApiWorkTimeMapper;
import net.focik.hr.employee.application.WorkTimeApiService;
import net.focik.hr.employee.domain.port.primary.AddWorkTimeUseCase;
import net.focik.hr.employee.domain.port.primary.GetWorkTimeRecordsUseCase;
import net.focik.hr.employee.domain.worktimerecords.IWorkTime;
import net.focik.hr.employee.domain.worktimerecords.share.DayOffType;
import net.focik.hr.employee.domain.worktimerecords.share.IllnessType;
import net.focik.hr.employee.domain.worktimerecords.share.WorkType;
import net.focik.hr.utils.exceptions.AccessDeniedException;
import net.focik.hr.utils.privileges.PrivilegeHelper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.focik.hr.utils.privileges.PrivilegeHelper.*;

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
    private WorkTimeApiService workTimeApiService;

    @PostMapping("")
    public ResponseEntity<WorkTimeDto> addWorkTime(@RequestBody WorkTimeDto workTimeDto, @RequestParam WorkType workType,
                                                   @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_WORKTIME_WRITE_ALL, HR_WORKTIME_WRITE);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        addWorkTimeUseCase.addWorkTime(workTimeMapper.toDomain(workTimeDto, workType));

        return new ResponseEntity<>(HttpStatus.CREATED);
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
    ResponseEntity<List<WorkTimeDto>> getWorkTime(@PathVariable Integer idEmployee,
                                                  @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                  @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_WORKTIME_READ_ALL, HR_WORKTIME_READ);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        List<IWorkTime> iWorkTimes = getWorkTimeRecordsUseCase.getWorkTimeByEmployeeAndDate(idEmployee, date);
        List<WorkTimeDto> workTimeDtos = workTimeApiService.addEmptyDays(iWorkTimes, date);

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

}
