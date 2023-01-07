package net.focik.hr.employee.domain.salary;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import net.focik.hr.employee.domain.worktimerecords.DaysToWork;
import net.focik.hr.utils.prints.ConvertTimeUtil;
import net.focik.hr.utils.prints.FontUtil;
import net.focik.hr.utils.prints.MoneyUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;


public class SalarySheetPdf {

    private SalarySheetPdf() {
    }

    public static String createPdf(LocalDate date, Map<String, Salary> empSalaryMap, DaysToWork daysToWork) {
        Document document = new Document();
        String filename = "salary.pdf";
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();
            int index = 1;
            for (Map.Entry<String, Salary> entry : empSalaryMap.entrySet()) {
                document.add(createItemTable(entry, date, daysToWork));
                if (index % 5 == 0)
                    document.newPage();
                index++;
            }
            document.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            return null;
        }
        return filename;
    }

    private static PdfPTable createItemTable(Map.Entry<String, Salary> entry, LocalDate date, DaysToWork daysToWork) {
        float[] columnWidths = {3.3f, 4, 3};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        addLabel(table, date, entry.getKey());
        addLeft(table, entry.getValue(), daysToWork);
        addMiddle(table, entry.getValue());
        addRight(table, entry.getValue());
        table.setSpacingAfter(10f);
        return table;
    }

    private static void addLabel(PdfPTable table, LocalDate date, String employee) {
        PdfPCell cellNr = new PdfPCell(new Phrase(String.format("ROZLICZENIE CZASU PRACY - %s,  %s %s", employee,
                date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, new Locale("pl", "PL")), date.getYear()),
                FontUtil.FONT_10_BOLD));
        cellNr.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellNr.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellNr.setColspan(3);
        cellNr.setBorder(Rectangle.TOP);
        cellNr.setPaddingBottom(5f);
        table.addCell(cellNr);

        table.setSpacingAfter(5f);
    }

    private static void addLeft(PdfPTable table, Salary salary, DaysToWork daysToWork) {
        Paragraph parLeft = new Paragraph();
        //regular
        parLeft.add(createLine("Godziny przepracowane:  ", FontUtil.FONT_8, ConvertTimeUtil.mapMinutesToTime(salary.getWorkRegularMinutes()), FontUtil.FONT_8_BOLD));
        //over50
        parLeft.add(createLine("Godziny przepracowane (50%):  ", FontUtil.FONT_8, ConvertTimeUtil.mapMinutesToTime(salary.getWorkOvertime50Minutes()), FontUtil.FONT_8_BOLD));
        //over100
        parLeft.add(createLine("Godziny przepracowane (100%):  ", FontUtil.FONT_8, ConvertTimeUtil.mapMinutesToTime(salary.getWorkOvertime100Minutes()), FontUtil.FONT_8_BOLD));
        //dayOff paid
        parLeft.add(createLine("Godziny urlopowe (płatne):  ", FontUtil.FONT_8, ConvertTimeUtil.mapMinutesToTime(salary.getDayOffMinutesPay()), FontUtil.FONT_8_BOLD));
        //dayOffNotPaid
        parLeft.add(createLine("Godziny urlopowe (bezpłatne):  ", FontUtil.FONT_8, ConvertTimeUtil.mapMinutesToTime(salary.getDayOffMinutesFree()), FontUtil.FONT_8_BOLD));
        //sick 80
        parLeft.add(createLine("Godziny zasiłkowe (80%):  ", FontUtil.FONT_8, ConvertTimeUtil.mapMinutesToTime(salary.getIllnessMinutes80()), FontUtil.FONT_8_BOLD));
        //sick 100
        parLeft.add(createLine("Godziny zasiłkowe (100%):  ", FontUtil.FONT_8, ConvertTimeUtil.mapMinutesToTime(salary.getIllnessMinutes100()), FontUtil.FONT_8_BOLD));
        //sum
        parLeft.add(createLine("Suma godzin:  ", FontUtil.FONT_8_BOLD, salary.getWorkTimeAll(), FontUtil.FONT_8_BOLD));
        //empty line
        parLeft.add(createLine("  ", FontUtil.FONT_8_BOLD, " ", FontUtil.FONT_8_BOLD));
        //hours to work
        parLeft.add(createLine("Normatywny czas pracy:  ", FontUtil.FONT_8, daysToWork.getHoursToWork().toString(), FontUtil.FONT_8));
        PdfPCell cell = new PdfPCell(parLeft);
        cell.setBorder(0);
        table.addCell(cell);
    }

    private static void addMiddle(PdfPTable table, Salary salary) {
        Paragraph parMiddle = new Paragraph();
        //regular
        parMiddle.add(createLine("Za przepracowane:  ", FontUtil.FONT_8, MoneyUtils.mapMoneyToString(salary.getForRegularRate()), FontUtil.FONT_8_BOLD));
        //over50
        parMiddle.add(createLine("Za nadgodziny (50%):  ", FontUtil.FONT_8, MoneyUtils.mapMoneyToString(salary.getForOvertime50()), FontUtil.FONT_8_BOLD));
        //over100
        parMiddle.add(createLine("Za nadgodziny (100%):  ", FontUtil.FONT_8, MoneyUtils.mapMoneyToString(salary.getForOvertime100()), FontUtil.FONT_8_BOLD));
        //dayOff
        parMiddle.add(createLine("Za urlop (100%):  ", FontUtil.FONT_8, MoneyUtils.mapMoneyToString(salary.getForDayOff()), FontUtil.FONT_8_BOLD));
        //illness80
        parMiddle.add(createLine("Za zasiłek (80%):  ", FontUtil.FONT_8, MoneyUtils.mapMoneyToString(salary.getForIllness80()), FontUtil.FONT_8_BOLD));
        //illness100
        parMiddle.add(createLine("Za zasiłek (100%):  ", FontUtil.FONT_8, MoneyUtils.mapMoneyToString(salary.getForIllness100()), FontUtil.FONT_8_BOLD));
        //all
        parMiddle.add(createLine("Razem:  ", FontUtil.FONT_8_BOLD, MoneyUtils.mapMoneyToString(salary.getAmountForAllWorktime()), FontUtil.FONT_8_BOLD));
        //empty line
        parMiddle.add(createLine("  ", FontUtil.FONT_8_BOLD, " ", FontUtil.FONT_8_BOLD));
        //empty line
        parMiddle.add(createLine("  ", FontUtil.FONT_8_BOLD, " ", FontUtil.FONT_8_BOLD));
        //dayOff left
        parMiddle.add(createLine("Pozostało urlopu:  ", FontUtil.FONT_8, String.valueOf(salary.getDaysOffLeft()), FontUtil.FONT_8_BOLD));
        //loan left
        parMiddle.add(createLine("Pozostało do oddanie (pożyczka):  ", FontUtil.FONT_8, MoneyUtils.mapMoneyToString(salary.getLoansToPay()), FontUtil.FONT_8_BOLD));
        PdfPCell cell = new PdfPCell(parMiddle);
        cell.setBorder(0);
        table.addCell(cell);
    }

    private static void addRight(PdfPTable table, Salary salary) {
        Paragraph parRight = new Paragraph();
        //advance
        parRight.add(createLine("Zaliczki:  ", FontUtil.FONT_8, MoneyUtils.mapMoneyToString(salary.getAdvancesSum()), FontUtil.FONT_8_BOLD));
        //loanInstallment
        parRight.add(createLine("Pożyczki:  ", FontUtil.FONT_8, MoneyUtils.mapMoneyToString(salary.getLoanInstallmentSum()), FontUtil.FONT_8_BOLD));
        //additions
        parRight.add(createLine("Dodatki:  ", FontUtil.FONT_8, MoneyUtils.mapMoneyToString(salary.getAdditionsSum()), FontUtil.FONT_8_BOLD));
        //empty line
        parRight.add(createLine("  ", FontUtil.FONT_8_BOLD, " ", FontUtil.FONT_8_BOLD));
        //rate regular
        parRight.add(createLine("Stawka:  ", FontUtil.FONT_8, salary.getRateRegular().toString(), FontUtil.FONT_8_BOLD));
        //rate overtime
        parRight.add(createLine("Stawka nadgodzinowa:  ", FontUtil.FONT_8, salary.getRateOvertime().toString(), FontUtil.FONT_8_BOLD));
        //empty line
        parRight.add(createLine("  ", FontUtil.FONT_8_BOLD, " ", FontUtil.FONT_8_BOLD));
        //empty line
        parRight.add(createLine("  ", FontUtil.FONT_8_BOLD, " ", FontUtil.FONT_8_BOLD));
        //to pay
        parRight.add(createLine("Do wypłaty:  ", FontUtil.FONT_10, salary.getRateOvertime().toString(), FontUtil.FONT_10_BOLD));
        PdfPCell cell = new PdfPCell(parRight);
        cell.setBorder(0);
        table.addCell(cell);
    }

    private static Phrase createLine(String arg1, Font font1, String arg2, Font font2) {
        Phrase phrase = new Phrase();
        Chunk chunk1 = new Chunk(arg1, font1);
        Chunk chunk2 = new Chunk(arg2, font2);
        phrase.add(chunk1);
        phrase.add(chunk2);
        phrase.add(Chunk.NEWLINE);
        phrase.add(new Chunk(" ", FontUtil.FONT_EMPTY_SPACE));
        phrase.add(Chunk.NEWLINE);
        return phrase;
    }
}