package com.company.example;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Backjun1253 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> arr = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        int answer = 0;
        arr.sort(Integer::compareTo);
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = arr.size() - 1;
            while (left < right) {
                if (left == i) {
                    left = left + 1;
                    continue;
                }

                if (right == i) {
                    right = right - 1;
                    continue;
                }

                int sum = arr.get(left) + arr.get(right);
                if (sum == arr.get(i)) {
                    answer++;
                    break;
                } else if (sum < arr.get(i)) {
                    left = left + 1;
                } else {
                    right = right - 1;
                }
            }
        }

        bw.write(answer + "\n");
        bw.flush();
    }
}
