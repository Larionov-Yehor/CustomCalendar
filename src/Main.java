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
       // Scanner scanner = new Scanner(System.in);
        //String monthToPrintStr = scanner.nextLine();

       // Month monthToPrint = Calendar.getInputMonth(monthToPrintStr);

       // DayOfWeek firstDayOfWeek = DayOfWeek.SUNDAY;





        YearMonth y=YearMonth.now();
        LocalDate l = LocalDate.now();
        ConsoleCalendar consoleCalendar = new ConsoleCalendar();
        System.out.println(consoleCalendar.printDaysNames());
        System.out.print(consoleCalendar.printFirstDay());

        HtmlCalendar htmlCalendar = new HtmlCalendar();
        //htmlCalendar.setWeekStart(DayOfWeek.SUNDAY);
        htmlCalendar.printHtml();


        //calendar.print(WayToPrint.CONSOLE, monthToPrint, firstDayOfWeek,DayOfWeek.SUNDAY,DayOfWeek.MONDAY);

    }
}