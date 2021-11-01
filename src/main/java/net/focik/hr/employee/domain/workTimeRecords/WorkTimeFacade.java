package net.focik.hr.employee.domain.workTimeRecords;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class WorkTimeFacade {

    WorkService workService;

    public Integer addWorkTime(IWorkTime workTime){
        return workService.addWorkTime(workTime);
    }

//    Integer addRateRegular(RateRegular rateRegular, Integer idEmployee){
//        return rateRegularCommandService.addRateRegular(rateRegular, idEmployee);
//    }


}
