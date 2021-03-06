package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1916
public class Backjun1916 {
    private static final int INF = Integer.MAX_VALUE;
    private static IOBuffered ioBuffered;
    private static int city;
    private static int bus;
    private static ArrayList<Route>[] cityList;
    private static int startCity;
    private static int endCity;
    private static int answer;
    private static final String[] array = {
            "5\n" +
            "8\n" +
            "1 2 2\n" +
            "1 3 3\n" +
            "1 4 1\n" +
            "1 5 10\n" +
            "2 4 2\n" +
            "3 4 1\n" +
            "3 5 1\n" +
            "4 5 3\n" +
            "1 5"
    };

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
        dijkstra();
        printOutputData();
    }

    private static void setInputData() throws IOException {
        city = stoi(ioBuffered.readLine());
        bus = stoi(ioBuffered.readLine());
        setBusList();
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        startCity = stoi(st.nextToken()) - 1;
        endCity = stoi(st.nextToken()) - 1;
    }

    private static void setBusList() throws IOException {
        createCityList();
        setBusInCity();
    }

    private static void createCityList() {
        cityList = new ArrayList[city];
        for (int i = 0; i < city; i++) {
            cityList[i] = new ArrayList<>();
        }
    }

    private static void setBusInCity() throws IOException {
        for (int i = 0; i < bus; i++) {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            int start = stoi(st.nextToken()) - 1;
            int end = stoi(st.nextToken()) - 1;
            int price = stoi(st.nextToken());
            cityList[start].add(Route.of(end, price));
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    /**
     * Dijkstra's Algorithm
     * reference : https://ratsgo.github.io/data%20structure&algorithm/2017/11/26/dijkstra/
     */
    private static void dijkstra() {
        PriorityQueue<Route> queue = new PriorityQueue<>();
        queue.offer(Route.of(startCity, 0));
        int[] dist = new int[city];
        Arrays.fill(dist, INF);
        dist[startCity] = 0;
        while (!queue.isEmpty()) {
            Route cRoute = queue.poll();
            int cEnd = cRoute.getEnd();
            int cPrice = cRoute.getPrice();
            if (dist[cEnd] < cPrice) {
                continue;
            }

            for (int i = 0; i < cityList[cEnd].size(); i++) {
                Route nRoute = cityList[cEnd].get(i);
                int nEnd = nRoute.getEnd();
                int nPrice = dist[cEnd] + nRoute.getPrice();
                if (nPrice < dist[nEnd]) {
                    dist[nEnd] = nPrice;
                    queue.offer(Route.of(nEnd, nPrice));
                }
            }
        }

        answer = dist[endCity];
    }

    private static void printOutputData() throws IOException {
        ioBuffered.print(answer);
    }

    private static class Route implements Comparable<Route> {
        private final int end;
        private final int price;

        private Route(final int end, final int price) {
            this.end = end;
            this.price = price;
        }

        public static Route of(final int end, final int price) {
            return new Route(end, price);
        }

        public int getEnd() {
            return this.end;
        }

        public int getPrice() {
            return this.price;
        }

        @Override
        public int compareTo(Route o) {
            if (o == this) {
                return 0;
            }

            return Integer.compare(this.getPrice(), o.getPrice());
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