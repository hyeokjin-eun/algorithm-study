package com.company.java.bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/8393
public class Backjun8393 {
    private static final String[] array = {
            "3", // 6
            "5" // 15
    };

    public static void main (String[] args) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("===== Test Case " + i + " Start =====");
            solution(array[i]);
            System.out.println("===== Test Case " + i + " End   =====");
        }
    }

    private static void solution(String input) {
        Scanner scanner = new Scanner(input);
        int temp = 0;
        if (scanner.hasNextInt()) {
            int max = scanner.nextInt();
            for (int i = 0; i < max; i++) {
                temp += i + 1;
            }
        }

        System.out.println(temp);
    }
}
