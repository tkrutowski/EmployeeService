package net.focik.hr.employee.query;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


interface LoanQueryDtoRepository extends JpaRepository<LoanQueryDbDto, Integer> {

    List<LoanQueryDbDto> findAllByIdEmployee(Integer idEmployee);

    @Override
    List<LoanQueryDbDto> findAll();
}
