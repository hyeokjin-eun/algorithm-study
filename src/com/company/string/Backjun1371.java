package com.company.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1371
public class Backjun1371 {
    private static final String[] array = {
            "english is a west germanic\n" +
            "language originating in england\n" +
            "and is the first language for\n" +
            "most people in the united\n" +
            "kingdom the united states\n" +
            "canada australia new zealand\n" +
            "ireland and the anglophone\n" +
            "caribbean it is used\n" +
            "extensively as a second\n" +
            "language and as an official\n" +
            "language throughout the world\n" +
            "especially in common wealth\n" +
            "countries and in many\n" +
            "international organizations",
            "baekjoon online judge",
            "abc a",
            "abc\n" +
            "ab",
            "amanda forsaken bloomer meditated gauging knolls\n" +
            "betas neurons integrative expender commonalities\n" +
            "latins antidotes crutched bandwidths begetting\n" +
            "prompting dog association athenians christian ires\n" +
            "pompousness percolating figured bagatelles bursted\n" +
            "ninth boyfriends longingly muddlers prudence puns\n" +
            "groove deliberators charter collectively yorks\n" +
            "daringly antithesis inaptness aerosol carolinas\n" +
            "payoffs chumps chirps gentler inexpressive morales"
    };

    public static void main (String[] args) throws IOException {
        for (int i = 0; i < array.length; i++) {
            System.out.println("===== Test Case " + i + " Start =====");
            long before = System.currentTimeMillis();
            solution(array[i]);
            long after = System.currentTimeMillis();
            System.out.println();
            System.out.println("===== Time : " + (after - before) + "   =====");
            System.out.println("===== Test Case " + i + " End   =====");
        }
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int[] alphabet = new int[26];
        while (true) {
            String s = br.readLine();
            if (s == null) {
                break;
            }

            char[] c = s.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] == ' ') {
                    continue;
                }

                alphabet[c[i] - 'a']++;
            }
        }

        int max = 0;
        for (int i = 0; i < alphabet.length; i++) {
            max = Math.max(alphabet[i], max);
        }

        for (int i = 0; i < alphabet.length; i++) {
            if (max == alphabet[i]) {
                bw.write(String.valueOf((char) ('a' + i)));
            }
        }

        bw.flush();
    }
}