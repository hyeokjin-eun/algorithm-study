package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/9019
public class Backjun9019 {
    private static final String[] array = {
            "3\n" +
            "1234 3412\n" +
            "1000 1\n" +
            "1 16"
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
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            String c = bfs(s, e);
            bw.write(c);
            if (t != T - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static String bfs(int s, int e) {
        boolean[] check = new boolean[10001];
        Queue<Integer> q = new LinkedList<>();
        Queue<String> a = new LinkedList<>();
        q.add(s);
        a.add("");
        check[s] = true;
        while (!q.isEmpty()) {
            int n = q.poll();
            String c = a.poll();
            if (n == e) {
                return c;
            }

            int nn = n;
            int cd = nn * 2 % 10000;
            if (!check[cd]) {
                q.add(cd);
                a.add(c + "D");
                check[cd] = true;
            }

            if (nn - 1 == -1) {
                if (!check[9999]) {
                    q.add(9999);
                    a.add(c + "S");
                    check[9999] = true;
                }
            } else {
                if (!check[nn - 1]) {
                    q.add(nn - 1);
                    a.add(c + "S");
                    check[nn - 1] = true;
                }
            }

            int il = (nn % 1000) * 10 + nn / 1000;
            if (!check[il]) {
                q.add(il);
                a.add(c + "L");
                check[il] = true;
            }

            int ir = (nn / 10) + (nn % 10) * 1000;
            if (!check[ir]) {
                q.add(ir);
                a.add(c + "R");
                check[ir] = true;
            }
        }

        return "";
    }
}