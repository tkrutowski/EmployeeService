package net.focik.hr.employee.infrastructure.inMemory;

import net.focik.hr.employee.domain.Employee;

import javax.inject.Singleton;
import java.util.HashMap;


public class DataBaseEmployee {
    public static HashMap<Integer, Employee> getEmployeeHashMap() {
        if(employeeHashMap == null)
            employeeHashMap = new HashMap<>();
        return employeeHashMap;
    }

    private static HashMap<Integer, Employee> employeeHashMap;

    private DataBaseEmployee( ){ }
}
