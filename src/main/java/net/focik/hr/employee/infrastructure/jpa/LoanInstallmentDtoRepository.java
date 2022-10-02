package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.LoanInstallmentDbDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


interface LoanInstallmentDtoRepository extends JpaRepository<LoanInstallmentDbDto, Integer> {

    @Query(value = "select * from rata_pozyczki where id_pozyczki = ?1 AND  data_raty like ?2%",
            nativeQuery = true)
    List<LoanInstallmentDbDto> findAllByIdLoanAndDate(Integer idLoan, String data);

    List<LoanInstallmentDbDto> findAllByIdLoan(Integer idLoan);

    void deleteByIdLoan(Integer integer);
}
