package cn.jxust.leave.utils;

import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author JJ
 * @date 2019/11/10 - 16:30
 */
public class TimeUtils {

    public static void main(String[] args) {

        int leaveDays = getDuringTime(Timestamp.valueOf("2019-11-10 17:00:00"), Timestamp.valueOf("2019-11-11 17:00:00"));

        System.out.println(leaveDays);

        System.out.println(getWeekOfDate(Timestamp.valueOf("2019-10-16 17:00:00")));
    }

    /**
     * 传入起始时间,结束时间,得到经过的天数
     * 单纯的只要是请假时间在24小时内包括24小时就算一天
     *
     * @param start_time
     * @param end_time
     * @return
     */
    public static int getDuringTime(Timestamp start_time, Timestamp end_time) {

        long l = end_time.getTime() - start_time.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM dd HH");
        String str = sdf.format(l).toString();
        String[] s = str.split("\\s+");
        int[] t = new int[3];
        for (int i = 0; i < s.length; i++) {
            t[i] = Integer.valueOf(s[i]);
        }
        t[0] -= 1;
        t[1] -= 1;
        t[2] -= 8;

        return t[0] * 30 + t[1] + (t[2] == 0 ? 0 : 1);
    }


    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        System.out.println(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static Timestamp getStringToTimestamp(String time) { //String time = "2012-02-21T13:21";

        try {
            Format f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date d = (Date) f.parseObject(time);
            Timestamp ts = new Timestamp(d.getTime());
            return ts;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
