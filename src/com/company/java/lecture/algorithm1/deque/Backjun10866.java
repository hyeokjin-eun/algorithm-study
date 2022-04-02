package com.company.java.lecture.algorithm1.deque;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/10866
public class Backjun10866 {
    private static final String[] array = {
            "15\n" +
            "push_back 1\n" +
            "push_front 2\n" +
            "front\n" +
            "back\n" +
            "size\n" +
            "empty\n" +
            "pop_front\n" +
            "pop_back\n" +
            "pop_front\n" +
            "size\n" +
            "empty\n" +
            "pop_back\n" +
            "push_front 3\n" +
            "empty\n" +
            "front",
            "22\n" +
            "front\n" +
            "back\n" +
            "pop_front\n" +
            "pop_back\n" +
            "push_front 1\n" +
            "front\n" +
            "pop_back\n" +
            "push_back 2\n" +
            "back\n" +
            "pop_front\n" +
            "push_front 10\n" +
            "push_front 333\n" +
            "front\n" +
            "back\n" +
            "pop_back\n" +
            "pop_back\n" +
            "push_back 20\n" +
            "push_back 1234\n" +
            "front\n" +
            "back\n" +
            "pop_back\n" +
            "pop_back"
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int commandIndex = Integer.parseInt(br.readLine());
        Deque deque = new Deque();
        for (int i = 0; i < commandIndex; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if ("push_front".equals(command)) {
                deque.pushFront(Integer.parseInt(st.nextToken()));
            } else if ("push_back".equals(command)) {
                deque.pushBack(Integer.parseInt(st.nextToken()));
            } else if ("pop_front".equals(command)) {
                bw.write(String.valueOf(deque.popFront()));
                bw.write("\n");
            } else if ("pop_back".equals(command)) {
                bw.write(String.valueOf(deque.popBack()));
                bw.write("\n");
            } else if ("size".equals(command)) {
                bw.write(String.valueOf(deque.size()));
                bw.write("\n");
            } else if ("empty".equals(command)) {
                bw.write(String.valueOf(deque.empty()));
                bw.write("\n");
            } else if ("front".equals(command)) {
                bw.write(String.valueOf(deque.front()));
                bw.write("\n");
            } else {
                bw.write(String.valueOf(deque.back()));
                bw.write("\n");
            }
        }

        br.close();
        bw.flush();
    }

    public static class Deque {
        private final ArrayList<Integer> arrayList;

        public Deque() {
            arrayList = new ArrayList<>();
        }

        public void pushFront(Integer number) {
            arrayList.add(0, number);
        }

        public void pushBack(Integer number) {
            arrayList.add(number);
        }

        public int popFront() {
            if (arrayList.isEmpty()) {
                return -1;
            } else {
                int result = arrayList.get(0);
                arrayList.remove(0);
                return result;
            }
        }

        public int popBack() {
            if (arrayList.isEmpty()) {
                return -1;
            } else {
                int result = arrayList.get(arrayList.size() - 1);
                arrayList.remove(arrayList.size() -1);
                return result;
            }
        }

        public int size() {
            return arrayList.size();
        }

        public int empty() {
            return arrayList.isEmpty() ? 1 : 0;
        }

        public int front() {
            if (arrayList.isEmpty()) {
                return -1;
            } else {
                return arrayList.get(0);
            }
        }

        public int back() {
            if (arrayList.isEmpty()) {
                return -1;
            } else {
                return arrayList.get(arrayList.size() - 1);
            }
        }
    }
}
