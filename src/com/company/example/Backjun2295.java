package com.company.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Backjun2295 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }

        arr.sort(Integer::compareTo);
        int result = solve(arr, n);
        bw.write(result + "\n");
        bw.flush();
    }

    private static int solve(ArrayList<Integer> arr, int n) {
        Set<Integer> memory = new HashSet<>();
        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memory.add(arr.get(i) + arr.get(j));
            }
        }

        ArrayList<Integer> temp = new ArrayList<>(memory);
        temp.sort(Integer::compareTo);
        for (int i = n - 1; 0 <= i; i--) {
            for (int j = 0; j < n; j++) {
                int target = arr.get(i) - arr.get(j);
                int left = 0;
                int right = temp.size() - 1;
                while (left <= right) {
                    int mid = (right + left) / 2;

                    if (temp.get(mid) == target) {
                        return arr.get(i);
                    } else if (temp.get(mid) < target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }

        return 0;
    }
}
