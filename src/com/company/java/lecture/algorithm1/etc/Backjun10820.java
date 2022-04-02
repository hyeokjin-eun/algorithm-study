package com.company.java.lecture.algorithm1.etc;

import java.io.*;

public class Backjun10820 {
    private static final String[] array = {
            "This is String\n" +
            "SPACE    1    SPACE\n" +
            " S a M p L e I n P u T     \n" +
            "0L1A2S3T4L5I6N7E8"
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
        while (true) {
            String sentences = br.readLine();
            if (sentences == null) {
                break;
            }

            int[] countArray = new int[4];
            for (char charInput : sentences.toCharArray()) {
                if (charInput >= 'a' && charInput <= 'z') {
                    countArray[0]++;
                } else if (charInput >= 'A' && charInput <= 'Z') {
                    countArray[1]++;
                } else if (charInput >= '0' && charInput <= '9') {
                    countArray[2]++;
                } else {
                    countArray[3]++;
                }
            }

            for (int count : countArray) {
                bw.write(String.valueOf(count));
                bw.write(" ");
            }

            bw.write("\n");
        }

        bw.flush();
    }
}
