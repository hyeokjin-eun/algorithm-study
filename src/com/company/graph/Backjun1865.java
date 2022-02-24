package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1865
public class Backjun1865 {
    private static IOBuffered ioBuffered;
    private static boolean[] answer;
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
        for (int i = 0; i < array.length; i++) {
            System.out.println("===== Test Case " + i + " Start =====");
            long before = System.currentTimeMillis();
            Solution.create().solution(array[i]);
            long after = System.currentTimeMillis();
            System.out.println();
            System.out.println("===== Time : " + (after - before) + "   =====");
            System.out.println("===== Test Case " + i + " End   =====");
        }
    }

    private static class Solution {
        private final int INF = Integer.MAX_VALUE;
        private int testCase;
        private int city;
        private int road;
        private int wormhole;
        private ArrayList<ArrayList<Route>> cityList;

        private Solution() {
        }

        public static Solution create(){
            return new Solution();
        }

        public void solution(String input) throws IOException {
            ioBuffered = IOBuffered.create(input);
            setAnswers();
            printOutputData();
        }

        private void setAnswers() throws IOException {
            testCase = stoi(ioBuffered.readLine());
            answer = new boolean[testCase];
            for (int i = 0; i < testCase; i++) {
                answer[i] = getAnswer();
            }
        }

        private boolean getAnswer() throws IOException {
            setInputData();
            return searchAllCity();
        }

        private void setInputData() throws IOException {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            city = stoi(st.nextToken());
            road = stoi(st.nextToken());
            wormhole = stoi(st.nextToken());
            setCityList();
        }

        private int stoi(String s) {
            return Integer.parseInt(s);
        }

        private void setCityList() throws IOException {
            createCityList();
            setCity();
        }

        private void createCityList() {
            cityList = new ArrayList<>();
            for (int i = 0; i < city; i++) {
                cityList.add(new ArrayList<>());
            }
        }

        private void setCity() throws IOException {
            setRoad();
            setWormhole();
        }

        private void setRoad() throws IOException {
            for (int i = 0; i < road; i++) {
                StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
                int start = stoi(st.nextToken()) - 1;
                int end = stoi(st.nextToken()) - 1;
                int time = stoi(st.nextToken());
                cityList.get(start).add(Route.of(end, time));
                cityList.get(end).add(Route.of(start, time));
            }
        }

        private void setWormhole() throws IOException {
            for (int i = 0; i < wormhole; i++) {
                StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
                int start = stoi(st.nextToken()) - 1;
                int end = stoi(st.nextToken()) - 1;
                int time = reverseTime(st.nextToken());
                cityList.get(start).add(Route.of(end, time));
            }
        }

        private int reverseTime(String s) {
            return stoi(s) * -1;
        }

        private boolean searchAllCity() {
            for (int i = 0; i < city; i++) {
                if (bellmanFord(i)) return true;
            }

            return false;
        }

        /**
         * BellmanFord algorithm
         * reference : https://ratsgo.github.io/data%20structure&algorithm/2017/11/27/bellmanford/
         */
        private boolean bellmanFord(final int start) {
            int[] times = new int[city];
            Arrays.fill(times, INF);
            times[start] = 0;
            for (int i = 0; i < city - 1; i++) {
                if (!setShortestDist(times)) break;
            }

            return times[start] < 0;
        }

        private boolean setShortestDist(int[] times) {
            boolean ok = false;
            for (int j = 0; j < city; j++) {
                for (Route route : cityList.get(j)) {
                    int end = route.getEnd();
                    int time = route.getTime();
                    if (isVisit(times[j]) && isShortestDist(times[j] + time, times[end])) {
                        times[end] = times[j] + time;
                        ok = true;
                    }
                }
            }

            return ok;
        }

        private boolean isVisit(int time) {
            return time != INF;
        }

        private boolean isShortestDist(int compare, int target) {
            return target > compare;
        }

        private void printOutputData() throws IOException {
            ioBuffered.print(testCase, answer);
        }
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