package com.company.java.divideandconquer;

import java.io.*;
import java.util.*;

// link
// https://code.plus/lecture/517
// TODO: 2021-10-20 전위 + 중위 를 이용해 후위 찾아 내는것 해보기
public class Backjun517 {
    private static final String[] array = {
            "3\n" +
            "1 2 3\n" +
            "1 3 2"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] inorder = new int[N];
        for (int i = 0; i < N; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        int[] postorder = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        int[] position = new int[N + 1];
        for (int i = 0; i < N; i++) {
            position[inorder[i]] = i;
        }

        StringBuilder sb = new StringBuilder();
        solve(sb, 0, N - 1, 0, N - 1, postorder, position);
        bw.write(sb.toString());
        bw.flush();
    }

    private static void solve(StringBuilder sb, int ins, int ine, int pos, int poe, int[] postorder, int[] position) {
        if (ins > ine || pos > poe) {
           return;
        }

        int root = postorder[poe];
        sb.append(root).append(" ");
        int p = position[root];
        int l = p - ins;
        solve(sb, ins, p - 1, pos, pos + l - 1, postorder, position);
        solve(sb, p + 1, ine, pos + l, poe - 1, postorder, position);
    }
}