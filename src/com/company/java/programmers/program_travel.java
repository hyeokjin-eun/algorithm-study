package com.company.java.programmers;

import java.io.*;
import java.util.*;

// link
// https://programmers.co.kr/learn/courses/30/lessons/43164
// TODO: 2021-10-08 재 검토
public class program_travel {
    private static final String[] array = {
            "3\n" +
            "ICN JFK\n" +
            "HND IAD\n" +
            "JFK HND",
            "5\n" +
            "ICN SFO \n" +
            "ICN ATL\n" +
            "SFO ATL\n" +
            "ATL ICN\n" +
            "ATL SFO",
            "8\n" +
            "ICN BOO\n" +
            "ICN COO\n" +
            "COO DOO\n" +
            "DOO COO\n" +
            "BOO DOO\n" +
            "DOO BOO\n" +
            "BOO ICN\n" +
            "COO BOO"
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
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int n = Integer.parseInt(br.readLine());
        String[][] tickets = new String[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            tickets[i][0] = st.nextToken();
            tickets[i][1] = st.nextToken();
        }

        Arrays.sort(tickets, (o1, o2) -> {
            if (o1[0].equals(o2[0])) {
                return o1[1].compareTo(o2[1]);
            }

            return 0;
        });
        String[] answer = bfs(tickets);
        System.out.println();
    }

    private static String[] bfs2(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
        HashMap<String, ArrayList<String>> a = new HashMap<>();
        HashMap<String, ArrayList<Boolean>> check = new HashMap<>();
        LinkedHashSet<String> set = new LinkedHashSet<>();
        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0; j < 2; j++) {
                set.add(tickets[i][j]);
            }
        }

        set.forEach(s -> {
            a.put(s, new ArrayList<>());
            check.put(s, new ArrayList<>());
        });

        for (int i = 0; i < tickets.length; i++) {
            a.get(tickets[i][0]).add(tickets[i][1]);
            a.get(tickets[i][0]).sort(Comparator.naturalOrder());
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(a.get("ICN").get(0));
        answer[0] = "ICN";
        a.get("ICN").remove(0);
        int index = 1;
        while (!queue.isEmpty()) {
            String to = queue.poll();
            answer[index] = to;
            if (0 < a.get(to).size()) {
                a.get(to).sort((o1, o2) -> Integer.compare(a.get(o1).size(), a.get(o2).size()));
                queue.offer(a.get(to).get(0));
                a.get(to).remove(0);
                index++;
            }
        }

        return answer;
    }

    private static String[] bfs(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
        Queue<String> from = new LinkedList<>();
        Queue<String> to = new LinkedList<>();
        boolean[] check = new boolean[tickets.length];
        from.offer(tickets[0][0]);
        to.offer(tickets[0][1]);
        check[0] = true;
        int index = 1;
        answer[0] = tickets[0][0];
        while (!from.isEmpty() && !to.isEmpty()) {
            String f = from.poll();
            String t = to.poll();
            answer[index] = t;
            for (int i = 0; i < tickets.length; i++) {
                if (!check[i] && tickets[i][0].equals(t)) {
                    check[i] = true;
                    from.offer(tickets[i][0]);
                    to.offer(tickets[i][1]);
                    index++;
                    break;
                }
            }
        }

        return answer;
    }
}
