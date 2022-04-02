package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1062
// TODO: 2021-10-12 비트 마스트로 재 해결 필요
public class Backjun1062 {
    private static final String[] array = {
            "3 6\n" +
            "antarctica\n" +
            "antahellotica\n" +
            "antacartica",
            "2 3\n" +
            "antaxxxxxxxtica\n" +
            "antarctica",
            "9 8\n" +
            "antabtica\n" +
            "antaxtica\n" +
            "antadtica\n" +
            "antaetica\n" +
            "antaftica\n" +
            "antagtica\n" +
            "antahtica\n" +
            "antajtica\n" +
            "antaktica",
            "2 5\n" +
            "antatica\n" +
            "antaatica",
            "7 9\n" +
            "antarotica\n" +
            "antajapwtica\n" +
            "antarnlhtica\n" +
            "antavfytica\n" +
            "antarwtica\n" +
            "antaltica\n" +
            "antantica",
            "2 6\n" +
            "antautica\n" +
            "antabtica",
            "2 25\n" +
            "antatica\n" +
            "antaztica"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String[] s = new String[N];
        for (int i = 0; i < N; i++) {
            s[i] = br.readLine();
        }

        boolean[] alpha = new boolean[26];
        alpha[0] = true;
        alpha['n' - 'a'] = true;
        alpha['t' - 'a'] = true;
        alpha['i' - 'a'] = true;
        alpha['c' - 'a'] = true;
        int answer = recursion(1, 5, K, alpha, s);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int recursion(int i, int select, int K, boolean[] alpha, String[] s) {
        if (select == K) {
            int answer = 0;
            for (String t : s) {
                boolean check = true;
                for (int j = 4; j < t.length() - 3; j++) {
                    String temp = Character.toString((char)(t.charAt(j)));
                    if (!alpha[t.charAt(j) - 'a']) {
                        check = false;
                        break;
                    }
                }

                if (check) {
                    answer++;
                }
            }

            return answer;
        }

        if (26 <= i) {
            return 0;
        }

        if (alpha[i]) {
            return recursion(i + 1, select, K, alpha, s);
        }

        alpha[i] = true;
        int selected = recursion(i + 1, select + 1, K, alpha, s);
        alpha[i] = false;
        int unselected = recursion(i + 1, select, K, alpha, s);
        return Math.max(selected, unselected);
    }
}