package com.company.java.bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/10926
public class Backjun10926 {
    private static final String[] array = {
            "joonas"
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
        String inputString = scanner.next();
        System.out.println(inputString + "??!");
    }
}
