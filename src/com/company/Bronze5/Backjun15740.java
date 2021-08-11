package com.company.Bronze5;

import java.util.Scanner;
import java.math.BigInteger;

// link
// https://www.acmicpc.net/problem/15740
public class Backjun15740 {
    private static final String[] array = {
            "1 2",
            "-60 40",
            "-999999999 1000000000",
            "1099511627776 1073741824",
            "123456789123456789123456789 987654321987654321987654321"
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
        BigInteger temp = BigInteger.valueOf(0);
        while (scanner.hasNextBigInteger()) {
            temp = temp.add(scanner.nextBigInteger());
        }

        System.out.println(temp);
    }
}
