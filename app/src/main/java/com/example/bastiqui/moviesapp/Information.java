package com.example.bastiqui.moviesapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Information {
    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        return dateFormat.format(date);
    }
}