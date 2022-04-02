package com.company.java.bronze5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

// link
// https://www.acmicpc.net/problem/16170
public class Backjun16170 {
    public static void main (String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String[] date = simpleDateFormat.format(Calendar.getInstance().getTime()).split("-");
        System.out.println(date[0]);
        System.out.println(date[1]);
        System.out.println(date[2]);
    }
}
