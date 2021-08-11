package com.company.Bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/10998
public class Backjun10998 {
    private static final String[] array = {
            "1 2",
            "3 4"
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
        int numA = scanner.nextInt();
        int numB = scanner.nextInt();
        System.out.println(numA * numB);
    }
}
