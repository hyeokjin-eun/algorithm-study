package com.company.programmers;

import java.io.*;
import java.util.*;

// link
public class program_bridge {
    private static final String[] array = {
            "2\n" +
            "10\n" +
            "4\n" +
            "7 4 5 6\n",
            "100\n" +
            "100\n" +
            "1\n" +
            "10\n",
            "100\n" +
            "100\n" +
            "10\n" +
            "10 10 10 10 10 10 10 10 10 10\n"
    };

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

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int bridge_length = stoi(br.readLine());
        int weight = stoi(br.readLine());
        int truck_size = stoi(br.readLine());
        int[] truck_weights = new int[truck_size];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < truck_size; i++) {
            truck_weights[i] = stoi(st.nextToken());
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) {
            queue.offer(-1);
        }

        Queue<Integer> wait_truck = new LinkedList<>();
        Queue<Integer> arrival_truck = new LinkedList<>();
        for (int i = 0; i < truck_weights.length; i++) {
            wait_truck.offer(truck_weights[i]);
            arrival_truck.offer(truck_weights[i]);
        }

        int answer = 0;
        int cur_weight = 0;
        while (!arrival_truck.isEmpty()) {
            answer++;
            int num = queue.poll();
            if (num == arrival_truck.peek()) {
                cur_weight -= arrival_truck.poll();
            }

            if (!wait_truck.isEmpty() && cur_weight + wait_truck.peek() <= weight) {
                cur_weight += wait_truck.peek();
                queue.offer(wait_truck.poll());
            } else {
                queue.offer(-1);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {

    }
}
