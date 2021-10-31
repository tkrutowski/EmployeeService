package net.focik.hr.employee.domain.workTimeRecords;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class WorkTimeCommandFacade {

    WorkTimeCommandService workTimeCommandService;

    public Integer addWorkTime(IWorkTime workTime){
        return workTimeCommandService.addWorkTime(workTime);
    }

//    Integer addRateRegular(RateRegular rateRegular, Integer idEmployee){
//        return rateRegularCommandService.addRateRegular(rateRegular, idEmployee);
//    }


}
