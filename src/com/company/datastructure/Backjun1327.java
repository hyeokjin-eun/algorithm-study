package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1327
public class Backjun1327 {
    private static final String[] array = {
            "3 3\n" +
            "3 2 1",
            "7 2\n" +
            "7 6 5 4 3 2 1",
            "7 2\n" +
            "2 1 3 4 6 5 7"
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
        char[] a = new char[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = st.nextToken().charAt(0);
        }

        bw.write(String.valueOf(bfs(a, K, N)));
        bw.flush();
    }

    private static int bfs(char[] a, int K, int N) {
        char[] b = a.clone();
        Arrays.sort(b);
        String answer = new String(b);
        Queue<Pair> q = new LinkedList<>();
        Set<String> s = new HashSet<>();
        q.offer(new Pair(new String(a), 0));
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            String str = pair.str;
            int cnt = pair.cnt;
            if (answer.equals(str)) {
                return cnt;
            }

            if (!s.contains(str)) {
                s.add(str);
                for (int i = 0; i < N - K + 1; i++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str, 0, i);
                    StringBuilder reverse = new StringBuilder();
                    reverse.append(str, i, i + K);
                    sb.append(reverse.reverse());
                    sb.append(str, i + K, N);
                    q.offer(new Pair(sb.toString(), cnt + 1));
                }
            }
        }

        return -1;
    }

    private static class Pair {
        String str;
        int cnt;
        public Pair(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }
    }
}
