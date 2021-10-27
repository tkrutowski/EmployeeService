package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.loan.Loan;
import net.focik.hr.employee.domain.loan.LoanInstallment;
import net.focik.hr.employee.domain.loan.port.secondary.LoanQueryRepository;
import net.focik.hr.employee.infrastructure.dto.JpaMapper;
import net.focik.hr.employee.infrastructure.dto.LoanDto;
import net.focik.hr.employee.infrastructure.inMemory.db.DataBaseLoans;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component()
@Profile({"test"})
@Log
public class InMemoryLoanQueryRepositoryAdapter implements LoanQueryRepository {

    JpaMapper jpaMapper = new JpaMapper();

    @Override
    public Optional<Loan> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<LoanInstallment> findLoanInstallmentByEmployeeIdAndDate(Integer employeeId, LocalDate date) {

        List<LoanInstallment> loanInstallments = new ArrayList<>();

        List<LoanDto> loanDtosByIdEmployee = DataBaseLoans.getLoansHashMap()
                .entrySet()
                .stream()
                .map(dtoEntry -> dtoEntry.getValue())
                .filter(loanDto -> loanDto.getIdEmployee().equals(employeeId))
                .collect(Collectors.toList());

        for (LoanDto loanDto : loanDtosByIdEmployee) {
            DataBaseLoans.getLoanInstallmentTypesHashMap().entrySet().stream()
                    .filter(e -> loanDto.getId().equals(e.getValue().getIdLoan()))
                    .map(Map.Entry::getValue)
                    .filter(loanInstallmentDto -> loanInstallmentDto.getDate().getYear() == date.getYear())
                    .filter(loanInstallmentDto -> loanInstallmentDto.getDate().getMonth() == date.getMonth())
                    .map(loanInstallmentDto -> jpaMapper.toDomain(loanInstallmentDto))
                    .forEach(loanInstallment -> loanInstallments.add(loanInstallment));
        }
        return loanInstallments;
    }


//    public List<LoanInstallment> findLoanInstallmentByEmployeeIdAndDate(Integer employeeId, LocalDate date) {
//        return DataBaseLoans.getLoansHashMap()
//                .entrySet()
//                .stream()
//                .map(dtoEntry -> dtoEntry.getValue())
//                .filter(loanDto -> loanDto.getIdEmployee().equals(employeeId))
//                .filter(loanDto -> loanDto.getDate().getYear() == date.getYear())
//                .filter(loanDto -> loanDto.getDate().getMonth() == date.getMonth())
//                .map(loanDto -> jpaMapper.toDomain(loanDto, (Set<LoanInstallment>) DataBaseLoans.getLoanInstallmentTypesHashMap().entrySet().stream()
//                        .filter(e -> loanDto.getId().equals(e.getKey()))
//                        .map(Map.Entry::getValue)
//                        .map(loanInstallmentDto -> jpaMapper.toDomain(loanInstallmentDto))
//                        .collect(Collectors.toList())))
//                .collect(Collectors.toList());
//    }
}
