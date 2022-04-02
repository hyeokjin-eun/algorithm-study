package com.company.bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/11382
public class Backjun11382 {
    private static final String[] array = {
            "77 77 7777"
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
        long temp = 0;
        while (scanner.hasNextLong()) {
            temp += scanner.nextLong();
        }

        System.out.println(temp);
    }
}
