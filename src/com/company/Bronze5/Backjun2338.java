package com.company.Bronze5;

import java.math.BigInteger;
import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/2338
public class Backjun2338 {

    private static final String[] array = {
            "1 -1",
            "12312318237198237189273981 -123123123123123123"
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

        System.out.println(numA.add(numB));
        System.out.println(numA.subtract(numB));
        System.out.println(numA.multiply(numB));
    }
}
