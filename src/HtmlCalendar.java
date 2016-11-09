import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by employee on 11/8/16.
 */
public class HtmlCalendar extends Calendar {

    private static final String startOfFile = "<html> <head> <title>Calendar</title><link href=\"style.css\" rel=\"stylesheet\"></head> <body> <table><tr>";
    private static final String endOfFile = "</tr></table></body></html>";
    private static final String style = " .weekend{color: #FE0000;} .currentDay {color: #00FE00;} td{ width: 30px;}";

    public HtmlCalendar() {

        this(LocalDate.now());
    }

    public HtmlCalendar(LocalDate today) {

        this(today, DayOfWeek.MONDAY);
    }

    public HtmlCalendar(LocalDate today, DayOfWeek weekStart) {

        setWeekend(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY);
        setLocale(Locale.ENGLISH);

        setWeekStart(weekStart);
        setToday(today);
    }

    @Override
    public String printYearHeader() {
        return Integer.toString(getToday().getYear());
    }

    @Override
    public String printMonthHeader() {
        return getToday().getMonth().getDisplayName(TextStyle.FULL, getLocale());
    }

    @Override
    public String printWeekendHeader(DayOfWeek dayOfWeek) {

        return "<td class=\"weekend\">" + dayOfWeek.getDisplayName(TextStyle.SHORT, getLocale()) + "</td>\n";
    }

    @Override
    public String printCommonHeader(DayOfWeek dayOfWeek) {

        return "<td>" + dayOfWeek.getDisplayName(TextStyle.SHORT, getLocale()) + "</td>\n";
    }

    @Override
    public String printGaps() {
        String result = "";

        result+= "<td></td>";

        return result;
    }

    @Override
    public String printCommonDay(LocalDate date) {

        return "<td>" + date.getDayOfMonth() + "</td>";
    }

    @Override
    public String printWeekend(LocalDate date) {
        return "<td class=\"weekend\">" + date.getDayOfMonth() + "</td>\n";
    }

    @Override
    public String printCurrentDay(LocalDate date) {
        return "<td class=\"currentDay\">" + date.getDayOfMonth() + "</td>\n";
    }

    @Override
    public String nextLine() {

        return "</tr><tr>";
    }

    @Override
    public void print(){
        String htmlFileContent = "";
        htmlFileContent  =  startOfFile+
                printYearHeaderAndMonth()+
                printDaysNames()+
                printGapsBeforeFirstDayOfMonth()+
                printDaysOfMonth()+
                endOfFile;

        writeCalendarToHTMLFileAndStyleToCSS(htmlFileContent, style);

    }



    public static void writeCalendarToHTMLFileAndStyleToCSS(String htmlFileContent, String cssFileContent) {

        Path pathHTML = Paths.get("/home/employee/Desktop/HTML/Calendar.html");
        Path pathCSS = Paths.get("/home/employee/Desktop/HTML/style.css");

        File fileHTML = pathHTML.toFile();
        File fileCSS = pathCSS.toFile();

        try {
            fileHTML.delete();
            fileCSS.delete();

            if (fileHTML.createNewFile() & fileCSS.createNewFile()) {
                FileWriter fwHTML = new FileWriter(fileHTML);
                FileWriter fwCSS = new FileWriter(fileCSS);

                fwHTML.write(htmlFileContent);
                fwHTML.close();

                fwCSS.write(cssFileContent);
                fwCSS.close();

            }
        } catch (IOException e) {
            e.getMessage();
        }

    }
}
