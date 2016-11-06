import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by employee on 11/3/16.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String monthToPrintStr = scanner.nextLine();

        Month monthToPrint = Calendar.getInputMonth(monthToPrintStr);

        DayOfWeek firstDayOfWeek = DayOfWeek.TUESDAY;

        Calendar.printCalendar(true, monthToPrint, firstDayOfWeek,DayOfWeek.SUNDAY,DayOfWeek.THURSDAY);

        //HTMLCalendarPrinter.printCalendarInHTML(DayOfWeek.MONDAY,DayOfWeek.SUNDAY);



    }
}