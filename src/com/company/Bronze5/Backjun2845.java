package com.company.Bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/2845
public class Backjun2845 {
    private static final String[] array = {
            "1 10\n" +
            "10 10 10 10 10",
            "5 20\n" +
            "99 101 100 1000 0 97"
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

        int people = scanner.nextInt();
        int area = scanner.nextInt();
        int areaInPeople = people * area;

        while (scanner.hasNextInt()) {
            System.out.print(scanner.nextInt() - areaInPeople);
            System.out.print(" ");
        }
    }
}
