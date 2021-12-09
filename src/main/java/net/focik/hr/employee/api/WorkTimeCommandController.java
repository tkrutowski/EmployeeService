package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.api.dto.DayOffTypeDto;
import net.focik.hr.employee.api.dto.IllnessTypeDto;
import net.focik.hr.employee.api.mapper.ApiDayOffMapper;
import net.focik.hr.employee.api.mapper.ApiIllnessMapper;
import net.focik.hr.employee.domain.workTimeRecords.WorkTimeFacade;
import net.focik.hr.employee.domain.workTimeRecords.share.DayOffType;
import net.focik.hr.employee.domain.workTimeRecords.share.IllnessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/worktime")
@CrossOrigin
class WorkTimeCommandController {

    private WorkTimeFacade workTimeFacade;
    ApiDayOffMapper dayOffMapper;
    ApiIllnessMapper illnessMapper;

    @GetMapping("/dayofftype")
    ResponseEntity<List<DayOffTypeDto>> getDayOffType() {

        DayOffType[] collect = (DayOffType.values());

        List<DayOffTypeDto> dayOffTypeDtos = Arrays.stream(collect)
                .map(dayOffType -> dayOffMapper.toDto(dayOffType))
                .collect(Collectors.toList());
        return new ResponseEntity<>(dayOffTypeDtos, HttpStatus.OK);
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
