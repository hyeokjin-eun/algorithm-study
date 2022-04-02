package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16165
public class Backjun16165 {
    private static final String[] array = {
            "3 4\n" +
            "twice\n" +
            "9\n" +
            "jihyo\n" +
            "dahyeon\n" +
            "mina\n" +
            "momo\n" +
            "chaeyoung\n" +
            "jeongyeon\n" +
            "tzuyu\n" +
            "sana\n" +
            "nayeon\n" +
            "blackpink\n" +
            "4\n" +
            "jisu\n" +
            "lisa\n" +
            "rose\n" +
            "jenny\n" +
            "redvelvet\n" +
            "5\n" +
            "wendy\n" +
            "irene\n" +
            "seulgi\n" +
            "yeri\n" +
            "joy\n" +
            "sana\n" +
            "1\n" +
            "wendy\n" +
            "1\n" +
            "twice\n" +
            "0\n" +
            "rose\n" +
            "1"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[] tn  = new String[n];
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String team = br.readLine();
            int member = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < member; j++) {
                String name = br.readLine();
                sb.append(name);
                if (j != member - 1) {
                    sb.append(" ");
                }
            }

            tn[i] = team;
            map.put(team, sb.toString());
        }

        for (int i = 0; i < m; i++) {
            String name = br.readLine();
            int type = Integer.parseInt(br.readLine());
            if (type == 1) {
                for (int j = 0; j < n; j++) {
                    String temp = map.get(tn[j]);
                    if (temp.contains(name)) {
                        bw.write(tn[j]);
                        bw.write("\n");
                    }
                }
            } else {
                String[] temp = map.get(name).split(" ");
                Arrays.sort(temp);
                for (int j = 0; j < temp.length; j++) {
                    bw.write(temp[j]);
                    bw.write("\n");
                }
            }
        }

        bw.flush();
    }
}
