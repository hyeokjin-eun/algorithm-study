package com.company.java.bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/15439
public class Backjun15439 {
    private static final String[] array = {
            "1",
            "2",
            "5",
            "2017"
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
        int num = scanner.nextInt();
        System.out.println(num * num - num);
    }
}
