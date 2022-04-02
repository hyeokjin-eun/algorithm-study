package com.company.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/10825
public class Backjun10825 {
    private static final String[] array = {
            "12\n" +
            "Junkyu 50 60 100\n" +
            "Sangkeun 80 60 50\n" +
            "Sunyoung 80 70 100\n" +
            "Soong 50 60 90\n" +
            "Haebin 50 60 100\n" +
            "Kangsoo 60 80 100\n" +
            "Donghyuk 80 60 100\n" +
            "Sei 70 70 70\n" +
            "Wonseob 70 70 90\n" +
            "Sanghyun 70 70 80\n" +
            "nsj 80 80 80\n" +
            "Taewhan 50 60 90"
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
        int N = Integer.parseInt(br.readLine());
        Pair[] a = new Pair[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());
            a[i] = new Pair(name, korean, english, math);
        }

        Arrays.sort(a, (o1, o2) -> {
            if (o1.korean == o2.korean) {
                if (o1.english == o2.english) {
                    if (o1.math == o2.math) {
                        return o1.name.compareTo(o2.name);
                    }

                    return o2.math - o1.math;
                }

                return o1.english - o2.english;
            }

            return o2.korean - o1.korean;
        });

        for (int i = 0; i < N; i++) {
            bw.write(a[i].name);
            if (i != N - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static class Pair {
        String name;
        int korean;
        int english;
        int math;
        public Pair(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }
    }
}
