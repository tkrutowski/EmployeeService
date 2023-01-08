package net.focik.hr.employee.domain.worktimerecords;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import net.focik.hr.utils.prints.FontUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;


public class TimeSheetPdf {

    private TimeSheetPdf() {}

    public static String createPdf(LocalDate date, List<String> emploeeList, DaysToWork daysToWork) {
        final String filename = "ewidencja.pdf";
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();

            for (String emp : emploeeList) {
                document.add(createDateTime(date));
                document.add(employeeName(emp));
                document.add(createItemTable(date, daysToWork));
                document.newPage();
            }
            document.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            return null;
        }
        return filename;
    }

    private static PdfPTable createDateTime(LocalDate date) {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cellNr = new PdfPCell(new Phrase(String.format("EWIDENCJA CZASU PRACY - %s %s",
                date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, new Locale("pl", "PL")), date.getYear()),
                FontUtil.FONT_12_BOLD));
        cellNr.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellNr.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellNr.setPaddingBottom(5f);
        table.addCell(cellNr);

        table.setSpacingAfter(5f);
        return table;
    }

    private static PdfPTable employeeName(String emp) {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cellName = new PdfPCell(new Phrase(emp, FontUtil.FONT_12_BOLD));
        cellName.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellName.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellName.setPaddingBottom(5f);
        table.addCell(cellName);
        table.setSpacingAfter(20f);
        return table;
    }

    private static PdfPTable createItemTable(LocalDate date, DaysToWork daysToWork) {
        float[] columnWidths = {1, 3.5f, 3.2f, 3, 3, 3, 3, 3, 3};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        addTableHeader(table);
        addRows(table, date, daysToWork);
        addSummary(table, daysToWork);
        return table;
    }

    private static void addTableHeader(PdfPTable table) {
        //day
        PdfPCell cellDay = new PdfPCell(new Phrase("dzień", FontUtil.FONT_10_BOLD));
        cellDay.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellDay.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellDay.setPaddingBottom(5f);
        cellDay.setColspan(2);
        cellDay.setRowspan(2);
        table.addCell(cellDay);

        //signature
        PdfPCell cellSignature = new PdfPCell(new Phrase("podpis pracownika", FontUtil.FONT_10_BOLD));
        cellSignature.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellSignature.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellSignature.setPaddingBottom(5f);
        cellSignature.setRowspan(2);
        table.addCell(cellSignature);

        //work time
        PdfPCell cellWorkTime = new PdfPCell(new Phrase("czas pracy", FontUtil.FONT_10_BOLD));
        cellWorkTime.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellWorkTime.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellWorkTime.setPaddingBottom(5f);
        cellWorkTime.setColspan(3);
        table.addCell(cellWorkTime);

        //absence
        PdfPCell cellAbsence = new PdfPCell(new Phrase("nieobecności", FontUtil.FONT_10_BOLD));
        cellAbsence.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellAbsence.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellAbsence.setPaddingBottom(5f);
        cellAbsence.setColspan(3);
        table.addCell(cellAbsence);

        //work start
        PdfPCell cellWorkStart = new PdfPCell(new Phrase("rozp.", FontUtil.FONT_10_BOLD));
        cellWorkStart.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellWorkStart.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellWorkStart.setPaddingBottom(5f);
        table.addCell(cellWorkStart);

        //work end
        PdfPCell cellWorkEnd = new PdfPCell(new Phrase("zakończ.", FontUtil.FONT_10_BOLD));
        cellWorkEnd.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellWorkEnd.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellWorkEnd.setPaddingBottom(5f);
        table.addCell(cellWorkEnd);

        //work count
        PdfPCell cellWorkCount = new PdfPCell(new Phrase("ilość godz.", FontUtil.FONT_10_BOLD));
        cellWorkCount.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellWorkCount.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellWorkCount.setPaddingBottom(5f);
        table.addCell(cellWorkCount);

        //absence day off
        PdfPCell cellAbsenceDayOff = new PdfPCell(new Phrase("urlop", FontUtil.FONT_10_BOLD));
        cellAbsenceDayOff.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellAbsenceDayOff.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellAbsenceDayOff.setPaddingBottom(5f);
        table.addCell(cellAbsenceDayOff);

        //absence doc
        PdfPCell cellAbsenceDoc = new PdfPCell(new Phrase("zwiln. lek.", FontUtil.FONT_10_BOLD));
        cellAbsenceDoc.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellAbsenceDoc.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellAbsenceDoc.setPaddingBottom(5f);
        table.addCell(cellAbsenceDoc);

        //absence other
        PdfPCell cellAbsenceOther = new PdfPCell(new Phrase("inne", FontUtil.FONT_10_BOLD));
        cellAbsenceOther.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellAbsenceOther.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellAbsenceOther.setPaddingBottom(5f);
        table.addCell(cellAbsenceOther);
    }

    private static void addRows(PdfPTable table, LocalDate date, DaysToWork daysToWork) {
        int daysInMonth = date.lengthOfMonth();
        int emptyColumnCount = 7;
        for (int i = 1; i <= daysInMonth; i++) {
            LocalDate tempDate = LocalDate.of(date.getYear(), date.getMonth(), i);
            boolean isHoliday = daysToWork.getHolidays().keySet().stream()
                    .anyMatch(date1 -> date1.equals(tempDate));
            //day
            PdfPCell cellDay = new PdfPCell(new Phrase(String.valueOf(i), FontUtil.FONT_10));
            cellDay.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellDay.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellDay.setPaddingBottom(5f);
            if (isHoliday || isWeekend(tempDate)) {
                cellDay.setBackgroundColor(FontUtil.HEADER_COLOR);
                cellDay.setMinimumHeight(15);
            }
            table.addCell(cellDay);
            //day of week
            PdfPCell cellDayOfWeek = new PdfPCell(
                    new Phrase(tempDate.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pl", "PL")),
                            FontUtil.FONT_10));
            cellDayOfWeek.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellDayOfWeek.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellDayOfWeek.setPaddingBottom(5f);
            if (isHoliday || isWeekend(tempDate)) {
                cellDayOfWeek.setBackgroundColor(FontUtil.HEADER_COLOR);
                cellDayOfWeek.setMinimumHeight(15);
            }
            table.addCell(cellDayOfWeek);

            //empty cell
            for (int c = 0; c < emptyColumnCount; c++) {
                PdfPCell emptyCell = new PdfPCell(new Phrase("", FontUtil.FONT_10));
                if (isHoliday || isWeekend(tempDate))
                    emptyCell.setBackgroundColor(FontUtil.HEADER_COLOR);
                table.addCell(emptyCell);
            }
        }
    }

    private static void addSummary(PdfPTable table, DaysToWork daysToWork) {

        //regular hours
        PdfPCell cellSumRegular = new PdfPCell(new Phrase("suma godzin przepracowanych", FontUtil.FONT_10_BOLD));
        cellSumRegular.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellSumRegular.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellSumRegular.setPaddingBottom(5f);
        cellSumRegular.setBorder(0);
        cellSumRegular.setColspan(5);
        table.addCell(cellSumRegular);

        //empty cell
        for (int c = 0; c < 4; c++) {
            table.addCell(new PdfPCell(new Phrase("", FontUtil.FONT_10)));
        }

        //should work (hours)
        PdfPCell cellWorkTimeString = new PdfPCell(new Phrase("normatywny czas pracy: ", FontUtil.FONT_10_BOLD));
        cellWorkTimeString.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellWorkTimeString.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellWorkTimeString.setPaddingBottom(5f);
        cellWorkTimeString.setBorder(0);
        cellWorkTimeString.setColspan(5);
        table.addCell(cellWorkTimeString);

        PdfPCell cellWorkTime = new PdfPCell(new Phrase(String.format("%sh", daysToWork.getHoursToWork()), FontUtil.FONT_10_BOLD));
        cellWorkTime.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellWorkTime.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellWorkTime.setPaddingBottom(5f);
        table.addCell(cellWorkTime);

        for (int c = 0; c < 3; c++) {
            table.addCell(new PdfPCell(new Phrase("", FontUtil.FONT_10)));
        }

        //overtime hours
        PdfPCell cellSumOvertime = new PdfPCell(new Phrase("liczba nadgodzin: ", FontUtil.FONT_10_BOLD));
        cellSumOvertime.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellSumOvertime.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellSumOvertime.setPaddingBottom(5f);
        cellSumOvertime.setBorder(0);
        cellSumOvertime.setColspan(5);
        table.addCell(cellSumOvertime);

        //empty cell
        for (int c = 0; c < 4; c++) {
            table.addCell(new PdfPCell(new Phrase("", FontUtil.FONT_10)));
        }
    }

    private static boolean isWeekend(LocalDate tempDate) {
        return tempDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || tempDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }
}