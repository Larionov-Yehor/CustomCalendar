import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * Created by employee on 11/3/16.
 */
public class Day {


    static int currentYear = LocalDate.now().getYear();
    static int currentDay = LocalDate.now().getDayOfYear();

    private int dayOfMonth;
    private int dayOfYear;
    private DayOfWeek dayOfWeek;

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getDayOfYearValue() {
        return dayOfYear;
    }

    public void setDayOfYearValue(int dayOfYearValue) {
        this.dayOfYear = dayOfYearValue;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Day(){

    }
    public Day(int dayOfMonth, int dayOfYear, DayOfWeek dayOfWeek){
        this.dayOfMonth = dayOfMonth;
        this.dayOfYear = dayOfYear;
        this.dayOfWeek =dayOfWeek;
    }

    public static List<Day> createDays(Month month) {

        List<Day> result = new ArrayList<>();

        LocalDate localDateToPrint = LocalDate.of(currentYear, month, 1);
        LocalDate lastDayOfMonth = localDateToPrint.with(lastDayOfMonth());
        int monthToPrintLength = lastDayOfMonth.getDayOfMonth();

        for (int i = 1; i <= monthToPrintLength; i++) {

            Day day = new Day();

            day.setDayOfYearValue(localDateToPrint.getDayOfYear());
            day.setDayOfWeek(localDateToPrint.getDayOfWeek());
            day.setDayOfMonth(localDateToPrint.getDayOfMonth());

            result.add(day);

            localDateToPrint = localDateToPrint.plusDays(1);
        }
        return result;
    }

    public boolean isWeekend(Day day, DayOfWeek... weekendsArg) {
        boolean result = false;
        List<DayOfWeek> weekends = new ArrayList<>();

        for (DayOfWeek dayOfWeek:weekendsArg){
            weekends.add(dayOfWeek);
        }

        if (weekends.contains(day.getDayOfWeek())) {
            result = true;
        }

        return result;
    }

    public boolean isToday() {
        boolean result = false;
        if (dayOfYear== currentDay) {
            result = true;
        }
        return result;
    }

    public boolean isFirstDayOfMonth() {
        boolean result = false;
        if (dayOfMonth == 1) {
            result = true;
        }
        return result;
    }

    public static void isLastDayOfWeek(Day day, DayOfWeek firstDayOfWeek){

        DayOfWeek lastDayOfWeek = firstDayOfWeek.plus(6);

        if(day.getDayOfWeek().equals(lastDayOfWeek)){
            System.out.println();

        }
    }

    public static void printWeekend(Day day, String parameter,DayOfWeek firstDayOfWeek) {

        System.out.print("\u001B[31m");
        System.out.printf(parameter, day.getDayOfMonth());
        System.out.print("\u001B[0m");
        isLastDayOfWeek(day,firstDayOfWeek);
    }

    public static void printToday(Day day, String parameter, DayOfWeek firstDayOfWeek) {

        System.out.print("\u001B[32m");
        System.out.printf(parameter, day.getDayOfMonth());
        System.out.print("\u001B[0m");
        isLastDayOfWeek(day,firstDayOfWeek);
    }

    public static void printCommonDay(Day day, String parameter, DayOfWeek firstDayOfWeek){
        System.out.printf(parameter, day.getDayOfMonth());
        isLastDayOfWeek(day,firstDayOfWeek);
    }

    public static String getPrintGapParameter(Day day, DayOfWeek firstDayOfWeekArg){

        int parameterForSpaces=4;
        DayOfWeek dayOfWeek = day.getDayOfWeek();
        DayOfWeek firstDayOfWeek = firstDayOfWeekArg;

        do{
            if(dayOfWeek.equals(firstDayOfWeek)){
                break;
            }
            parameterForSpaces+=4;

            firstDayOfWeek=firstDayOfWeek.plus(1);

        }while (!dayOfWeek.equals(firstDayOfWeek));

        return "%"+parameterForSpaces+"d";
    }

   /* public static void printFirstDayOfMonth(Day day, DayOfWeek firstDayOfWeek,DayOfWeek...weekends) {

        String parameter = getPrintGapParameter(day,firstDayOfWeek);

        int switchValue = 0;

        if (day.isToday()) {
            switchValue = 2;
        }
        if (isWeekend(day, weekends)) {
            switchValue = 3;
        }
        if (isToday(day) & isWeekend(day)) {
            switchValue = 1;
        }

        switch (switchValue) {
            case 0:
                printCommonDay(day,parameter,firstDayOfWeek);
                break;
            case 1:
                printToday(day,parameter,firstDayOfWeek);
                break;
            case 2:
                printToday(day,parameter,firstDayOfWeek);
                break;
            case 3:
                printWeekend(day,parameter,firstDayOfWeek);
                break;
        }
    }*/
}