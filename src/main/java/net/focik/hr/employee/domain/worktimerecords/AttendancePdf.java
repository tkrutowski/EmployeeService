package net.focik.hr.employee.domain.worktimerecords;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.WorkTime;
import net.focik.hr.employee.query.EmployeeQueryBasicDto;
import net.focik.hr.utils.prints.FontUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AttendancePdf {

    private static final int employeesPerPage = 6;

    private AttendancePdf() {
    }

    public static String createPdf(LocalDate date, Map<String, List<EmployeeQueryBasicDto>> emploeeList, DaysToWork daysToWork) {
        Document document = new Document();
        String filename = "lista_obecnosci.pdf";
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();
            String time;
            for (Map.Entry<String, List<EmployeeQueryBasicDto>> entry : emploeeList.entrySet()) {

                if (EmployeeType.CONSTRUCTION_MANAGER.name().equals(entry.getKey())) {
                    time = "7:00 - 15:00";
                } else {
                    WorkTime workTime = WorkTime.valueOf(entry.getKey());
                    if (workTime == WorkTime.FULL_TIME) {
                        time = "7:00 - 15:00";
                    } else {
                        time = "7:00 - 11:00";
                    }
                }

                for (int i = 0; i < Math.ceil(entry.getValue().size() / 6d); i++) {
                    document.add(createDateTime(date, time));
                    document.add(createCompanyName());

                    document.add(createItemTable(entry.getValue().subList(employeesPerPage * i,
                                    Math.min(entry.getValue().size(), employeesPerPage * (i + 1))),
//                                    entry.getValue().size() > employeesPerPage * (i + 1) ? employeesPerPage * (i + 1) : entry.getValue().size()),
                            date,
                            daysToWork));

                    document.newPage();
                }
            }
            document.close();
        } catch (IOException | com.itextpdf.text.DocumentException e) {
            e.printStackTrace();
            return null;
        }
        return filename;
    }

    private static PdfPTable createDateTime(LocalDate date, String time) {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cellNr = new PdfPCell(new Phrase(String.format("LISTA OBECNOŚCI - %s - Czas pracy %s", date, time), FontUtil.FONT_12_BOLD));
        cellNr.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellNr.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellNr.setPaddingBottom(5f);
        table.addCell(cellNr);

        table.setSpacingAfter(5f);
        return table;
    }

    private static PdfPTable createCompanyName() {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cellName = new PdfPCell(new Phrase("PROGAS Łukasz Morkowski", FontUtil.FONT_12_BOLD));
        cellName.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellName.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellName.setPaddingBottom(5f);
        table.addCell(cellName);
        table.setSpacingAfter(20f);
        return table;
    }

    private static PdfPTable createItemTable(List<EmployeeQueryBasicDto> employeeList, LocalDate date, DaysToWork daysToWork) {
        List<String> nameList = employeeList.stream()
                .map(basicDto -> basicDto.getFirstName() + " " + basicDto.getLastName())
                .collect(Collectors.toList());

        float[] columnWidths = {1, 3, 3, 3, 3, 3, 3};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        addTableHeader(table, nameList);
        addRows(table, date, daysToWork);
        return table;
    }

    private static void addTableHeader(PdfPTable table, List<String> headers) {
        PdfPCell day = new PdfPCell();
        day.setHorizontalAlignment(Element.ALIGN_CENTER);
        day.setVerticalAlignment(Element.ALIGN_MIDDLE);
        day.setPhrase(new Phrase("Dni", FontUtil.FONT_10_BOLD));
        table.addCell(day);
        headers.forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setVerticalAlignment(Element.ALIGN_MIDDLE);
            header.setPhrase(new Phrase(columnTitle, FontUtil.FONT_10_BOLD));
            table.addCell(header);
        });

        for (int i = headers.size(); i < 6; i++) {
            PdfPCell header = new PdfPCell();
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setVerticalAlignment(Element.ALIGN_MIDDLE);
            header.setPhrase(new Phrase("", FontUtil.FONT_10));
            table.addCell(header);
        }
    }

    private static void addRows(PdfPTable table, LocalDate date, DaysToWork daysToWork) {
        int daysInMonth = date.lengthOfMonth();
        int emptyColumnCount = 6;
        for (int i = 1; i <= daysInMonth; i++) {
            LocalDate tempDate = LocalDate.of(date.getYear(), date.getMonth(), i);
            boolean isHoliday = daysToWork.getHolidays().keySet().stream()
                    .anyMatch(date1 -> date1.equals(tempDate));
            //day
            PdfPCell cellDay = new PdfPCell(new Phrase(String.valueOf(i), FontUtil.FONT_10_BOLD));
            cellDay.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellDay.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellDay.setPaddingBottom(5f);
            if (isHoliday || isWeekend(tempDate)) {
                cellDay.setBackgroundColor(FontUtil.HEADER_COLOR);
                cellDay.setMinimumHeight(15);
            }
            table.addCell(cellDay);

            //empty cell
            for (int c = 0; c < emptyColumnCount; c++) {
                PdfPCell emptyCell = new PdfPCell(new Phrase("", FontUtil.FONT_10));
                if (isHoliday || isWeekend(tempDate))
                    emptyCell.setBackgroundColor(FontUtil.HEADER_COLOR);
                table.addCell(emptyCell);
            }
        }
    }

    private static boolean isWeekend(LocalDate tempDate) {
        return tempDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || tempDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }
}