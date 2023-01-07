package net.focik.hr.employee.domain.salary.port.primary;

import net.focik.hr.employee.domain.share.PrintTypePdf;

import java.time.LocalDate;
import java.util.List;

public interface PrintSalaryUseCase {
    String createPdf(List<Integer> idEmployees, LocalDate date, PrintTypePdf printTypePdf);
}
