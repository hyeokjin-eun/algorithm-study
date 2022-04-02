package com.company.java.bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/6749
public class Backjun6749 {
    private static final String[] array = {
            "12 15",
            "5 10"
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
        int youngest = scanner.nextInt();
        int middle = scanner.nextInt();
        int oldest = middle + (middle - youngest);
        System.out.println(oldest);
    }
}
