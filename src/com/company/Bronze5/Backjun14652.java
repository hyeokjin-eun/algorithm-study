package com.company.Bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/14652
public class Backjun14652 {
    private static final String[] array = {
            "3 4 6",
            "6 4 14"
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
        scanner.nextInt();
        int width = scanner.nextInt();
        int seat = scanner.nextInt();
        int seatLength = seat / width;
        int seatWidth = seat % width;
        System.out.println(seatLength + " " + seatWidth);
    }
}
