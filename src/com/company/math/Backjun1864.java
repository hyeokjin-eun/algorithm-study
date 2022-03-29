package com.company.math;

import com.company.bf.Backjun17173;

import java.io.*;
import java.util.*;

// link
//
public class Backjun1864 {
    private static final String[] array = {
            "(@&\n" +
            "?/--\n" +
            "/(\\\n" +
            "?\n" +
            "#"
    };
    private static IOBuffered ioBuffered;
    private static Map<String, Integer> map;
    private static ArrayList<Integer> answer;

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
        ioBuffered = IOBuffered.create(input);
        setData();
        setAnswer();
        printAnswer();
    }

    private static void setAnswer() throws IOException {
        answer = new ArrayList<>();
        while (true) {
            char[] cs = ioBuffered.readLine().toCharArray();
            if (isStop(cs)) {
                break;
            }

            int temp = 0;
            for (int i = 0; i < cs.length; i++) {
                temp += (Math.pow(8, i) * map.get(String.valueOf(cs[cs.length - i - 1])));
            }

            answer.add(temp);
        }
    }

    private static final String[] chars = new String[]{
            "-", "\\", "(", "@", "?", ">", "&", "%", "/"
    };

    private static final int[] nums = new int[]{
            0, 1, 2, 3, 4, 5, 6, 7, -1
    };

    private static void setData() {
        map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], nums[i]);
        }
    }

    private static boolean isStop(char[] cs) {
        return cs.length == 1 && cs[0] == '#';
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }

    private static class IOBuffered {
        private final BufferedReader bufferedReader;
        private final BufferedWriter bufferedWriter;

        private IOBuffered(String input) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
            InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
        }

        public static IOBuffered create(String input) throws IOException {
            return new IOBuffered(input);
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public void print(ArrayList<Integer> answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(ArrayList<Integer> answer) throws IOException {
            for (int i = 0; i < answer.size(); i++) {
                bufferedWriter.write(String.valueOf(answer.get(i)));
                if (i != answer.size() - 1) {
                    bufferedWriter.write("\n");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
