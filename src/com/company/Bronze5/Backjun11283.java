package com.company.Bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/11283
public class Backjun11283 {
    private static final String[] array = {
            "가",
            "힣",
            "백",
            "준"
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
        char inputChar = scanner.next().charAt(0);
        System.out.println(inputChar - 44031);
    }
}
