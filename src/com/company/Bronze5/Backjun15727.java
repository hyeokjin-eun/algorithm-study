package com.company.Bronze5;

import java.util.Scanner;
import java.lang.Math;

// link
// https://www.acmicpc.net/problem/15727
public class Backjun15727 {
    private static final String[] array = {
            "12"
    };

    public static void main (String[] args) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("===== Test Case " + i + " Start =====");
            solution(array[i]);
            System.out.println("===== Test Case " + i + " End   =====");
        }
    }

    private static void solution (String input) {
        Scanner scanner = new Scanner(input);
        int num = scanner.nextInt();
        System.out.println((int) Math.ceil(num / 5.0));
    }
}
