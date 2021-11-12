package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.LoanInstallmentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


interface LoanInstallmentDtoRepository extends JpaRepository<LoanInstallmentDto, Integer> {

    List<LoanInstallmentDto> findAllByIdLoanAndDate(Integer idLoan, LocalDate date);
    List<LoanInstallmentDto> findAllByIdLoan(Integer idLoan);
}
