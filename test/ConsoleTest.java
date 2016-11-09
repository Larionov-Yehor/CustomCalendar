import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by employee on 11/9/16.
 */
public class ConsoleTest {

    public ByteArrayOutputStream outCont = new ByteArrayOutputStream();
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
    public void assertPrintWeekendInRed(){
        ConsoleCalendar consoleCalendar = new ConsoleCalendar();
        assertThat(consoleCalendar.printWeekend(LocalDate.now()), startsWith(red));

    }

    @Test
    public void assertPrintTodayInGreen(){
        ConsoleCalendar consoleCalendar = new ConsoleCalendar();
        assertThat(consoleCalendar.printCurrentDay(LocalDate.now()), startsWith(green));
    }

    @Test
    public void assertStartPrintDaysNamesFromCorrectDay(){
        ConsoleCalendar consoleCalendar = new ConsoleCalendar();
        assertThat(consoleCalendar.printWeekendHeader(DayOfWeek.MONDAY), startsWith(red));
    }


    @Test
    public void assertCorrectGapPrint(){
        ConsoleCalendar consoleCalendar = new ConsoleCalendar();
        assertThat(consoleCalendar.printGaps(), equalTo("\t"));
    }
}
