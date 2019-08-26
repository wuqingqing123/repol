package com.wu.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将字符串转换为日期格式
 */
public class StringToDate implements Converter<String,Date> {
    /**
     *
     * @param s 传进来字符串
     * @return
     */
    @Override
    public Date convert(String s) {
        //判断字符串是否为空
        if (s == null) {
            throw new RuntimeException("传入数据不能为空");
        }
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try{
            //把字符串转换成日期
            return df.parse(s);
        }catch(Exception e){
            throw new RuntimeException("数据类型转换出错");
        }

    }
}
