package com.company.java.string;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/14426
public class Backjun14426_arr {
    private static final String[] array = {
            "5 10\n" +
            "baekjoononlinejudge\n" +
            "startlink\n" +
            "codeplus\n" +
            "sundaycoding\n" +
            "codingsh\n" +
            "baekjoon\n" +
            "star\n" +
            "start\n" +
            "code\n" +
            "sunday\n" +
            "coding\n" +
            "cod\n" +
            "online\n" +
            "judge\n" +
            "plus"
    };

    public static void main(String[] args) throws IOException {
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
        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        int answer = 0;
        ArrayList<String> s = new ArrayList<>();
        for (int i = 0; i < N + M; i++) {
            if (i < N) {
                s.add(br.readLine());
            } else {
                String sr = br.readLine();
                for (int j = 0; j < s.size(); j++) {
                    if (s.get(j).startsWith(sr)) {
                        answer++;
                        break;
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}