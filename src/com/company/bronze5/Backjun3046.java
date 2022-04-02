package com.company.bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/3046
public class Backjun3046 {
    private static final String[] array = {
            "11 15",
            "4 3"
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
        int r1 = scanner.nextInt();
        int s = scanner.nextInt();
        int r2 = s * 2 - r1;
        System.out.println(r2);
    }
}
