package net.focik.hr.employee.domain.worktimerecords.port.primary;

import net.focik.hr.employee.domain.share.PrintTypePdf;

import java.time.LocalDate;
import java.util.List;

public interface PrintWorkTimeUseCase {
    String createPdf(List<Integer> idEmployees, LocalDate date, PrintTypePdf printTypePdf);
}
