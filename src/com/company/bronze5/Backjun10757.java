package com.company.bronze5;

import java.util.Scanner;
import java.math.BigInteger;

// link
// https://www.acmicpc.net/problem/10757
public class Backjun10757 {
    private static final String[] array = {
            "9223372036854775807 9223372036854775808"
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
        BigInteger numA = scanner.nextBigInteger();
        BigInteger numB = scanner.nextBigInteger();
        System.out.println(numA.add(numB));
    }
}
