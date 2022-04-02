package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1620
public class Backjun1620 {
    private static final String[] array = {
            "26 5\n" +
            "Bulbasaur\n" +
            "Ivysaur\n" +
            "Venusaur\n" +
            "Charmander\n" +
            "Charmeleon\n" +
            "Charizard\n" +
            "Squirtle\n" +
            "Wartortle\n" +
            "Blastoise\n" +
            "Caterpie\n" +
            "Metapod\n" +
            "Butterfree\n" +
            "Weedle\n" +
            "Kakuna\n" +
            "Beedrill\n" +
            "Pidgey\n" +
            "Pidgeotto\n" +
            "Pidgeot\n" +
            "Rattata\n" +
            "Raticate\n" +
            "Spearow\n" +
            "Fearow\n" +
            "Ekans\n" +
            "Arbok\n" +
            "Pikachu\n" +
            "Raichu\n" +
            "25\n" +
            "Raichu\n" +
            "3\n" +
            "Pidgey\n" +
            "Kakuna"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            map.put(String.valueOf(i + 1), s);
            map.put(s, String.valueOf(i + 1));
        }

        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            bw.write(map.get(s));
            if (i != M - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }
}
