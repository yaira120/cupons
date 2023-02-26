package cupon_project.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtils {
    public static Date javaDateToSqlDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    public static Date getStartDate() {
        int year=2022;
        int month=02;
        int day=(int)(Math.random()* 14+1);
        return Date.valueOf(year+"-"+month+"-"+day);
       // return LocalDate.now().minusDays((int) (Math.random() * 14 + 1));
    }

    public static Date getEndDate(){
        int year=2022;
        int month=02;
        int day=(int)(Math.random()*14 +14);
        return Date.valueOf(year+"-"+month+"-"+day);

    }


/*
    public static LocalDate getEndDate() {
        return LocalDate.now().plusDays((int) (Math.random() * 14 + 1));
    }

 */

    public static String beutufulyDate(LocalDate localDate) {
        return String.format("%02d/%02d/%04d", localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());

    }

    public static String beutufulyDateAndTime(LocalDateTime localDate) {
        return String.format("%02d/%02d/%04d %02d:%02d:%02d", localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear(),
                localDate.getHour(), localDate.getMinute(), localDate.getSecond());

    }

  public static String beutufulyDateAndTimeNow(){

        return beutufulyDateAndTime(LocalDateTime.now());
    }



}
