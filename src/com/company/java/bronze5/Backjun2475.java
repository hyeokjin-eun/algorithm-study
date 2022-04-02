package com.company.java.bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/2475
public class Backjun2475 {
    private static final String[] array = {
            "0 4 2 5 6", // 1
            "0 0 0 0 2" // 4
    };

    public static void main(String[] args) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("===== Test Case " + i + " Start =====");
            solution(array[i]);
            System.out.println("===== Test Case " + i + " End   =====");
        }
    }

    private static void solution(String input) {
        int max = 5;
        Scanner scanner = new Scanner(input);
        int temp = 0;
        for (int i = 0; i < max; i++) {
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                temp += num * num;
            }
        }

        scanner.close();
        System.out.println(temp % 10);
    }
}
