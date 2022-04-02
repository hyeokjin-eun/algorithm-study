package com.company.greedy;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1285
// TODO: 2021-10-15 비트 마스크 재 수강 후 다시
public class Backjun1285 {
    private static final String[] array = {
            "3\n" +
            "HHT\n" +
            "THH\n" +
            "THT",
            "17\n" + // 93
            "THHTHHHHHTTTTTTTH\n" +
            "THTHHTHHTHHTTHTHT\n" +
            "HTTTHTTHTTHTTTHTH\n" +
            "HTTTTTTHHTHHHHHHH\n" +
            "HHTHTHHHTTHTTHHHH\n" +
            "HHTHHTHTTHHHTTTTT\n" +
            "HHHTHTHTTHHHTTTTH\n" +
            "HHTHTTTHTHHHTHHHT\n" +
            "TTTTTTTTTTHTHHHHH\n" +
            "THHTHTHTHTHTTTHHT\n" +
            "HHHHTHTHHTHTTHHHH\n" +
            "TTHTHTTTHTTHTHTTH\n" +
            "TTHHTHHHTTHTTTTTT\n" +
            "HTHHHHHHHTTHTTHHH\n" +
            "HHTHTHTTHHTHHHTHH\n" +
            "HHTTTHHHTHHTTTTHH\n" +
            "TTHHHTTTHTTTTHTHT",
            "19\n" + // 120
            "HTTHHTHTTHTHHTTHTTH\n" +
            "THHTTTTHTTTTHTTTTHH\n" +
            "THHTHHTHTHTTTTTHHHT\n" +
            "THTHHHTHHHTTTHTHHTH\n" +
            "TTHHHTTHTHHHHTTHTHT\n" +
            "THHHHHHTTHTTHTTHTHH\n" +
            "THHTTHTTTTHTHTTTHTT\n" +
            "TTHHHHHHTHTHHHTTTHH\n" +
            "TTHHHTHTTHHHHTHHTTH\n" +
            "HHTHHTHHHTHTHTTHHTH\n" +
            "HTTTHTTTHTHHHTHTTTT\n" +
            "HHHHHHTTTTTHHHHTHTH\n" +
            "THTHHTTTHHTTHTHTHTT\n" +
            "THHHTHTHTHTHHHTTHHH\n" +
            "THTTTTTHHTHTHTTTTTT\n" +
            "THHTTHHHHHTTHTTTTTT\n" +
            "HTHTHHTTTTTTHHHTTHH\n" +
            "TTTHTHHTHTHHTHTTTTH\n" +
            "HTTHHHTHTHTTHHHTHHT",
            "20\n" + // 137
            "THHTHTTHTTHTHHHTTHTH\n" +
            "TTTTHHTTHTTTTTHTHTTT\n" +
            "TTHHTHTHHTHTHHHTTHTT\n" +
            "THTHTTHHHTTTHHHHTTTT\n" +
            "HHTHHTHTTTHHHTHTTTHT\n" +
            "HTHHHTTHHHTHTTTTTHTT\n" +
            "THTTHTTTTTTHHHHTHHHH\n" +
            "HTTHHHTHTTHTHTTHHTTT\n" +
            "HHTHHTTTTTHHTHHTTHHH\n" +
            "HHHTTHTHHTTTHHTHHHHH\n" +
            "HHTTTHTTTTHHHHHTTHHH\n" +
            "HTHTHTHHHTTTHHHHHHTT\n" +
            "HTHTTHTTHHTHHHTTTHTH\n" +
            "TTTTTHHTTHTHHTHTHHTT\n" +
            "TTTTTHHTHTTTTHTHHHHT\n" +
            "HHHHTTTTTHHTHTHHTTHT\n" +
            "HTTHHHHTTHTTTTTHTHTH\n" +
            "HTTHHHTTHTHTTHHHHHTT\n" +
            "HHHTHTHTTHTHTTHTTHHH\n" +
            "HTTTHHHHTHHHTHHHTHHT\n" +
            "137"
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
        char[][] a = new char[N][N];
        for (int i = 0; i < N; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                a[i][j] = temp[j];
            }
        }

        int ans = N*N;
        for (int state=0; state<(1<<N); state++) {
            int sum = 0;
            for (int j=0; j<N; j++) {
                int cnt = 0;
                for (int i=0; i<N; i++) {
                    char cur = a[i][j];
                    if ((state & (1 << i)) != 0) {
                        cur = flip(cur);
                    }
                    if (cur == 'T') {
                        cnt += 1;
                    }
                }
                sum += Math.min(cnt, N-cnt);
            }
            if (ans > sum) ans = sum;
        }

        System.out.println(ans);
    }

    static char flip(char x) {
        if (x == 'H') {
            return 'T';
        } else {
            return 'H';
        }
    }
}