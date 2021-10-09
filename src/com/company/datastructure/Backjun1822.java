package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1822
public class Backjun1822 {
    private static final String[] array = {
            "4 3\n" +
            "2 5 11 7\n" +
            "9 7 4"
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
        int aN = Integer.parseInt(st.nextToken());
        int bN = Integer.parseInt(st.nextToken());
        HashSet<Long> a = new HashSet<>();
        HashSet<Long> b = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < aN; i++) {
            long num = Integer.parseInt(st.nextToken());
            a.add(num);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bN; i++) {
            long num = Integer.parseInt(st.nextToken());
            b.add(num);
        }

        ArrayList<Long> c = new ArrayList<>(a);
        ArrayList<Long> answer = new ArrayList<>();
        for (int i = 0; i < c.size(); i++) {
            if (a.contains(c.get(i))) {
                if (!b.contains(c.get(i))) {
                    answer.add(c.get(i));
                }
            }
        }

        answer.sort(Comparator.naturalOrder());
        bw.write(String.valueOf(answer.size()));
        bw.write("\n");
        for (int i = 0; i < answer.size(); i++) {
            bw.write(String.valueOf(answer.get(i)));
            if (i != answer.size() - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }
}
