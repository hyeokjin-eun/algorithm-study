package com.company.Bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/10430
public class Backjun10430 {
    private static final String[] array = {
            "5 8 4"
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
        int numA = scanner.nextInt();
        int numB = scanner.nextInt();
        int numC = scanner.nextInt();
        System.out.println((numA + numB) % numC);
        System.out.println(((numA % numC) + (numB % numC)) % numC);
        System.out.println((numA * numB) % numC);
        System.out.println(((numA % numC) * (numB % numC)) % numC);
    }
}
