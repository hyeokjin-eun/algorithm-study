package com.company.java.lecture.algorithm2.bf;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/14500
public class Backjun14500 {
    private static final int[][][] blocks = {
            {{0,1}, {0,2}, {0,3}},
            {{1,0}, {2,0}, {3,0}},
            {{1,0}, {1,1}, {1,2}},
            {{0,1}, {1,0}, {2,0}},
            {{0,1}, {0,2}, {1,2}},
            {{1,0}, {2,0}, {2,-1}},
            {{0,1}, {0,2}, {-1,2}},
            {{1,0}, {2,0}, {2,1}},
            {{0,1}, {0,2}, {1,0}},
            {{0,1}, {1,1}, {2,1}},
            {{0,1}, {1,0}, {1,1}},
            {{0,1}, {-1,1}, {-1,2}},
            {{1,0}, {1,1}, {2,1}},
            {{0,1}, {1,1}, {1,2}},
            {{1,0}, {1,-1}, {2,-1}},
            {{0,1}, {0,2}, {-1,1}},
            {{0,1}, {0,2}, {1,1}},
            {{1,0}, {2,0}, {1,1}},
            {{1,0}, {2,0}, {1,-1}}
    };

    private static final String[] array = {
            "5 5\n" +
            "1 2 3 4 5\n" +
            "5 4 3 2 1\n" +
            "2 3 4 5 6\n" +
            "6 5 4 3 2\n" +
            "1 2 1 2 1",
            "4 5\n" +
            "1 2 3 4 5\n" +
            "1 2 3 4 5\n" +
            "1 2 3 4 5\n" +
            "1 2 3 4 5",
            "4 10\n" +
            "1 2 1 2 1 2 1 2 1 2\n" +
            "2 1 2 1 2 1 2 1 2 1\n" +
            "1 2 1 2 1 2 1 2 1 2\n" +
            "2 1 2 1 2 1 2 1 2 1"
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[][] t = new int[n][m];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                t[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int[][] block : blocks) {
                    int temp = t[i][j];
                    for (int[] inner : block) {
                        if (0 <= i + inner[0] && i + inner[0] < n && 0 <= j + inner[1] && j + inner[1] < m) {
                            temp += t[i + inner[0]][j + inner[1]];
                        } else {
                            break;
                        }
                    }

                    if (answer < temp) {
                        answer = temp;
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
