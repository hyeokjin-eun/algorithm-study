package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2529
public class Backjun2529 {
    private static final String[] array = {
            "2\n" +
            "< >",
            "9\n" +
            "> < < < > > > < <"
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
        int K = Integer.parseInt(br.readLine());
        char[] a = new char[K];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            a[i] = st.nextToken().charAt(0);
        }

        HashSet<String> ans = new HashSet<>();
        boolean[] check = new boolean[10];
        for (int i = 0; i < 10; i++) {
            check[i] = true;
            recursion(ans, K, 0, String.valueOf(i), check, a, i);
            check[i] = false;
        }

        ArrayList<String> answer = new ArrayList<>(ans);
        answer.sort(Comparator.naturalOrder());
        bw.write(answer.get(answer.size() - 1));
        bw.write("\n");
        bw.write(answer.get(0));
        bw.flush();
    }

    private static void recursion(HashSet<String> ans, int K, int index, String s, boolean[] check, char[] a, int before) {
        if (s.length() == K + 1) {
            ans.add(s);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (!check[i]) {
                if (a[index] == '<' && before < i) {
                    check[i] = true;
                    recursion(ans, K, index + 1, s + i, check, a, i);
                    check[i] = false;
                } else if (a[index] == '>' && before > i) {
                    check[i] = true;
                    recursion(ans, K, index + 1, s + i, check, a, i);
                    check[i] = false;
                }
            }
        }
    }
}