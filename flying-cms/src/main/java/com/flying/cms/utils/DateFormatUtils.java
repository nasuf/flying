package com.flying.cms.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 处理日期工具类
 * 
 * @author Administrator
 *
 */
public class DateFormatUtils {

    /**
     * 将日期类型转化为毫秒值
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Long parseDateToMillis(Date date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        String time = df.format(new Date());
        long timeMillis = df.parse(time).getTime();
        System.out.println(timeMillis);
        return timeMillis;
    }

    /**
     * 将毫秒值转化为时间格式-24小时制
     * 
     * @param timeMillis
     * @return
     */
    public static String parseDateToString(Long timeMillis) {
        if (null == timeMillis || 0L == timeMillis)
            return "";
        else {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = new Date(timeMillis);
            return formatter.format(d);
        }
    }

    /**
     * 处理总在线时长
     * 
     * @param dateMillis
     * @return
     */
    public static String processTotalOnlineTime(Long dateMillis) {
        String date4Display = null;
        int days4Display = 0;
        int hours4Display = 0;
        int minutes4Display = 0;
        if (dateMillis == null || dateMillis == 0L) {
            date4Display = "0";
        } else {
            int days = (int) (dateMillis / 1000 / 60 / 60 / 24);
            days4Display = (int) Math.floor(days);
            int hours = (int) (dateMillis / 1000 / 60 / 60 - (24 * days4Display));
            hours4Display = (int) Math.floor(hours);
            int minutes = (int) (dateMillis / 1000 / 60 - (24 * 60 * days4Display) - (60 * hours4Display));
            minutes4Display = (int) Math.floor(minutes);
        }
        if (days4Display == 0) {
            if (hours4Display == 0) {
                return minutes4Display + "分钟";
            } else {
                return hours4Display + "小时" + minutes4Display + "分钟";
            }
        } else {
            return days4Display + "天" + hours4Display + "小时" + minutes4Display + "分钟";
        }
    }

    public static void main(String[] args) {
        System.out.println(processTotalOnlineTime(13505085L));
    }
}
