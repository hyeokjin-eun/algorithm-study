package com.company.Bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/5554
public class Backjun5554 {
    private static final String[] array = {
            "31 34 7 151",
            "316 430 643 1253"
    };

    public static void main(String[] args) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("===== Test Case " + i + " Start =====");
            solution(array[i]);
            System.out.println("===== Test Case " + i + " End   =====");
        }
    }

    private static void solution(String input) {
        Scanner scanner = new Scanner(input);
        int temp = 0;
        while (scanner.hasNextInt()) {
            temp += scanner.nextInt();
        }

        int minute = (int) temp / 60;
        int second = temp - minute * 60;
        System.out.println(minute);
        System.out.println(second);
    }
}
