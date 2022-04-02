package com.company.java.lecture.algorithm2.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/3085
public class Backjun3085 {
    private static final String[] array = {
            "3\n" +
            "CCP\n" +
            "CCP\n" +
            "PPC",
            "4\n" +
            "PPPP\n" +
            "CYZY\n" +
            "CCPY\n" +
            "PPCC",
            "5\n" +
            "YCPZY\n" +
            "CYZZP\n" +
            "CCPPP\n" +
            "YCYZC\n" +
            "CPPZZ",
            "3\n" +
            "YCP\n" +
            "PZY\n" +
            "YYY"
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
        int index = Integer.parseInt(br.readLine());
        char[][] candy = new char[index][index];
        for (int i = 0; i < index; i++) {
            char[] inputs = br.readLine().toCharArray();
            for (int j = 0; j < index; j++) {
                candy[i][j] = inputs[j];
            }
        }

        int answer = 1;
        for (int i = 0; i < index; i++) {
            // 가로 Change
            for (int j = 1; j < index; j++) {
                char temp = candy[i][j];
                candy[i][j] = candy[i][j - 1];
                candy[i][j - 1] = temp;
                int count = check(i, j - 1,false, index, candy);
                if (answer < count) {
                    answer = count;
                }

                candy[i][j - 1] = candy[i][j];
                candy[i][j] = temp;
            }

            // 세로 Change
            for (int j = 1; j < index; j++) {
                char temp = candy[j][i];
                candy[j][i] = candy[j - 1][i];
                candy[j - 1][i] = temp;
                int count = check(j - 1, i, true, index, candy);
                if (answer < count) {
                    answer = count;
                }

                candy[j - 1][i] = candy[j][i];
                candy[j][i] = temp;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int check (int index, int change, boolean check, int max, char[][] candy) {
        int count = 0;
        int rowMax = 1;
        int columnMax = 1;
        if (check) {
            rowMax++;
        } else {
            columnMax++;
        }

        // 가로 Check
        for (int i = index; i < index + rowMax; i++) {
            int temp = 1;
            for (int j = 1; j < max; j++) {
                if (candy[i][j - 1] == candy[i][j]) {
                    temp++;
                } else {
                    if (count < temp) {
                        count = temp;
                    }

                    temp = 1;
                }
            }

            if (count < temp) {
                count = temp;
            }
        }

        // 세로 Check
        for (int i = change; i < change + columnMax; i++) {
            int temp = 1;
            for (int j = 1; j < max; j++) {
                if (candy[j - 1][i] == candy[j][i]) {
                    temp++;
                } else {
                    if (count < temp) {
                        count = temp;
                    }

                    temp = 1;
                }
            }

            if (count < temp) {
                count = temp;
            }
        }

        return count;
    }
}
