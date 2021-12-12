package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.api.dto.DayOffTypeDto;
import net.focik.hr.employee.api.dto.IllnessTypeDto;
import net.focik.hr.employee.api.dto.WorkTimeDto;
import net.focik.hr.employee.api.mapper.ApiDayOffMapper;
import net.focik.hr.employee.api.mapper.ApiIllnessMapper;
import net.focik.hr.employee.api.mapper.ApiWorkTimeMapper;
import net.focik.hr.employee.domain.port.primary.GetWokTimeRecordsUseCase;
import net.focik.hr.employee.domain.workTimeRecords.DayOff;
import net.focik.hr.employee.domain.workTimeRecords.IWorkTime;
import net.focik.hr.employee.domain.workTimeRecords.WorkTimeFacade;
import net.focik.hr.employee.domain.workTimeRecords.share.DayOffType;
import net.focik.hr.employee.domain.workTimeRecords.share.IllnessType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/worktime")
@CrossOrigin
class WorkTimeCommandController {

    //    private WorkTimeFacade workTimeFacade;
    private GetWokTimeRecordsUseCase getWokTimeRecordsUseCase;
    ApiDayOffMapper dayOffMapper;
    ApiIllnessMapper illnessMapper;
    ApiWorkTimeMapper workTimeMapper;

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

        List<IWorkTime> iWorkTimes = getWokTimeRecordsUseCase.getWorkTimeByEmployeeAndDate(idEmployee, date);


        return new ResponseEntity<>(addEmptyDays(iWorkTimes, date), HttpStatus.OK);
    }


    /** Add empty days to complete the calendar
      * @param workTimes
     * @param date
     * @return completed and sort by date full month
     */
    private List<WorkTimeDto> addEmptyDays(List<IWorkTime> workTimes, LocalDate date) {
        int daysInMonth = date.lengthOfMonth();
        List<WorkTimeDto> workTimeDtos = workTimes.stream()
                .map(workTime -> workTimeMapper.toDto(workTime))
                .collect(Collectors.toList());

        boolean isExist;
        for (int i = 1; i <= daysInMonth; i++) {
            isExist = false;
            for (WorkTimeDto dto : workTimeDtos) {
                if (Integer.parseInt(dto.getDate().substring(0, 2)) == i) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                workTimeDtos.add(WorkTimeDto.builder()
                        .date(LocalDate.of(date.getYear(), date.getMonth().getValue(), i).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                        .dayOfWeek(LocalDate.of(date.getYear(), date.getMonth().getValue(), i).format(DateTimeFormatter.ofPattern("EEEE", new Locale("pl", "PL"))))
                        .build());
            }
        }

        return workTimeDtos.stream()
                .sorted(Comparator.comparing(WorkTimeDto::getDate))
                .collect(Collectors.toList());
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
