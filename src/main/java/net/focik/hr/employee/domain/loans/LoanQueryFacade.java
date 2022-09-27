package net.focik.hr.employee.domain.loans;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.share.LoanStatus;
import net.focik.hr.employee.query.LoanQueryDto;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class LoanQueryFacade {

    LoanQueryService loanService;

    public List<LoanQueryDto> findLoansByEmployee(int idEmployee, LoanStatus loanStatus) {
        return loanService.findLoansByEmployee(idEmployee, loanStatus);
    }
}
