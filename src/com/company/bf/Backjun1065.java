package com.company.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1065
public class Backjun1065 {

    private static final String[] array = {
            "110",
            "1",
            "210",
            "1000",
            "500"
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
        N = Util.stoi(ioBuffered.readLine());
    }

    private static void setAnswer() {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (check(i)) {
                count++;
            }
        }

        answer = count;
    }

    private static boolean check(int n) {
        String num = Util.itos(n);
        boolean init = true;
        int x = 0;
        for (int i = 1; i < num.length(); i++) {
            int temp = (num.charAt(i - 1) - '0') - (num.charAt(i) - '0');
            if (init) {
                init = false;
                x = temp;
                continue;
            }

            if (x != temp) {
                return false;
            }
        }

        return true;
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }

    private static class Util {
        public static int stoi(String s) {
            return Integer.parseInt(s);
        }

        public static String itos(int i) {
            return String.valueOf(i);
        }
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
