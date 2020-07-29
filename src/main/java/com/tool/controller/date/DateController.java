package com.tool.controller.date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/date")
public class DateController {

    public static void main(String[] args) throws Exception {
        dataSubstring();
    }

    /**
     * 字符串转日期
     */
    private static void string2Date() {
        System.out.println("字符串转日期============");
        // 日期字符串
        String dateStr = "1999-1-1 12:12:12";
        // 日期格式
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            // 转换
            Date date = sdf.parse(dateStr);
            System.out.println(date);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * 日期转字符串
     */
    private static void date2String() {
        System.out.println("日期转字符串============");
        // 获取当前日期
        Date date = new Date();
        // 日期格式
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            // 转换
            String dateStr = sdf.format(date);
            System.out.println(dateStr);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * 日期截取
     */
    private static void dataSubstring() {
        System.out.println("日期截取============");
        // 获取当前日期
        Date date = new Date();
        // 日期格式
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            // 转换
            String dateStr = sdf.format(date);

            System.out.println("年===" + dateStr.substring(0,4));
            System.out.println("月===" + dateStr.substring(5,7));
            System.out.println("日===" + dateStr.substring(8,10));
            System.out.println("时===" + dateStr.substring(12,14));
            System.out.println("分===" + dateStr.substring(15,17));
            System.out.println("秒===" + dateStr.substring(18,20));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
