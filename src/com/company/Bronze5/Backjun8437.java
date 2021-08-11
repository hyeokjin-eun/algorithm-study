package com.company.Bronze5;

import java.util.Scanner;
import java.math.BigInteger;

// link
// // https://www.acmicpc.net/problem/8437
public class Backjun8437 {
    private static final String[] array = {
            "10 2"
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
        BigInteger numA = scanner.nextBigInteger();
        BigInteger numB = scanner.nextBigInteger();
        System.out.println(numA.add(numB).divide(BigInteger.valueOf(2L)));
        System.out.println(numA.subtract(numB).divide(BigInteger.valueOf(2L)));
    }
}
