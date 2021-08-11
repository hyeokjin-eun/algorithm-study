package com.company.Bronze5;

import java.util.Scanner;
import java.math.BigInteger;

// link
// https://www.acmicpc.net/problem/13277
public class Backjun13277 {
    private static final String[] array = {
            "1 2",
            "3 4",
            "893724358493284 238947328947329"
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
        BigInteger temp = BigInteger.valueOf(1);
        while (scanner.hasNextBigInteger()) {
            temp = temp.multiply(scanner.nextBigInteger());
        }

        System.out.println(temp);
    }
}
