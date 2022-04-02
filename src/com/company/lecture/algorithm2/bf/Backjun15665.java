package com.company.lecture.algorithm2.bf;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/15665
public class Backjun15665 {
    private static final int[] a = new int[10];
    private static final LinkedHashSet<String> t = new LinkedHashSet<>();

    private static final String[] array = {
            "3 1\n" +
            "4 4 2",
            "4 2\n" +
            "9 7 9 1",
            "4 4\n" +
            "1 1 2 2"
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
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        recursion(0, n, m, nums);
        for (String output : t) {
            bw.write(output);
        }

        bw.flush();
        t.clear();
    }

    private static void recursion(int index, int n, int m, int[] nums) {
        if (index == m) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                sb.append(a[i]);
                if (i != m -1) {
                    sb.append(" ");
                }
            }

            sb.append("\n");
            t.add(sb.toString());
            return;
        }

        for (int i = 0; i < n; i++) {
            a[index] = nums[i];
            recursion(index + 1, n, m, nums);
        }
    }
}
