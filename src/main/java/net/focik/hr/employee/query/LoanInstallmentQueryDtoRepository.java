package net.focik.hr.employee.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


interface LoanInstallmentQueryDtoRepository extends JpaRepository<LoanInstallmentQueryDbDto, Integer> {

    @Query(value = "select * from LoanInstallment_view where id_pozyczki = ?1 AND  data_raty like ?2%",
            nativeQuery = true)
    List<LoanInstallmentQueryDbDto> findAllByIdLoanAndDate(Integer idLoan, String data);
}