import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by employee on 11/8/16.
 */
public class HtmlCalendar extends Calendar {

    private static final String startOfFile = "<html> <head> <title>Calendar</title><link href=\"style.css\" rel=\"stylesheet\"></head> <body> <table>";
    private static final String endOfFile = "</tr></table></body></html>";
    private static final String style = " .weekend{color: #FE0000;} .currentDay {color: #00FE00;} td{ width: 30px;}";

    private static String htmlFileContent = " ";

    public void printHtml(){

        htmlFileContent = startOfFile+ "<tr>" + printDaysNames() + "</tr>"+ printFirstDay()+ endOfFile;
        writeCalendarToHTMLFileAndStyleToCSS(htmlFileContent, style);

    }

    @Override
    public String printWeekendHeader(DayOfWeek dayOfWeek) {

        return "<td class=\"weekend\">" + dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "</td>\n";
    }

    @Override
    public String printCommonHeader(DayOfWeek dayOfWeek) {

        return "<td>" + dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "</td>\n";
    }

    @Override
    public String printGaps() {
        String result = "";

        result+= "<td></td>";

        return result;
    }

    @Override
    public String printCommonDay(String day) {
        return "<td>" + day + "</td>";
    }


    public static void writeCalendarToHTMLFileAndStyleToCSS(String htmlFileContent, String cssFileContent) {

        Path pathHTML = Paths.get("/home/employee/Desktop/HTML/Calendar.html");
        Path pathCSS = Paths.get("/home/employee/Desktop/HTML/style.css");

        File fileHTML = pathHTML.toFile();
        File fileCSS = pathCSS.toFile();

        try {
            fileHTML.delete();
            fileCSS.delete();

            if (fileHTML.createNewFile() & fileCSS.createNewFile()) {
                FileWriter fwHTML = new FileWriter(fileHTML);
                FileWriter fwCSS = new FileWriter(fileCSS);

                fwHTML.write(htmlFileContent);
                fwHTML.close();

                fwCSS.write(cssFileContent);
                fwCSS.close();

            }
        } catch (IOException e) {
            e.getMessage();
        }

    }
}
