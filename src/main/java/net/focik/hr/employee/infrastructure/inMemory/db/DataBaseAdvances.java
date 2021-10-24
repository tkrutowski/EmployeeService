package net.focik.hr.employee.infrastructure.inMemory.db;

import net.focik.hr.employee.infrastructure.dto.AdvanceDto;

import java.util.HashMap;


public class DataBaseAdvances {
    public static HashMap<Integer, AdvanceDto> getAdvancesHashMap() {
        if(employeeHashMap == null)
            employeeHashMap = new HashMap<>();
        return employeeHashMap;
    }

    private static HashMap<Integer, AdvanceDto> employeeHashMap;

    private DataBaseAdvances( ){ }
}
