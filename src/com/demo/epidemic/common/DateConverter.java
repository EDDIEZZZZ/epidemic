package com.demo.epidemic.common;

import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author eddie
 * @Date 2020-02-25 16:59
 * @Version 1.0
 */
@Component
public class DateConverter implements Converter<String, Date> {

    public static Logger logger = Logger.getLogger(DateConverter.class);

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            logger.error("装换提交的参数" + s + "为日期值时出错：" + e.getMessage());
        }
        return date;
    }
}
