package com.company.Bronze5;

import java.util.Scanner;
import java.math.BigInteger;

// link
// https://www.acmicpc.net/problem/15894
public class Backjun15894 {
    private static final String[] array = {
            "1",
            "3"
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
        BigInteger num = scanner.nextBigInteger();
        scanner.close();
        System.out.println(num.multiply(BigInteger.valueOf(4)));
    }
}
