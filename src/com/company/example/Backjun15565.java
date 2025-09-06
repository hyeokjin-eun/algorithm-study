package com.company.example;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Backjun15565 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        ArrayList<Integer> arr = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        int answer = Integer.MAX_VALUE;
        int left = 0;
        int count = 0;

        for (int right = 0; right < n; right++) {
            if (arr.get(right) == 1) count++;

            while (count >= k) {
                answer = Math.min(answer, right - left + 1);
                if (arr.get(left) == 1) count--;
                left++;
            }
        }

        bw.write((answer == Integer.MAX_VALUE ? -1 : answer) + "\n");
        bw.flush();
    }
}
