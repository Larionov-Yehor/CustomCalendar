import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

/**
 * Created by employee on 11/9/16.
 */
public class HtmlTests {

    public ByteArrayOutputStream outCont = new ByteArrayOutputStream();
    public static String red = "<td class=\"weekend\">";
    public static String green = "<td class=\"currentDay\">";

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
        HtmlCalendar htmlCalendar = new HtmlCalendar();
        assertThat(htmlCalendar.printWeekend(LocalDate.now()), startsWith(red));

    }

    @Test
    public void assertPrintTodayInGreen(){
        HtmlCalendar htmlCalendar = new HtmlCalendar();
        assertThat(htmlCalendar.printCurrentDay(LocalDate.now()), startsWith(green));
    }

    @Test
    public void assertStartPrintDaysNamesFromCorrectDay(){
        HtmlCalendar htmlCalendar = new HtmlCalendar();
        assertThat(htmlCalendar.printWeekendHeader(DayOfWeek.MONDAY), startsWith(red));
    }

    @Test
    public void assertCorrectGapPrint() {
        HtmlCalendar htmlCalendar = new HtmlCalendar();
        assertThat(htmlCalendar.printGaps(), equalTo("<td></td>"));
    }
}
