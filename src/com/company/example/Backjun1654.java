package com.company.example;

import java.io.*;
import java.util.StringTokenizer;

public class Backjun1654 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        long max = 0;
        long[] len = new long[k];
        for (int i = 0; i < k; i++) {
            len[i] = Long.parseLong(br.readLine());
            max = Math.max(max, len[i]);
        }

        long l = 1;
        long r = max;
        long answer = 0;
        while (l <= r) {
            long mid = (l + r) / 2;
            long count = 0;
            for (int i = 0; i < k; i++) {
                count += len[i] != 0 ? len[i] / mid : 0;
            }

            if (n <= count) {
                answer = Math.max(answer, mid);
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
