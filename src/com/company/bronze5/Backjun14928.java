package com.company.bronze5;

import java.util.Scanner;

// link
// https://www.acmicpc.net/problem/14928
public class Backjun14928 {
    private static final String[] array = {
            "20000303200003032000030320000303200003032000030320000303200003032000030320000303",
            "123456789123456789123456789123456789123456789123456789123456789123456789"
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
        // 시간 초과
        //BigInteger num = scanner.nextBigInteger();
        //scanner.close();;
        //System.out.println(num.remainder(BigInteger.valueOf(20000303)));

        String num = scanner.next();
        long temp = 0;
        for (int i = 0; i < num.length(); i++) {
            temp = (temp * 10 + (num.charAt(i) - '0')) % 20000304;
        }

        System.out.println(temp);
    }

}
