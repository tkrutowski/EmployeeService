package net.focik.hr.employee.domain.workTimeRecords;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
abstract class WorkTime {
    private int idEmployee;
    private LocalDate date;

    public int getIdEmployee() {
        return idEmployee;
    }

    public LocalDate getDate() {
        return date;
    }

    protected void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    protected void setDate(LocalDate date) {
        this.date = date;
    }
}
