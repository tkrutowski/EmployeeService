package net.focik.hr.employee.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmployeeFacadeH2Test {

    @Autowired
    EmployeeService employeeService;

    @Test
    void getEmployee() {
        //given
        int ID_EMPLOYEE=222;

        //when
        Employee employee = employeeService.findEmployeeById(ID_EMPLOYEE);

        //then
        assertEquals(ID_EMPLOYEE, employee.getId());
    }
}