package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/17219
public class Backjun17219 {
    private static final String[] array = {
            "16 4\n" +
            "noj.am IU\n" +
            "acmicpc.net UAENA\n" +
            "startlink.io THEKINGOD\n" +
            "google.com ZEZE\n" +
            "nate.com VOICEMAIL\n" +
            "naver.com REDQUEEN\n" +
            "daum.net MODERNTIMES\n" +
            "utube.com BLACKOUT\n" +
            "zum.com LASTFANTASY\n" +
            "dreamwiz.com RAINDROP\n" +
            "hanyang.ac.kr SOMEDAY\n" +
            "dhlottery.co.kr BOO\n" +
            "duksoo.hs.kr HAVANA\n" +
            "hanyang-u.ms.kr OBLIVIATE\n" +
            "yd.es.kr LOVEATTACK\n" +
            "mcc.hanyang.ac.kr ADREAMER\n" +
            "startlink.io\n" +
            "acmicpc.net\n" +
            "noj.am\n" +
            "mcc.hanyang.ac.kr"
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
        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map.put(st.nextToken(), st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            bw.write(map.get(br.readLine()));
            if (i != M - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}