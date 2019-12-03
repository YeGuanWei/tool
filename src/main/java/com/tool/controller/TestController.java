package com.tool.controller;

import java.util.Calendar;

/**
 * 发送Post请求
 */
public class TestController {

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(5, 1);
        cal.set(10, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        int month = cal.get(2) + 1;
        System.out.println(month);
        long newEffMonth = cal.getTimeInMillis();
        cal.add(2, -1);
        long effMonth = cal.getTimeInMillis();
        System.out.println(effMonth + "---" + newEffMonth);
    }

}
