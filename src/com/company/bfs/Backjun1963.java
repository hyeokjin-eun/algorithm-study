package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1963
public class Backjun1963 {
    private static final String[] array = {
            "3\n" +
            "1033 8179\n" +
            "1373 8017\n" +
            "1033 1033"
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
        boolean[] eratosthenes = eratosthenes();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int answer = bfs(s, e, eratosthenes);
            if (answer == -1) {
                bw.write("Impossible");
            } else {
                bw.write(String.valueOf(answer));
            }

            if (t != T - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int bfs(int s, int e, boolean[] eratosthenes) {
        boolean[] check = new boolean[10000];
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> cq = new LinkedList<>();
        q.add(s);
        cq.add(0);
        check[s] = true;
        while (!q.isEmpty()) {
            int n = q.poll();
            int c = cq.poll();
            if (n == e) {
                return c;
            }

            int t1 = 10;
            int t2 = 1;
            for (int i = 0; i < 3; i++) {
                int num = ((n / t1) * t1) + (n % t2);
                for (int j = 0; j < 10; j++) {
                    if (!eratosthenes[num] && !check[num]) {
                        cq.add(c + 1);
                        check[num] = true;
                        q.add(num);
                    }

                    num += t2;
                }

                t1 *= 10;
                t2 *= 10;
            }

            int num = n % 1000 + 1000;
            for (int i = 1; i < 10; i++) {
                if (!eratosthenes[num] && !check[num]){
                    cq.add(c + 1);
                    check[num] = true;
                    q.add(num);
                }

                num += 1000;
            }
        }

        return -1;
    }

    private static boolean[] eratosthenes() {
        boolean[] eratosthenes = new boolean[10001];
        for (int i = 2; i < 10000; i++) {
            for (int j = i * i; j < 10000; j += i) {
                eratosthenes[j] = true;
            }
        }

        return eratosthenes;
    }
}