import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarDataSource {

    private GregorianCalendar calendar = null;

    public CalendarDataSource() {
        this.calendar = new GregorianCalendar();
    }

    public int getCurrentDay() {
        return new GregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH);
    }

    public int getCurrentMonth() {
        return new GregorianCalendar().get(GregorianCalendar.MONTH);
    }

    public int getCurrentYear() {
        return new GregorianCalendar().get(GregorianCalendar.YEAR);
    }

    public int getDay() {
        return this.calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    public int getMonth() {
        return this.calendar.get(GregorianCalendar.MONTH);
    }

    public int getNumberOfDays() {
        return this.calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
    }

    public int getWeekDay(int day) {
        GregorianCalendar cal = (GregorianCalendar) this.calendar.clone();
        cal.set(GregorianCalendar.DAY_OF_MONTH, day);
        return cal.get(GregorianCalendar.DAY_OF_WEEK);
    }

    public int getYear() {
        return this.calendar.get(GregorianCalendar.YEAR);
    }

    public void setDay(int day) {
        this.calendar.set(GregorianCalendar.DAY_OF_MONTH, day);
    }

    public void setMonth(int month) {
        this.calendar.set(GregorianCalendar.MONTH, month);
    }

    public void setYear(int year) {
        this.calendar.set(GregorianCalendar.YEAR, year);
    }
}
