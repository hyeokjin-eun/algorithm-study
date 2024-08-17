package com.company.implement;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1244
public class Backjun1244 {

    private static final String[] array = {
            "8\n" +
            "0 1 0 1 0 0 0 1\n" +
            "2\n" +
            "1 3\n" +
            "2 3",
            "21\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "1\n" +
            "1 7"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int[] switchs;
    private static int M;
    private static int[][] students;
    private static int[] answer;

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

    private static void setData() throws IOException {
        StringTokenizer stringTokenizer;
        N = stoi(ioBuffered.readLine());
        stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        switchs = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            switchs[i] = stoi(stringTokenizer.nextToken());
        }

        M = stoi(ioBuffered.readLine());
        students = new int[M][2];
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(ioBuffered.readLine());
            students[i][0] = stoi(stringTokenizer.nextToken());
            students[i][1] = stoi(stringTokenizer.nextToken());
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        for (int i = 0; i < M; i++) {
            change(students[i][0], students[i][1]);
        }

        answer = arrayCopy(switchs, 1, N + 1);
    }

    private static void change(int age, int switchCount) {
        if (isMale(age)) {
            changeTheNumberInMultiple(switchCount);
        } else {
            changeTheNumberInTheRange(switchCount);
        }
    }

    private static boolean isMale(int age) {
        return age == 1;
    }

    private static void changeTheNumberInMultiple(int switchCount) {
        for (int i = switchCount; i <= N; i += switchCount) {
            changeSwitchToggle(i);
        }
    }

    private static void changeTheNumberInTheRange(int switchCount) {
        changeSwitchToggle(switchCount);
        for (int i = 1; switchCount + i <= N && 0 < switchCount - i; i++) {
            if (switchs[switchCount + i] != switchs[switchCount - i]) {
                break;
            }

            changeSwitchToggle(switchCount + i);
            changeSwitchToggle(switchCount - i);
        }
    }

    private static void changeSwitchToggle(int index) {
        switchs[index] = switchs[index] == 1 ? 0 : 1;
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }

    private static int[] arrayCopy(int[] origin, int start, int end) {
        return Arrays.copyOfRange(origin, start, end);
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

        public static IOBuffered create(String input) {
            return new IOBuffered(input);
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public void print(int[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int[] answer) throws IOException {
            for (int i = 0; i < answer.length; i++) {
                bufferedWriter.write(String.valueOf(answer[i]));
                if (i != answer.length - 1) {
                    if ((i + 1) % 20 == 0) {
                        bufferedWriter.write("\n");
                    } else {
                        bufferedWriter.write(" ");
                    }
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
