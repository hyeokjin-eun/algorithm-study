package com.company.Bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/3003
public class Backjun3003 {
    private static final String[] array = {
            "0 1 2 2 2 7",
            "2 1 2 1 2 1",
            "10 0 10 0 10 0"
    };

    public static void main(String[] args) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("===== Test Case " + i + " Start =====");
            solution(array[i]);
            System.out.println("===== Test Case " + i + " End   =====");
        }
    }

    private static void solution(String input) {
        int[] pieces = {1, 1, 2, 2, 2, 8};
        Scanner scanner = new Scanner(input);
        for (int piece : pieces) {
            if (scanner.hasNextInt()) {
                System.out.print(piece - scanner.nextInt());
                System.out.print(" ");
            }
        }
    }
}
