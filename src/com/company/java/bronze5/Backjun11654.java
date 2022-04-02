package com.company.java.bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/11654
public class Backjun11654 {
    private static final String[] array = {
            "A",
            "C",
            "0",
            "9",
            "a",
            "z"
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
        int code = scanner.next().charAt(0);
        System.out.println(code);
    }
}
