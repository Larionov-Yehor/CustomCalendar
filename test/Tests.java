import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.xml.transform.OutputKeys.METHOD;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by employee on 11/2/16.
 */

public class Tests {

    public ByteArrayOutputStream outCont = new ByteArrayOutputStream();
    public static String defaultPrintParameter = "%4d";
    public static String red = "\u001B[31m";
    public static String green = "\u001B[32m";

    @Before
    public void setDefStream() {
        System.setOut(new PrintStream(outCont));
    }

    @After
    public void cleanUpStream() {
        System.out.flush();
        System.setOut(null);
    }

    @Test
    public void assertWeekendPrintInRed() {

        Day weekend = new Day();
        weekend.setDayOfWeek(DayOfWeek.SATURDAY);
        Day.printWeekend(weekend,defaultPrintParameter, DayOfWeek.SATURDAY);

        assertThat(outCont.toString(),containsString(red));
    }
    @Test
    public void assertTodayPrintInGreen() {

        LocalDate ld = LocalDate.now();
        Day today = new Day();
        today.setDayOfWeek(ld.getDayOfWeek());
        today.setPrintValue(ld.getDayOfMonth());
        today.setDayOfYearValue(ld.getDayOfYear());

        Day.printToday(today,defaultPrintParameter, DayOfWeek.MONDAY);

        assertThat(outCont.toString(),containsString(green));


    }
    @Test
    public void assertHeaderDaysOfWeekCorrectPrint(){

        HeaderDays.printHeaderDays(DayOfWeek.TUESDAY, DayOfWeek.SUNDAY);

        assertThat(outCont.toString(), startsWith(" Tue"));

    }


    @Test
    public void checkIfNumberOfDaysInMonthNotBiggerThan31AndNotLessThan28(){

        List<Day> daysInMonth = Day.createDays(Month.DECEMBER);

        assertTrue(daysInMonth.size()>=28 && daysInMonth.size()<=31);
    }





}