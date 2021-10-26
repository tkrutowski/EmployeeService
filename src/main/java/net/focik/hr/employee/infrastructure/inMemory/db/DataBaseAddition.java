package net.focik.hr.employee.infrastructure.inMemory.db;

import net.focik.hr.employee.infrastructure.dto.AdditionDto;

import java.util.HashMap;


public class DataBaseAddition {
    public static HashMap<Integer, AdditionDto> getAdditionsHashMap() {
        if(additionDtoHashMap == null)
            additionDtoHashMap = new HashMap<>();
        return additionDtoHashMap;
    }

    public static HashMap<Integer, String> getAdditionTypesHashMap() {
        if(additionTypeHashMap == null)
            additionTypeHashMap = new HashMap<>();
        return additionTypeHashMap;
    }

    private static HashMap<Integer, AdditionDto> additionDtoHashMap;
    private static HashMap<Integer, String> additionTypeHashMap;

    private DataBaseAddition( ){ }
}
