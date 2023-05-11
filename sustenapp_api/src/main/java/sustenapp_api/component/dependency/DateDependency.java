package sustenapp_api.component.dependency;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDependency {
    public static Calendar getDate() {
        return Calendar.getInstance();
    }

    public static String getDateFormated(Date data) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(data);
    }

    public static Calendar parseDate(Date data) {
        Calendar calendar = getDate();
        calendar.setTime(data);
        return calendar;
    }

    public static Date getTime() {
        return getDate().getTime();
    }

    public static int getDay() {
        return getDate().get(Calendar.YEAR);
    }

    public static int getMonth() {
        return getDate().get(Calendar.MONTH);
    }

    public static int getYear() {
        return getDate().get(Calendar.YEAR);
    }
}
