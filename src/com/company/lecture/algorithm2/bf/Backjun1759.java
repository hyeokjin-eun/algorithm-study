package com.company.lecture.algorithm2.bf;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1759
public class Backjun1759 {
    private static final boolean[] check = new boolean[16];
    private static final char[] answer = new char[16];

    private static final String[] array = {
            "4 6\n" +
            "a t c i s w",
            "3 5\n" +
            "a e i b c"
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
        String[] nums = br.readLine().split(" ");
        int l = Integer.parseInt(nums[0]);
        int c = Integer.parseInt(nums[1]);
        char[] a = new char[c];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            a[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(a);
        StringBuilder sb = dp(1, l + 1, c, a);
        bw.write(sb.toString());
        bw.flush();
    }

    private static StringBuilder dp(int index, int l, int c, char[] a) {
        if (index == l && check(answer, l)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < l; i++) {
                sb.append(answer[i]);
            }

            sb.append("\n");
            return sb;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c; i++) {
            if (check[i] || a[i] < answer[index - 1]) {
                continue;
            }

            check[i] = true;
            answer[index] = a[i];
            sb.append(dp(index + 1, l, c, a));
            check[i] = false;
        }

        return sb;
    }

    private static boolean check(char[] answer, int l) {
        int mo = 0;
        int ja = 0;
        for (int i = 1; i < l; i++) {
            if (answer[i] == 'a' || answer[i] == 'e' || answer[i] == 'i' || answer[i] == 'o' || answer[i] == 'u') {
                mo++;
            } else {
                ja++;
            }
        }

        return ja >= 2 && mo >= 1;
    }
}
