package com.company.Bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/15964
public class Backjun15964 {
    private static final String[] array = {
            "4 3",
            "3 4"
    };

    public static void main (String[] agrs) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("===== Test Case " + i + " Start =====");
            solution(array[i]);
            System.out.println("===== Test Case " + i + " End   =====");
        }
    }

    private static void solution (String input) {
        Scanner scanner = new Scanner(input);
        long numA = scanner.nextLong();
        long numB = scanner.nextLong();
        System.out.println((numA + numB) * (numA - numB));
    }
}
