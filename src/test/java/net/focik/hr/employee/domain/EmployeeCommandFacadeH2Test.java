package net.focik.hr.employee.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmployeeCommandFacadeH2Test {

    @Autowired
    EmployeeCommandService employeeCommandService;

    @Test
    void getEmployee() {
        //given
        int ID_EMPLOYEE=222;

        //when
        Employee employee = employeeCommandService.findEmployeeById(ID_EMPLOYEE);

        //then
        assertEquals(ID_EMPLOYEE, employee.getId());
    }
}