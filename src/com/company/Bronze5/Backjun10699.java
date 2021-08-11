package com.company.Bronze5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

// link
// https://www.acmicpc.net/problem/10699
public class Backjun10699 {
    public static void main (String[] args) {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(Calendar.getInstance().getTime());
        System.out.println(date);
    }
}
