package net.focik.hr.employee.infrastructure.inMemory.db;

import net.focik.hr.employee.infrastructure.dto.LoanDbDto;
import net.focik.hr.employee.infrastructure.dto.LoanInstallmentDbDto;

import java.util.HashMap;


public class DataBaseLoans {
    public static HashMap<Integer, LoanDbDto> getLoansHashMap() {
        if(loanDtoHashMap == null)
            loanDtoHashMap = new HashMap<>();
        return loanDtoHashMap;
    }

    public static HashMap<Integer, LoanInstallmentDbDto> getLoanInstallmentTypesHashMap() {
        if(loanInstallmentDtoHashMap == null)
            loanInstallmentDtoHashMap = new HashMap<>();
        return loanInstallmentDtoHashMap;
    }

    private static HashMap<Integer, LoanDbDto> loanDtoHashMap;
    private static HashMap<Integer, LoanInstallmentDbDto> loanInstallmentDtoHashMap;

    private DataBaseLoans( ){ }
}
