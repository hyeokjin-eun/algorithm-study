package com.company.datastructure;

import java.io.*;
import java.util.Stack;

// link
// https://www.acmicpc.net/problem/5002
public class Backjun5002 {
    private static final String[] array = {
            "1\n" +
            "MWWMWMMWM",
            "2\n" +
            "WMMMMWWMMMWWMW"
    };
    private static IOBuffered ioBuffered;
    private static int X;
    private static char[] temp;
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
        setInputData();
        setAnswer();
        printOutData();
    }

    private static void setInputData() throws IOException {
        X = stoi(ioBuffered.readLine());
        temp = ioBuffered.readLine().toCharArray();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        Stack<Character> stack = new Stack<>();
        for (int i = temp.length - 1; 0 <= i; i--) {
            stack.push(temp[i]);
        }

        int man = 0;
        int women = 0;
        while (!stack.isEmpty()) {
            char cur = stack.pop();
            if (cur == 'M') {
                int nMan = man + 1;
                if (Math.abs(nMan - women) <= X) {
                    man++;
                } else if (!stack.isEmpty() && X < Math.abs(nMan - women)) {
                    char next = stack.pop();
                    if (next == 'W') {
                        int nWomen = women + 1;
                        if (Math.abs(nWomen - man) <= X) {
                            women++;
                            stack.push(cur);
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                int nWomen = women + 1;
                if (Math.abs(nWomen - man) <= X) {
                    women++;
                } else if (!stack.isEmpty() && X < Math.abs(nWomen - man)) {
                    char next = stack.pop();
                    if (next == 'W') {
                        int nMan = man + 1;
                        if (Math.abs(nMan - women) <= X) {
                            man++;
                            stack.push(cur);
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        answer = man + women;
    }

    private static void printOutData() throws IOException {
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

        public static IOBuffered create(String input) {
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
