package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2143
public class Backjun2143 {
    private static int T;
    private static int N;
    private static int M;
    private static int[] A;
    private static int[] B;
    private static StringTokenizer st;
    private static final String[] array = {
            "5\n" +
            "4\n" +
            "1 3 1 2\n" +
            "3\n" +
            "1 3 2"
    };

    public static void main (String[] args) throws IOException {
        // TEST
        test();
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
        T = stoi(br.readLine());
        N = stoi(br.readLine());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = stoi(st.nextToken());
        }

        M = stoi(br.readLine());
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = stoi(st.nextToken());
        }

        ArrayList<Integer> a = getArrayList(A, N);
        ArrayList<Integer> b = getArrayList(B, M);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < b.size(); i++) {
            if (map.containsKey(b.get(i))) {
                map.put(b.get(i), map.get(b.get(i)) + 1);
            } else {
                map.put(b.get(i), 1);
            }
        }

        long answer = 0;
        for (int i = 0; i < a.size(); i++) {
            if (map.containsKey(T - a.get(i))) {
                answer += map.get(T - a.get(i));
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static ArrayList<Integer> getArrayList(int[] at, int am) {
        ArrayList<Integer> t = new ArrayList<>();
        for (int i = 0; i < am; i++) {
            int sum = 0;
            for (int j = i; j < am; j++) {
                sum += at[j];
                t.add(sum);
            }
        }

        return t;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {
        int[] test = new int[]{1, 2, 3};
        ArrayList<Integer> t = getArrayList(test, 3);
        System.out.println(t);
    }
}