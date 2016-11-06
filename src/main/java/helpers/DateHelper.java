package helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;


public class DateHelper {

    /**
     * Get current month as a String
     *
     * @return current month
     */
    public static String getCurrentMonth() {
        LocalDateTime currentTime = LocalDateTime.now();
        Month month = currentTime.getMonth();
        return month.toString();
    }

    /**
     * Get date few days ago
     *
     * @param days   how many days ago we need date
     * @param format format of the return
     * @return String in specified format
     */
    public static String getDateFewDaysAgo(int days, String format) {
        LocalDate date = LocalDate.now().minusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String formattedString = date.format(formatter);
        return formattedString;

    }
}