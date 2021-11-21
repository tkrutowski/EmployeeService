package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.LoanInstallmentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


interface LoanInstallmentDtoRepository extends JpaRepository<LoanInstallmentDto, Integer> {

    @Query(value = "select * from rata_pozyczki where id_pozyczki = ?1 AND  data_raty like ?2%",
            nativeQuery = true)
    List<LoanInstallmentDto> findAllByIdLoanAndDate(Integer idLoan, String data);
    List<LoanInstallmentDto> findAllByIdLoan(Integer idLoan);
}
