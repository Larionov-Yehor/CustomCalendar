import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class HTMLCalendarPrinter {

    private static final String startOfFile = "<html> <head> <title>Calendar</title><link href=\"style.css\" rel=\"stylesheet\"></head> <body> <table>";
    private static final String endOfFile = "</table></body></html>";
    private static final String style = " .weekend{color: #FE0000;} .currentDay {color: #00FE00;} td{ width: 30px;}";


    public static void printCalendarInHTML(Month month, DayOfWeek firstDayOfWeek, DayOfWeek... weekends) {

        List<Day> daysToPrint = Day.createDays(month);
        String days = "";

        for (Day dayToPrint : daysToPrint) {

            if (Day.isFirstDayOfMonth(dayToPrint)) {
                days += printEmptyTabsBeforeFirstDayOfMonth(dayToPrint, firstDayOfWeek);
                days += printFirstDayOfMonth(dayToPrint, firstDayOfWeek, weekends);
                continue;
            }
            if (Day.isToday(dayToPrint)) {
                days += printToday(dayToPrint, firstDayOfWeek);
                continue;
            }
            if (Day.isWeekend(dayToPrint, weekends)) {
                days += printWeekend(dayToPrint, firstDayOfWeek);
                continue;
            }
            days += printCommonDay(dayToPrint, firstDayOfWeek);

        }

        String htmlFileContent =
                                startOfFile +
                                printShortDaysNames(firstDayOfWeek, weekends) +
                                days +
                                endOfFile;

        writeCalendarToHTMLFileAndStyleToCSS(htmlFileContent, style);

    }

    public static String printShortDaysNames(DayOfWeek firstDayOfWeek, DayOfWeek... weekendsParam) {

        String result = "<tr>";

        DayOfWeek headerDay = firstDayOfWeek;

        List<DayOfWeek> weekends = HeaderDays.convertWeekendVarArgToList(weekendsParam);

        for (int i = 1; i <= DayOfWeek.values().length; i++) {

            if (weekends.contains(headerDay)) {
                result += printWeekendHeader(headerDay);
            }

            else {
                result += printCommonHeader(headerDay);
            }
            headerDay = headerDay.plus(1);
        }

        return result + "</tr>\n";
    }


    public static String printFirstDayOfMonth(Day day, DayOfWeek firstDayOfWeek, DayOfWeek... weekends) {

        String result = "";

        int switchValue = 0;

        if (Day.isToday(day)) {
            switchValue = 1;
        }
        if (Day.isWeekend(day, weekends)) {
            switchValue = 2;
        }
        if (Day.isToday(day) & Day.isWeekend(day, weekends)) {
            switchValue = 1;
        }

        switch (switchValue) {
            case 0:
                result += printCommonDay(day, firstDayOfWeek);
                break;
            case 1:
                result += printToday(day, firstDayOfWeek);
                break;
            case 2:
                result += printWeekend(day, firstDayOfWeek);
                break;

        }

        return result;
    }

    public static String printWeekend(Day day, DayOfWeek firstDayOfWeek) {

        String result = "";
        result += "<td class=\"weekend\">" + day.getPrintValue() + "</td>\n";
        result += ifLastDayOfWeekAddNewRowToTable(day, firstDayOfWeek);

        return result;
    }

    public static String printToday(Day day, DayOfWeek firstDayOfWeek) {

        String result = "";
        result += "<td class=\"currentDay\">" + day.getPrintValue() + "</td>\n";
        result += ifLastDayOfWeekAddNewRowToTable(day, firstDayOfWeek);

        return result;
    }

    public static String printCommonDay(Day day, DayOfWeek firstDayOfWeek) {

        String result = "";
        result += "<td>" + day.getPrintValue() + "</td>\n";
        result += ifLastDayOfWeekAddNewRowToTable(day, firstDayOfWeek);

        return result;
    }

    public static String ifLastDayOfWeekAddNewRowToTable(Day day, DayOfWeek firstDayOfWeek) {

        String result = "";
        DayOfWeek lastDayOfWeek = firstDayOfWeek.plus(6);

        if (day.getDayOfWeek().equals(lastDayOfWeek)) {
            result += "</tr><tr>";
        }
        return result;
    }


    public static String printEmptyTabsBeforeFirstDayOfMonth(Day day, DayOfWeek firstDayOfWeekArg) {
        String result = "<tr>";

        DayOfWeek dayOfWeek = day.getDayOfWeek();
        DayOfWeek firstDayOfWeek = firstDayOfWeekArg;

        do {
            if (dayOfWeek.equals(firstDayOfWeek)) {
                break;
            }
            result += "<td></td>\t";

            firstDayOfWeek = firstDayOfWeek.plus(1);

        } while (!dayOfWeek.equals(firstDayOfWeek));


        return result;
    }



    public static String printWeekendHeader(DayOfWeek dayOfWeek){

        return "<td class=\"weekend\">" + dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "</td>\n";
    }

    public static String printCommonHeader(DayOfWeek dayOfWeek){

        return "<td>" + dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "</td>\n";
    }


    public static boolean writeCalendarToHTMLFileAndStyleToCSS(String htmlFileContent, String cssFileContent) {

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

                return true;

            } else {
                System.out.println("Files with such names are already exist.");
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return false;
    }
}