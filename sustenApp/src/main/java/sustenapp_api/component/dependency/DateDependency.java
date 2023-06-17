package sustenapp_api.component.dependency;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDependency {
    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    public static Date getDate() {
        return getCalendar().getTime();
    }

    public static String getDateFormated(Date data) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(data);
    }

    public static String getDateFormatedMonthAndYear(Date data) {
        DateFormat formatter = new SimpleDateFormat("MM/yyyy");
        return formatter.format(data);
    }

    public static Calendar parseDateToCalendar(Date data) {
        Calendar calendar = getCalendar();
        calendar.setTime(data);
        return calendar;
    }

    public static boolean compareDate(Date dateComparadora, Date dateComparada) {
        return getDateFormatedMonthAndYear(dateComparadora).equals(getDateFormatedMonthAndYear(dateComparada));
    }
}
