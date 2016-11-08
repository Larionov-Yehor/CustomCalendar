import java.time.DayOfWeek;
import java.time.format.TextStyle;

/**
 * Created by employee on 11/8/16.
 */
public class ConsoleCalendar extends Calendar {

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
    public String printCommonDay(String day) {
        return day;
    }


}
