package com.tool.controller.date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/date")
public class DateController {

    public static void main(String[] args) throws Exception {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String date = sdf.format(new Date());
        System.out.println(date.substring(0,4));
        System.out.println(date.substring(5));
    }

}
