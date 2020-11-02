package zzuli.zw.weather.utils;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static Logger logger = Logger.getLogger(DateUtils.class);
    public static boolean isNight(){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("HH");
        String str = df.format(date);
        int a = Integer.parseInt(str);
        return a > 18 && a <= 24;
    }

    public static boolean compare(String dateStr){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        try {
            Date parse = df.parse(dateStr);
            return parse.compareTo(format(date)) >= 0;
        } catch (ParseException e) {
            logger.error(e);
            e.printStackTrace();
            return false;
        }
    }

    private static Date format(Date date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String format = df.format(date);
        return df.parse(format);
    }
}
