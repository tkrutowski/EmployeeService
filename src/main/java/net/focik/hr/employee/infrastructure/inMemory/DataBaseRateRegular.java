package net.focik.hr.employee.infrastructure.inMemory;

import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.RateRegular;

import java.util.HashMap;


public class DataBaseRateRegular {
    public static HashMap<Integer, RateRegular> getRateRegularHashMap() {
        if(employeeHashMap == null)
            employeeHashMap = new HashMap<>();
        return employeeHashMap;
    }

    private static HashMap<Integer, RateRegular> employeeHashMap;

    private DataBaseRateRegular( ){ }
}
