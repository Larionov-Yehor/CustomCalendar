import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by employee on 11/3/16.
 */
public class Main {
    public static void main(String[] args) {

        LocalDate l = LocalDate.of(2016, 9,1);

        ConsoleCalendar consoleCalendar = new ConsoleCalendar(l);

       // consoleCalendar.setLocale(Locale.ITALIAN);
        consoleCalendar.setWeekStart(DayOfWeek.WEDNESDAY);

        consoleCalendar.print();

        HtmlCalendar htmlCalendar = new HtmlCalendar(LocalDate.now(), DayOfWeek.MONDAY);
        //htmlCalendar.setWeekStart(DayOfWeek.WEDNESDAY);
        htmlCalendar.print();

    }
}