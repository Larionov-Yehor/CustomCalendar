/*
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;

*/
/**
 * Created by LaroSelf on 07.11.2016.
 *//*

public class HTMLTests {
//html output tests 

    @Test
    public void assertCurrentDayPrintInSpecialColor() {

        Day currentDay = new Day();
        currentDay.setDayOfYearValue(LocalDate.now().getDayOfYear());
        currentDay.setDayOfWeek(DayOfWeek.SUNDAY);

        String result = "";
        if (Day.isToday(currentDay)) {
            result += HTMLCalendarPrinter.printToday(currentDay, DayOfWeek.MONDAY);
        }

        assertThat(result, startsWith("<td class=\"currentDayColor\">"));
    }

    @Test
    public void assertWeekendPrintInSpecialColor(){

        Day weekend = new Day();
        weekend.setDayOfMonth(3);
        weekend.setDayOfWeek(DayOfWeek.SUNDAY);

        String result = "";
        if (Day.isWeekend(weekend, DayOfWeek.MONDAY,DayOfWeek.SUNDAY)){
            result += HTMLCalendarPrinter.printWeekend(weekend, DayOfWeek.TUESDAY);
        }

        assertThat(result, startsWith("<td class=\"weekendColor\">"));
    }


    @Test
    public void assertStartOfNewRowAfterLastDayOfWeek(){

        Day day = new Day();
        day.setDayOfWeek(DayOfWeek.SUNDAY);
        DayOfWeek firstDayOfWeek = DayOfWeek.MONDAY;
        DayOfWeek lastDayOfWeek = firstDayOfWeek.plus(6);

        String result = HTMLCalendarPrinter.ifLastDayOfWeekAddNewRowToTable(day,firstDayOfWeek);

        assertThat(result, startsWith("</tr><tr>"));
    }

    @Test
    public void assertAppropriateNumberOfEmptyCellsInTableBeforeFirstDayOfMonth(){

        Day day = new Day();
        day.setDayOfWeek(DayOfWeek.WEDNESDAY);
        DayOfWeek firstDayOfWeek = DayOfWeek.MONDAY;

        //if first day of week is Monday and first day of month is Wednesday there must be 2 empty cells in table

        String result = "<tr>" + "<td></td>\t" + "<td></td>\t";

        assertThat(result, startsWith(HTMLCalendarPrinter.printEmptyTabsBeforeFirstDayOfMonth(day,firstDayOfWeek)));


    }



}*/
