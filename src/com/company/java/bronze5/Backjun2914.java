package com.company.java.bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/2914
public class Backjun2914 {
    private static final String[] array = {
            "38 24",
            "1 100",
            "10 10"
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
        int cnt = scanner.nextInt();
        int avg = scanner.nextInt();

        System.out.println((cnt * (avg - 1)) + 1);
    }
}
