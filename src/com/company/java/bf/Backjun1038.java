package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1038
public class Backjun1038 {
    private static final String[] array = {
            "12",
            "18",
            "0",
            "100",
            "1000000"
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
        int N = Integer.parseInt(br.readLine());
        if (N > 1022) {
            bw.write(String.valueOf(-1));
        } else {
            ArrayList<Long> list = new ArrayList<>();
            for(int i=0; i<10; i++){
                getDecNum(i, 1, list);
            }

            Collections.sort(list);
            bw.write(String.valueOf(list.get(N)));
        }

        bw.flush();
    }

    private static ArrayList<Long> getDecNum(long num, int temp, ArrayList<Long> list){
        if(temp > 10){
            return list;
        }

        list.add(num);

        for(int i=0; i<10; i++){
            if(num%10 > i){
                getDecNum((num*10)+i, temp+1, list);
            }
        }
        return list;
    }
}
