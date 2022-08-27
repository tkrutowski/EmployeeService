package net.focik.hr.employee.domain.worktimerecords;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DaysToWorkServiceTest {

    @Autowired
    DaysToWorkService daysToWorkService;

    @Test
    void findDaysToWorkByDate() {
        //given
        int YEAR = 2020;
        int MONTH = 11;
        int HOURS_TO_WORK = 160;

        //when
        DaysToWork daysToWorkByDate = daysToWorkService.findDaysToWorkByDate(YEAR, MONTH);

        //then
        assertEquals(2, daysToWorkByDate.getHolidays().size());
        assertEquals(HOURS_TO_WORK, daysToWorkByDate.getHoursToWork());
    }
}