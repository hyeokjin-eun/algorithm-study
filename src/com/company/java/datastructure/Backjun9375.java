package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/9375
public class Backjun9375 {
    private static final String[] array = {
            "2\n" +
            "3\n" +
            "hat headgear\n" +
            "sunglasses eyewear\n" +
            "turban headgear\n" +
            "3\n" +
            "mask face\n" +
            "sunglasses face\n" +
            "makeup face"
    };

    public static void main(String[] args) throws IOException {
        // TEST
        test();
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

    private static void test() {
        String[][] s2 = new String[][]{
                {"hat", "headgear"},
                {"sunglasses", "eyewear"},
                {"turban", "headgear"}
        };

        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < s2.length; i++) {
            String[] s = s2[i];
            if (map.containsKey(s2[i][1])) {
                map.get(s2[i][1]).add(s2[i][0]);
            } else {
                map.put(s2[i][1], new ArrayList<>());
                map.get(s2[i][1]).add("");
                map.get(s2[i][1]).add(s2[i][0]);
            }
        }

        int answer = 1;
        for (String key : map.keySet()) {
            answer *= map.get(key).size();
        }

        answer -= 1;
        System.out.println(answer);
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int T = stoi(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = stoi(br.readLine());
            HashMap<String, ArrayList<String>> map = new HashMap<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String cate = st.nextToken();
                if (!map.containsKey(cate)) {
                    map.put(cate, new ArrayList<>());
                    map.get(cate).add("");

                }

                map.get(cate).add(name);
            }

            int answer = 1;
            for (String key : map.keySet()) {
                answer *= map.get(key).size();
            }

            bw.write(String.valueOf(answer - 1));
            if (t != T - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}