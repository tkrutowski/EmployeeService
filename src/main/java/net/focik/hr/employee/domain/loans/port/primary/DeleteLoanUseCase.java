package net.focik.hr.employee.domain.loans.port.primary;

public interface DeleteLoanUseCase {
    void deleteLoanById(int id);

    void deleteLoanInstallmentById(int id);
}
