package com.company.java.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/16968
// TODO: 2021-10-29 조합을 이용한 식으로 푸는것 가능
public class Backjun16968_1 {
    private static final String[] array = {
            "dd",
            "cc",
            "dcdd"
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
        char[] s = br.readLine().toCharArray();
        int answer = recursion(s, 0, ' ');
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int recursion(char[] s, int index, int before) {
        if (s.length == index) {
            return 1;
        }

        int answer = 0;
        int start = s[index] == 'c' ? 'a' : '0';
        int end = s[index] == 'c' ? 'z' : '9';
        for (int i = start; i <= end; i++) {
            if (before == i) {
                continue;
            }

            answer += recursion(s, index + 1, i);
        }

        return answer;
    }
}
