package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1865
public class Backjun1865 {
    private static IOBuffered ioBuffered;
    private static int testCase;
    private static boolean[] answer;
    private static int city;
    private static int road;
    private static int wormhole;
    private static ArrayList<ArrayList<Route>> cityList;
    private static final String[] array = {
            "2\n" +
            "3 3 1\n" +
            "1 2 2\n" +
            "1 3 4\n" +
            "2 3 1\n" +
            "3 1 3\n" +
            "3 2 1\n" +
            "1 2 3\n" +
            "2 3 4\n" +
            "3 1 8"
    };

    public static void main(String[] args) throws IOException {
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
        setAnswers();
        printOutputData();
    }

    private static void setAnswers() throws IOException {
        testCase = stoi(ioBuffered.readLine());
        answer = new boolean[testCase];
        for (int i = 0; i < testCase; i++) {
            answer[i] = getAnswer();
        }
    }

    private static boolean getAnswer() throws IOException {
        setInputData();
        return searchAllCity();
    }

    private static void setInputData() throws IOException {
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        city = stoi(st.nextToken());
        road = stoi(st.nextToken());
        wormhole = stoi(st.nextToken());
        setCityList();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setCityList() throws IOException {
        createCityList();
        setCity();
    }

    private static void createCityList() {
        cityList = new ArrayList<>();
        for (int i = 0; i < city; i++) {
            cityList.add(new ArrayList<>());
        }
    }

    private static void setCity() throws IOException {
        setRoad();
        setWormhole();
    }

    private static void setRoad() throws IOException {
        for (int i = 0; i < road; i++) {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            int start = stoi(st.nextToken()) - 1;
            int end = stoi(st.nextToken()) - 1;
            int time = stoi(st.nextToken());
            cityList.get(start).add(Route.of(end, time));
            cityList.get(end).add(Route.of(start, time));
        }
    }

    private static void setWormhole() throws IOException {
        for (int i = 0; i < wormhole; i++) {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            int start = stoi(st.nextToken()) - 1;
            int end = stoi(st.nextToken()) - 1;
            int time = reverseTime(st.nextToken());
            cityList.get(start).add(Route.of(end, time));
        }
    }

    private static int reverseTime(String s) {
        return stoi(s) * -1;
    }

    /**
     * BellmanFord algorithm
     * link : https://ratsgo.github.io/data%20structure&algorithm/2017/11/27/bellmanford/
     */
    private static boolean bellmanFord(final int start) {
        int[] times = new int[city];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[start] = 0;
        for (int i = 0; i < city - 1; i++) {
            if (!setShortestDist(times)) break;
        }

        return times[start] < 0;
    }

    private static boolean setShortestDist(int[] times) {
        boolean ok = false;
        for (int j = 0; j < city; j++) {
            for (Route route : cityList.get(j)) {
                int end = route.getEnd();
                int time = route.getTime();
                if (times[j] != Integer.MAX_VALUE && times[end] > times[j] + time) {
                    times[end] = times[j] + time;
                    ok = true;
                }
            }
        }
        
        return ok;
    }

    private static boolean searchAllCity() {
        for (int i = 0; i < city; i++) {
            if (bellmanFord(i)) return true;
        }

        return false;
    }

    private static void printOutputData() throws IOException {
        ioBuffered.print(testCase, answer);
    }

    private static class Route {
        private final int end;
        private final int time;

        private Route(final int end,final int time) {
            this.end = end;
            this.time = time;
        }

        public static Route of(final int end,final int time) {
            return new Route(end, time);
        }

        public int getEnd() {
            return this.end;
        }

        public int getTime() {
            return this.time;
        }
    }

    private static class IOBuffered {
        private final BufferedReader bufferedReader;
        private final BufferedWriter bufferedWriter;

        private IOBuffered(final String input) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
            InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
        }

        public static IOBuffered create(final String input) {
            return new IOBuffered(input);
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public void print(final int testCase, boolean[] answer) throws IOException {
            write(testCase, answer);
            flush();
        }

        private void write(final int testCase, boolean[] answer) throws IOException {
            for (int i = 0; i < testCase; i++) {
               bufferedWriter.write(answer[i] ? "YES" : "NO");
               if (i != testCase - 1) {
                   bufferedWriter.write("\n");
               }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}