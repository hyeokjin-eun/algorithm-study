package com.company.example;

import java.io.*;
import java.util.*;

public class Backjun11659 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] temp = new int[n];
        temp[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < n; i++) {
            temp[i] = Integer.parseInt(st.nextToken()) + temp[i - 1];
        }

        for (int i = 0; i < m; i++) {
            if (i != 0) {
                bw.write("\n");
            }

            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken()) - 1;
            int n2 = Integer.parseInt(st.nextToken()) - 1;

            if (n1 == 0) {
                bw.write(String.valueOf(temp[n2]));
            } else {
                bw.write(String.valueOf(temp[n2] - temp[n1 - 1]));
            }
        }

        bw.flush();
    }
}
