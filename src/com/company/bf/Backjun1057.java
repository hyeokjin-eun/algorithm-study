package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1057
public class Backjun1057 {
    private static int N;
    private static int p1;
    private static int p2;
    private static final String[] array = {
            "16 1 2",
            "16 8 9",
            "65536 1000 35000",
            "60000 101 891"
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
        N = stoi(st.nextToken());
        p1 = stoi(st.nextToken());
        p2 = stoi(st.nextToken());
        bw.write(String.valueOf(play()));
        bw.flush();
    }

    private static int play() {
        Queue<Integer> match = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            match.offer(i);
        }

        int round = 0;
        while (true) {
            Queue<Integer> winner = new LinkedList<>();
            round += 1;
            while (!match.isEmpty()) {
                int player1 = 0;
                int player2 = 0;
                if (!match.isEmpty()) {
                    player1 = match.poll();
                }

                if (!match.isEmpty()) {
                    player2 = match.poll();
                }

                int win;
                if (player1 == 0 || player2 == 0) {
                    win = player1 == 0 ? player2 : player1;
                } else {
                    if (player1 == p1 && player2 == p2) {
                        return round;
                    } else if (player1 == p2 && player2 == p1) {
                        return round;
                    } else {
                        if (player1 == p1 || player1 == p2) {
                            win = player1;
                        } else if (player2 == p1 || player2 == p2) {
                            win = player2;
                        } else {
                            win = Math.max(player1, player2);
                        }
                    }
                }

                if (win != 0) {
                    winner.offer(win);
                }
            }

            if (winner.isEmpty() || winner.size() == 1) {
                break;
            } else {
                match = winner;
            }
        }

        return -1;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}