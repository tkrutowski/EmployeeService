package net.focik.hr.employee.infrastructure.inMemory.db;

import net.focik.hr.employee.infrastructure.dto.LoanDto;
import net.focik.hr.employee.infrastructure.dto.LoanInstallmentDto;

import java.util.HashMap;


public class DataBaseLoans {
    public static HashMap<Integer, LoanDto> getLoansHashMap() {
        if(loanDtoHashMap == null)
            loanDtoHashMap = new HashMap<>();
        return loanDtoHashMap;
    }

    public static HashMap<Integer, LoanInstallmentDto> getLoanInstallmentTypesHashMap() {
        if(loanInstallmentDtoHashMap == null)
            loanInstallmentDtoHashMap = new HashMap<>();
        return loanInstallmentDtoHashMap;
    }

    private static HashMap<Integer, LoanDto> loanDtoHashMap;
    private static HashMap<Integer, LoanInstallmentDto> loanInstallmentDtoHashMap;

    private DataBaseLoans( ){ }
}
