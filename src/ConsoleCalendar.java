import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by employee on 11/8/16.
 */
public class ConsoleCalendar extends Calendar {

    public ConsoleCalendar() {

        this(LocalDate.now());
    }

    public ConsoleCalendar(LocalDate today) {

        this(today, DayOfWeek.MONDAY);
    }

    public ConsoleCalendar(DayOfWeek dayOfWeek) {

        this(LocalDate.now(), dayOfWeek);
    }

    public ConsoleCalendar(LocalDate today, DayOfWeek weekStart) {

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

        return "\t" + getToday().getMonth().getDisplayName(TextStyle.FULL, getLocale());

    }

    @Override
    public String printWeekendHeader(DayOfWeek dayOfWeek) {

        return
                "\u001B[31m"+
                dayOfWeek.getDisplayName(TextStyle.SHORT, getLocale())+
                "\u001B[0m" +
                        " ";
    }

    @Override
    public String printCommonHeader(DayOfWeek dayOfWeek) {

        return
                dayOfWeek.getDisplayName(TextStyle.SHORT, getLocale())+
                        " ";

    }

    @Override
    public String printGaps() {

        String result="";

           result+="\t";

        return result;
    }

    @Override
    public String printCommonDay(LocalDate date) {

        return date.getDayOfMonth()+"\t";
    }

    @Override
    public String printWeekend(LocalDate date) {
        return "\u001B[31m"+
                date.getDayOfMonth()+
                "\u001B[0m" + "\t" ;
    }

    @Override
    public String printCurrentDay(LocalDate date) {
        return "\u001B[32m"+
                date.getDayOfMonth()+
                "\u001B[0m" + "\t" ;
    }

    @Override
    public String nextLine() {
        return "\n";
    }
    @Override
    public void print() {

        String result ="";
        result +=
                printYearHeaderAndMonth()+
                        printDaysNames()+
                        printGapsBeforeFirstDayOfMonth()+
                        printDaysOfMonth();

        System.out.println(result);
    }


}
