
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.Locale;
import java.util.Set;


/**
 * Created by employee on 11/3/16. abc
 */

public class Calendar {

    static LocalDate dateToday = LocalDate.now();
    static String defaultPrintParameter = "%4d";


    YearMonth month;
    DayOfWeek weekStart;

    LocalDate today;
    Set<DayOfWeek> weekend;
    Locale locale;

    public Calendar(){
    this(YearMonth.now());
    }

    public Calendar(YearMonth month){
    this(month, LocalDate.now());
    }

    public Calendar(YearMonth month, LocalDate today){
        this.month = month;
        this.today = today;

    }

    public void setWeekend(DayOfWeek ... weekend){
    
    }

    public void setWeekStart(DayOfWeek dayOfWeek){

    }
    public void setLocale(Locale locale){

    }


    public void printCalendar(WayToPrint wayToPrint, Month month, DayOfWeek firstDayOfWeek, DayOfWeek... weekend) {

        if(wayToPrint.equals(WayToPrint.HTML)){
            HTMLCalendarPrinter.printCalendarInHTML(month,firstDayOfWeek,weekend);
        }

        else {
            printCalendarToConsole(month,firstDayOfWeek,weekend);

        }
    }

    public static void printCalendarToConsole(Month month, DayOfWeek firstDayOfWeek, DayOfWeek...weekend){
        List<Day> daysToPrint = Day.createDays(month);
        HeaderDays.printHeaderDays(firstDayOfWeek, weekend);
        printDays(daysToPrint, firstDayOfWeek, weekend);
    }



    public static void printDays(List<Day> daysToPrint, DayOfWeek firstDayOfWeek, DayOfWeek... weekend) {

        for (Day day : daysToPrint) {

            if (Day.isFirstDayOfMonth(day)) {
                Day.printFirstDayOfMonth(day, firstDayOfWeek,weekend);
                continue;
            }

            if (Day.isToday(day)) {
                Day.printToday(day, defaultPrintParameter, firstDayOfWeek);
                continue;
            }

            if (Day.isWeekend(day, weekend)) {
                Day.printWeekend(day, defaultPrintParameter, firstDayOfWeek);
                continue;
            }
            Day.printCommonDay(day, defaultPrintParameter, firstDayOfWeek);
        }
    }

    public static Month getInputMonth(String month) {

        if (month.isEmpty()) {
            Month monthToPrint = dateToday.getMonth();
            return monthToPrint;
        }
        return Month.valueOf(month);
    }


}