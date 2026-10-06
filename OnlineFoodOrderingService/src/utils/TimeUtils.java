package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    public static String getCurrentTime(){
        LocalDateTime now = LocalDate.now();
        return now.format(DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy"));
    }
}
