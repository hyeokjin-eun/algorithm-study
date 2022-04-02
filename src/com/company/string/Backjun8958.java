package com.company.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/8958
public class Backjun8958 {
    private static final String[] array = {
            "5\n" +
            "OOXXOXXOOO\n" +
            "OOXXOOXXOO\n" +
            "OXOXOXOXOXOXOX\n" +
            "OOOOOOOOOO\n" +
            "OOOOXOOOOXOOOOX"
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
        int T = stoi(br.readLine());
        while (T-- != 0) {
            int sum = 0;
            int temp = 0;
            char[] s = br.readLine().toCharArray();
            for (int i = 0; i < s.length; i++) {
                if (s[i] == 'O') {
                    temp++;
                    sum += temp;
                } else {
                    temp = 0;
                }
            }

            bw.write(String.valueOf(sum));
            if (T != 0) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}