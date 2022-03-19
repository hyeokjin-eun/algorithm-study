package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/4358
public class Backjun4358 {
    private static final String[] array = {
            "Red Alder\n" +
            "Ash\n" +
            "Aspen\n" +
            "Basswood\n" +
            "Ash\n" +
            "Beech\n" +
            "Yellow Birch\n" +
            "Ash\n" +
            "Cherry\n" +
            "Cottonwood\n" +
            "Ash\n" +
            "Cypress\n" +
            "Red Elm\n" +
            "Gum\n" +
            "Hackberry\n" +
            "White Oak\n" +
            "Hickory\n" +
            "Pecan\n" +
            "Hard Maple\n" +
            "White Oak\n" +
            "Soft Maple\n" +
            "Red Oak\n" +
            "Red Oak\n" +
            "White Oak\n" +
            "Poplan\n" +
            "Sassafras\n" +
            "Sycamore\n" +
            "Black Walnut\n" +
            "Willow"
    };
    private static IOBuffered ioBuffered;
    private static int total;
    private static TreeMap<String, Integer> map;
    private static ArrayList<String> answer;

    public static void main (String[] args) throws IOException {
        // TEST
        test();
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

    private static void test() {

    }

    private static void solution(String input) throws IOException {
        ioBuffered = IOBuffered.create(input);
        setData();
        setAnswer();
        printAnswer();
    }

    private static void setData() throws IOException {
        total = 0;
        map = new TreeMap<>();
        while (true) {
            String wood = ioBuffered.readLine();
            if (isNotInput(wood)) {
                break;
            }

            addWood(wood);
            total++;
        }
    }

    private static boolean isNotInput(String wood) {
        return wood == null;
    }

    private static void addWood(String wood) {
        if (containsWood(wood)) {
            map.replace(wood, map.get(wood) + 1);
        } else {
            map.put(wood, 1);
        }
    }

    private static void setAnswer() {
        answer = new ArrayList<>();
        for (String key : map.keySet()) {
            String percent = String.format("%.04f", (double) map.get(key) * 100 / total);
            answer.add(key + " " + percent);
        }
    }

    private static boolean containsWood(String wood) {
        return map.containsKey(wood);
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

        public static IOBuffered create(String input) {
            return new IOBuffered(input);
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public void print(ArrayList<String> answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(ArrayList<String> answer) throws IOException {
            for (int i = 0; i < answer.size(); i++) {
                bufferedWriter.write(answer.get(i));
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
