package com.wu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换类
 * 时间类型与字符串类型的相互转换
 */
public class DateUtils {

    /**
     * 时间转换成字符串
     */
    public static String dateToString(Date date, String patt){
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        //将时间转换成字符串
        String format=sdf.format(date);
        return  format;
    }

    /**
     * 字符串转换成时间类型
     */
    public static Date stringToDate(String str,String patt) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        //将字符串转换成时间
        Date parse=sdf.parse(str);
        return parse;
    }
}
