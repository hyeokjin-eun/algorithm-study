package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1389
public class Backjun1389 {
    private static IOBuffered ioBuffered;
    private static int N;
    private static int M;
    private static ArrayList<ArrayList<Integer>> friend;
    private static int[] visit;
    private static Queue<Integer> queue;
    private static int answer;
    private static final String[] input = {
            "5 5\n" +
            "1 3\n" +
            "1 4\n" +
            "4 5\n" +
            "4 3\n" +
            "3 2"
    };

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < input.length; i++) {
            System.out.println("===== Test Case " + i + " Start =====");
            long before = System.currentTimeMillis();
            solution(input[i]);
            long after = System.currentTimeMillis();

            System.out.println();
            System.out.println("===== Time : " + (after - before) + "   =====");
            System.out.println("===== Test Case " + i + " End   =====");
        }
    }

    private static void solution(String input) throws IOException {
        ioBuffered = IOBuffered.create(input);
        setInputData();
        setMinFriend();
        outPrint();
    }

    private static void setInputData() throws IOException {
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        setFriendData();
    }

    private static void setFriendData() throws IOException {
        friend = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            friend.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            int from = stoi(st.nextToken()) - 1;
            int to = stoi(st.nextToken()) - 1;
            friend.get(from).add(to);
            friend.get(to).add(from);
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setMinFriend() {
        int preFriendCount = Integer.MAX_VALUE;
        answer = -1;
        for (int i = 0; i < N; i++) {
            int curFriendCount = bfs(i);
            if (curFriendCount < preFriendCount) {
                preFriendCount = curFriendCount;
                answer = i + 1;
            }
        }
    }

    private static int bfs(int target) {
        queueCreate(target);
        visitCreate(target);
        setFriendCount();
        return getFriendCount();
    }

    private static void queueCreate(int target) {
        queue = new LinkedList<>();
        queue.offer(target);
    }

    private static void visitCreate(int target) {
        visit = new int[N];
        Arrays.fill(visit, -1);
        visit[target] = 0;
    }

    private static void setFriendCount() {
        while (!queue.isEmpty()) {
            int current = queue.poll();
            int friendCount = visit[current];
            setNextFriend(current, friendCount);
        }
    }

    private static void setNextFriend(int current, int friendCount) {
        for (int i = 0; i < friend.get(current).size(); i++) {
            int next = friend.get(current).get(i);
            if (isVisit(next)) {
                continue;
            }

            queue.offer(next);
            visit[next] = friendCount + 1;
        }
    }

    private static boolean isVisit(int next) {
        return visit[next] != -1;
    }

    private static int getFriendCount() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            result += visit[i];
        }
        return result;
    }

    private static void outPrint() throws IOException {
        ioBuffered.print();
    }

    private static class IOBuffered {
        private final BufferedReader bufferedReader;
        private final BufferedWriter bufferedWriter;

        private IOBuffered(String input) {
            bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public static IOBuffered create(String input) {
            return new IOBuffered(input);
        }

        public String readLine() throws IOException {
            return this.bufferedReader.readLine();
        }

        public void print() throws IOException {
            write();
            flush();
        }

        private void write() throws IOException {
            bufferedWriter.write(String.valueOf(answer));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
