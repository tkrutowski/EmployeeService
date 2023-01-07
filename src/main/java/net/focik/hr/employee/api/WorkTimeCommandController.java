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
import net.focik.hr.employee.domain.share.PrintTypePdf;
import net.focik.hr.employee.domain.worktimerecords.IWorkTime;
import net.focik.hr.employee.domain.worktimerecords.port.primary.AddWorkTimeUseCase;
import net.focik.hr.employee.domain.worktimerecords.port.primary.DeleteWorkTimeUseCase;
import net.focik.hr.employee.domain.worktimerecords.port.primary.GetWorkTimeRecordsUseCase;
import net.focik.hr.employee.domain.worktimerecords.port.primary.PrintWorkTimeUseCase;
import net.focik.hr.employee.domain.worktimerecords.share.DayOffType;
import net.focik.hr.employee.domain.worktimerecords.share.IllnessType;
import net.focik.hr.employee.domain.worktimerecords.share.WorkType;
import net.focik.hr.utils.exceptions.AccessDeniedException;
import net.focik.hr.utils.exceptions.HttpResponse;
import net.focik.hr.utils.privileges.PrivilegeHelper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
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

    private final GetWorkTimeRecordsUseCase getWorkTimeRecordsUseCase;
    private final AddWorkTimeUseCase addWorkTimeUseCase;
    private final ApiDayOffMapper dayOffMapper;
    private final ApiIllnessMapper illnessMapper;
    private final ApiWorkTimeMapper workTimeMapper;
    private final WorkTimeApiService workTimeApiService;
    private final DeleteWorkTimeUseCase deleteWorkTimeUseCase;
    private final PrintWorkTimeUseCase printWorkTimeUseCase;


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


    @PostMapping()
    public ResponseEntity<HttpResponse> addWorkTime(@RequestBody WorkTimeDto workTimeDto, @RequestParam WorkType workType,
                                                    @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_WORKTIME_WRITE_ALL, HR_WORKTIME_WRITE);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        addWorkTimeUseCase.addWorkTime(workTimeMapper.toDomain(workTimeDto, workType));

        return response(HttpStatus.CREATED, "Czas pracy dodany.");
    }


    @DeleteMapping("/{idEmployee}")
    public ResponseEntity<HttpResponse> deleteWorkTime(@PathVariable int idEmployee,
                                                       @RequestParam("type") WorkType workType,
                                                       @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                       @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try delete worktime with idEmployee: {} and workType: {} and date: {}", idEmployee, workType, date);
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_WORKTIME_DELETE_ALL, HR_WORKTIME_DELETE);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        deleteWorkTimeUseCase.deleteWorkTime(idEmployee, date, workType);

        log.info("Deleted worktime with idEmployee: {} and workType: {} and date: {}", idEmployee, workType, date);
        return response(HttpStatus.NO_CONTENT, "Czas pracy usunięty.");
    }

    @GetMapping("/illnesstype")
    ResponseEntity<List<IllnessTypeDto>> getIllnessType() {

        IllnessType[] collect = (IllnessType.values());

        List<IllnessTypeDto> illnessTypeDtos = Arrays.stream(collect)
                .map(illnessMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(illnessTypeDtos, HttpStatus.OK);
    }

    @GetMapping("/dayofftype")
    ResponseEntity<List<DayOffTypeDto>> getDayOffType() {

        DayOffType[] collect = (DayOffType.values());

        List<DayOffTypeDto> dayOffTypeDtos = Arrays.stream(collect)
                .map(dayOffMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dayOffTypeDtos, HttpStatus.OK);
    }

    @GetMapping("/print")
    public ResponseEntity<?> downloadFile(@RequestParam List<Integer> idEmployees,
                                          @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                          @RequestParam("print") PrintTypePdf printTypePdf,
                                          @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Try print {} for date {}", printTypePdf, date);
        List<String> accessRole = null;
        switch (printTypePdf) {
            case TIME_SHEET:
            case ATTENDANCE:
                accessRole = List.of(ROLE_ADMIN, HR_WORKTIME_READ_ALL);
                break;
            case WORK_SHEET:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + printTypePdf);
        }

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }
        String fileName = printWorkTimeUseCase.createPdf(idEmployees, date, printTypePdf);

        Resource resource;
        try {
            Path path = Path.of(fileName);
            resource = new UrlResource(path.toUri());
        } catch (IOException e) {
            return response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase(), message);
        return new ResponseEntity<>(body, status);
    }
}