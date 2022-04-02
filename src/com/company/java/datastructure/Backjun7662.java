package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/7662
public class Backjun7662 {

    private static final String[] array = {
            "2\n" +
            "7\n" +
            "I 16\n" +
            "I -5643\n" +
            "D -1\n" +
            "D 1\n" +
            "D 1\n" +
            "I 123\n" +
            "D -1\n" +
            "9\n" +
            "I -45\n" +
            "I 653\n" +
            "D 1\n" +
            "I -642\n" +
            "I 45\n" +
            "I 97\n" +
            "D 1\n" +
            "D -1\n" +
            "I 333"
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
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int Q = Integer.parseInt(br.readLine());
            TreeMap<Long, Long> map = new TreeMap<>();
            boolean check = true;
            for (int q = 0; q < Q; q++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char k = st.nextToken().charAt(0);
                long n = Integer.parseInt(st.nextToken());
                if (check) {
                    if (k == 'I') {
                        map.put(n, map.getOrDefault(n, 0L) + 1);
                    } else {
                        if (map.isEmpty()) {
                            check = false;
                        } else {
                            if (n == -1) {
                                long key = map.firstKey();
                                if (map.get(key) == 1) {
                                    map.remove(key);
                                } else {
                                    map.put(key, map.get(key) - 1);
                                }
                            } else {
                                long key = map.lastKey();
                                if (map.get(key) == 1) {
                                    map.remove(key);
                                } else {
                                    map.put(key, map.get(key) - 1);
                                }
                            }
                        }
                    }
                }
            }

            if (!map.isEmpty()) {
                bw.write(String.valueOf(map.lastKey()));
                bw.write(" ");
                bw.write(String.valueOf(map.firstKey()));
            } else {
                bw.write("EMPTY");
            }

            if (t != T - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }
}

