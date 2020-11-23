package com.tool.controller.date;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeController {

    public static void main(String[] args) throws Exception {
        // 获取当前时间
        LocalDateTime newTime = LocalDateTime.now();

        System.out.println(newTime);

        //指定一个字符串时间
        String wordGameStartTime = "2020-11-22 10:18:27";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(wordGameStartTime, formatter);
        System.out.println(dateTime);

        Duration duration = Duration.between(newTime, dateTime);
        System.out.println(duration.toDays());

    }

}
