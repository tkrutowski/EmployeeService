package net.focik.hr.employee.domain.port.primary;


public interface DeleteRateUseCase {
    void deleteRateRegularById(int id);

    void deleteRateOvertimeById(int id);
}
