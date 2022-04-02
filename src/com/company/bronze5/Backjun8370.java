package com.company.bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/8370
public class Backjun8370 {
    private static final String[] array = {
            "2 5 3 20"
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
        int n1 = scanner.nextInt();
        int k1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int k2 = scanner.nextInt();

        System.out.println(n1 * k1 + n2 * k2);
    }
}
