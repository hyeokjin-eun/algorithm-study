package com.company.string;

import java.io.*;
import java.util.*;

public class Backjun9536 {

    private static final String[] array = {
            "1\n" +
            "toot woof wa ow ow ow pa blub blub pa toot pa blub pa pa ow pow toot\n" +
            "dog goes woof\n" +
            "fish goes blub\n" +
            "elephant goes toot\n" +
            "seal goes ow\n" +
            "what does the fox say?",
            "1\n" +
            "woof pow\n" +
            "dog goes woof\n" +
            "what does the fox say?",
            "2\n" +
            "toot woof wa ow ow ow pa pow\n" +
            "dog goes woof\n" +
            "fish goes blub\n" +
            "elephant goes toot\n" +
            "seal goes ow\n" +
            "what does the fox say?\n" +
            "wa moo pa pow umm moo woof\n" +
            "doge goes wa\n" +
            "snake goes pa\n" +
            "fish goes moo\n" +
            "what does the fox say?"
    };
    private static IOBuffered ioBuffered;
    private static String[] testCase;
    private static int T;
    private static String[] answer;
    private static Map<String, String>[] map;

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
        //TODO Algorithm Start
        ioBuffered = IOBuffered.create(input);
        setData();
        setAnswer();
        printAnswer();
    }

    private static void setData() throws IOException {
        T = stringToInteger(ioBuffered.readLine());
        map = new HashMap[T];
        testCase = new String[T];
        for (int i = 0; i < T; i++) {
            testCase[i] = ioBuffered.readLine();
            map[i] = new HashMap<>();
            while (true) {
                String temp = ioBuffered.readLine();
                if ( ! temp.equals("what does the fox say?")) {
                    StringTokenizer stringTokenizer = new StringTokenizer(temp);
                    String a = stringTokenizer.nextToken();
                    stringTokenizer.nextToken();
                    String b = stringTokenizer.nextToken();
                    map[i].put(b, a);
                } else {
                    break;
                }
            }
        }
    }

    private static int stringToInteger(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = new String[T];
        for (int i = 0; i < T; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            StringTokenizer stringTokenizer = new StringTokenizer(testCase[i]);
            while (stringTokenizer.hasMoreTokens()) {
                String temp = stringTokenizer.nextToken();
                if ( ! map[i].containsKey(temp)) {
                    stringBuilder.append(temp);
                    if (stringTokenizer.hasMoreTokens()) {
                        stringBuilder.append(" ");
                    }
                }

                answer[i] = stringBuilder.toString();
            }
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

        /**
         * IOBuffered Create
         * @param input Input String
         * @return IOBuffered Instance
         */
        public static IOBuffered create(String input) throws IOException {
            return new IOBuffered(input);
        }

        /**
         * BufferedReader Read Line
         * @return BufferedReader.readLIne
         */
        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        /**
         * Console Print Out (BufferedWriter.write())
         */
        public void print(String[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(String[] answer) throws IOException {
            //TODO Answer Write Implement
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
