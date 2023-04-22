package com.company.implement;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1475
public class Backjun1475 {

    private static final String[] array = {
            "9999",
            "122",
            "12635",
            "888888"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int answer;

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
        N = stoi(ioBuffered.readLine());
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static String itos(int i) {
        return String.valueOf(i);
    }

    private static void setAnswer() {
        String number = itos(N);
        init();
        int count = 1;
        for (int i = 0; i < number.length(); i++) {
            int j = number.charAt(i) - '0';
            if (check(j)) {
                use(j);
            } else {
                init();
                use(j);
                count++;
            }
        }

        answer = count;
    }

    private static boolean[] numbers;
    private static void init() {
        numbers = new boolean[10];
    }

    private static boolean check(int num) {
        if ((num == 9 || num == 6)) {
            if (!numbers[6]) {
                return true;
            } else if (!numbers[9]) {
                return true;
            }
        } else {
            if (!numbers[num]) {
                return true;
            }
        }

        return false;
    }

    private static void use(int num) {
        if (num == 6 || num == 9) {
            if (!numbers[6]) {
                numbers[6] = true;
            } else if (!numbers[9]) {
                numbers[9] = true;
            }
        } else {
            numbers[num] = true;
        }
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

        public void print(int answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int answer) throws IOException {
            bufferedWriter.write(String.valueOf(answer));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
