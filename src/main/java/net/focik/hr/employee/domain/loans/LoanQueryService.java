package net.focik.hr.employee.domain.loans;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.loans.port.secondary.LoanQueryRepository;
import net.focik.hr.employee.domain.share.LoanStatus;
import net.focik.hr.employee.query.LoanInstallmentQueryDto;
import net.focik.hr.employee.query.LoanQueryDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class LoanQueryService {

    private final LoanQueryRepository loanRepository;

    List<LoanQueryDto> findLoansByEmployee(int idEmployee, LoanStatus loanStatus) {
        List<LoanQueryDto> queryDtoList;
        if (idEmployee == 0) {
            queryDtoList = loanRepository.findAll();
        } else {
            queryDtoList = loanRepository.findLoanByEmployeeId(idEmployee);
        }

        if (loanStatus == null || LoanStatus.ALL.equals(loanStatus))
            return queryDtoList;

        queryDtoList = queryDtoList.stream()
                .filter(loan -> loan.getLoanStatus().equals(loanStatus.name()))
                .collect(Collectors.toList());

        return queryDtoList;
    }

    public List<LoanInstallmentQueryDto> findLoanInstallmentByEmployeeIdAndDate(Integer employeeId, LocalDate date) {
        return loanRepository.findLoanInstallmentByEmployeeIdAndDate(employeeId, date);
    }
}
