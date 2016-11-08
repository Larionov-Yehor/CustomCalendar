
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;


/**
 * Created by employee on 11/3/16. abc
 */

public abstract class Calendar {

    static LocalDate dateToday = LocalDate.now();
    static String defaultPrintParameter = "%4d";


    private YearMonth month;
    private DayOfWeek weekStart;

    private LocalDate today;
    private Set<DayOfWeek> weekend = new HashSet<>();
    private Locale locale;

    public Calendar() {
        this(YearMonth.now());
    }

    public Calendar(YearMonth month) {
        this(month, LocalDate.now());
    }

    public Calendar(YearMonth month, LocalDate today) {
        this.month = month;
        this.today = today;

        setWeekend(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        setWeekStart(DayOfWeek.MONDAY);
        setLocale(Locale.ENGLISH);
    }


    public abstract String printWeekendHeader(DayOfWeek dayOfWeek);
    public abstract String printCommonHeader(DayOfWeek dayOfWeek);
    public abstract String printGaps();
    public abstract String printCommonDay(String day);

    public String print(){

        return  //printMonthAndYear()+
                printDaysNames()+
               // printFirstDayOfMonth()+
                printDays();
    }

    public String printFirstDay(){

        String result = "";

        int param = generateEmptySpacesBeforeFirstDayOfMonth();

        for(int i=1; i<=param; i++){
  
            result += printGaps();

            }

            result += printCommonDay("1");

        return result;
    }

    public int generateEmptySpacesBeforeFirstDayOfMonth(){

        int result = 0;

        DayOfWeek firstDayOfWeek = getWeekStart();
        DayOfWeek dayOfWeek = getToday().withDayOfMonth(1).getDayOfWeek();


        do{
            if(dayOfWeek.equals(firstDayOfWeek)){
                break;
            }

            result+=1;

            firstDayOfWeek = firstDayOfWeek.plus(1);

        }while (!dayOfWeek.equals(firstDayOfWeek));

    return result;

    }

    public String printDaysNames() {

        String res = "";

        DayOfWeek dayOfWeek = getWeekStart();
        Set<DayOfWeek> weekend = getWeekend();



        for(int i=1; i<=DayOfWeek.values().length; i++){

            if(weekend.contains(dayOfWeek)){
                res+=printWeekendHeader(dayOfWeek);
            }
            else {
                res+=printCommonHeader(dayOfWeek);
            }
            dayOfWeek = dayOfWeek.plus(1);

        }

        return res;
    }

    public boolean isLastDayOfWeek(){

        DayOfWeek lastDayOfWeek = getWeekStart().plus(6);

       /* if(____.equals(lastDayOfWeek)){
            return true;
        }*/

      return false;}



        public String printMonthAndYear(){
       return null;
   }


    public String printDays(){
        return null;
    }





















    public void setWeekend(DayOfWeek... weekend) {

        for (DayOfWeek dayOfWeek : weekend) {
            this.weekend.add(dayOfWeek);
        }

    }

    public void setWeekStart(DayOfWeek dayOfWeek) {
        this.weekStart = dayOfWeek;

    }

    public void setLocale(Locale locale) {

        this.locale = locale;

    }









    public LocalDate getToday() {
        return today;
    }

    public YearMonth getMonth() {
        return month;
    }

    public DayOfWeek getWeekStart() {
        return weekStart;
    }

    public Set<DayOfWeek> getWeekend() {
        return weekend;
    }

    public Locale getLocale() {
        return locale;
    }
}





           // HTMLCalendarPrinter.printCalendarInHTML(month,firstDayOfWeek,weekend);

            //List<Day> daysToPrint = Day.createDays(month)





    /*public static void printCalendarToConsole(Month month, DayOfWeek firstDayOfWeek, DayOfWeek...weekend){
        List<Day> daysToPrint = Day.createDays(month);
        HeaderDays.printHeaderDays(firstDayOfWeek, weekend);
        printDays(daysToPrint, firstDayOfWeek, weekend);
    }
*/


  /*  public static void printDays(List<Day> daysToPrint, DayOfWeek firstDayOfWeek, DayOfWeek... weekend) {

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


}*/