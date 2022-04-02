package com.company.java.bronze5;

// link
// https://www.acmicpc.net/problem/9654
public class Backjun9654 {
    private static final String[] ARRAY = {
            "SHIP NAME      CLASS          DEPLOYMENT IN SERVICE",
            "N2 Bomber      Heavy Fighter  Limited    21",
            "J-Type 327     Light Combat   Unlimited  1",
            "NX Cruiser     Medium Fighter Limited    18",
            "N1 Starfighter Medium Fighter Unlimited  25",
            "Royal Cruiser  Light Combat   Limited    4",
    };

    public static void main (String[] args) {
        for (String str : ARRAY) {
            System.out.println(str);
        }
    }
}
