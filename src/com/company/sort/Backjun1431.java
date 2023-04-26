package com.company.sort;

import java.io.*;
import java.util.Arrays;

// link
// https://www.acmicpc.net/problem/1431
public class Backjun1431 {

    private static final String[] array = {
            "5\n" +
            "ABCD\n" +
            "145C\n" +
            "A\n" +
            "A910\n" +
            "Z321",
            "2\n" +
            "Z19\n" +
            "Z20",
            "4\n" +
            "34H2BJS6N\n" +
            "PIM12MD7RCOLWW09\n" +
            "PYF1J14TF\n" +
            "FIPJOTEA5",
            "5\n" +
            "ABCDE\n" +
            "BCDEF\n" +
            "ABCDA\n" +
            "BAAAA\n" +
            "ACAAA"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static String[] temp;
    private static String[] answer;

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
        temp = new String[N];
        answer = new String[N];
        for (int i = 0; i < N; i++) {
            temp[i] = ioBuffered.readLine();
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        Arrays.sort(temp, (o1, o2) -> {
            if (o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }

            int o1s = 0;
            int o2s = 0;
            for (int i = 0; i < o1.length(); i++) {
                if ('0' <= o1.charAt(i) && o1.charAt(i) <= '9') {
                    o1s += o1.charAt(i) - '0';
                }

                if ('0' <= o2.charAt(i) && o2.charAt(i) <= '9') {
                    o2s += o2.charAt(i) - '0';
                }
            }

            if (o1s != o2s) {
                return o1s - o2s;
            }

            return o1.compareTo(o2);
        });

        answer = temp;
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

        public void print(String[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(String[] answer) throws IOException {
            for (int i = 0; i < answer.length; i++) {
                bufferedWriter.write(answer[i]);
                if (i != answer.length - 1) {
                    bufferedWriter.write("\n");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
