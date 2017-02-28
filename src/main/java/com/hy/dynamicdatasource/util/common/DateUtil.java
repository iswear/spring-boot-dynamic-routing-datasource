package com.hy.dynamicdatasource.util.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/20.
 */
public class DateUtil
{
    public enum DateFormatters
    {
        Y_M_D_SPACE_H_M_S("yyyy-MM-dd HH:mm:ss"),
        YMDHMS("yyyyMMddHHmmss");

        private String formatStr;

        private DateFormatters(String formatStr)
        {
            this.formatStr = formatStr;
        }

        public String getFormatStr()
        {
            return formatStr;
        }

        public void setFormatStr(String formatStr)
        {
            this.formatStr = formatStr;
        }

    }

    public static String now(DateFormatters format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format.getFormatStr());
        return sdf.format(new Date());
    }

    public static String format(Date date, DateFormatters format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format.getFormatStr());
        return sdf.format(date);
    }

    public static String format(Timestamp time, DateFormatters format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format.getFormatStr());
        return sdf.format(time);
    }

    public static Date date(String date, DateFormatters format) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format.getFormatStr());
        return sdf.parse(date);
    }

}
