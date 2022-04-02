package com.company.java.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2251
public class Backjun2251 {
    private static int A;
    private static int B;
    private static int C;
    private static boolean[][][] check;
    private static Queue<Integer> queue;
    private static HashSet<Integer> set;
    private static final String[] array = {
            "8 9 10",
            "1 2 2"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = stoi(st.nextToken());
        B = stoi(st.nextToken());
        C = stoi(st.nextToken());
        bfs();

        ArrayList<Integer> answer = new ArrayList<>(set);
        Collections.sort(answer);
        for (int i = 0; i < answer.size(); i++) {
            bw.write(String.valueOf(answer.get(i)));
            if (i != answer.size() - 1) {
                bw.write(" ");
            }
        }

        bw.flush();
    }

    private static int[] from = new int[]{0, 0, 1, 1, 2, 2};
    private static int[] to = new int[]{1, 2, 0, 2, 0, 1};
    private static void bfs() {
        set = new HashSet<>();
        check = new boolean[A + 1][B + 1][C + 1];
        queue = new LinkedList<>();
        queue.offer(0);
        queue.offer(0);
        queue.offer(C);
        set.add(C);
        check[0][0][C] = true;
        while (!queue.isEmpty()) {
            int a = queue.poll();
            int b = queue.poll();
            int c = queue.poll();
            if (a == 0) {
                set.add(c);
            }

            for (int i = 0; i < 6; i++) {
                int f = from[i];
                int t = to[i];
                int na = a;
                int nb = b;
                int nc = c;
                int r = t == 0 ? A - na : t == 1 ? B - nb : C - nc;
                if ((f == 0 && na == 0) || (f == 1 && nb == 0) || (f == 2 && nc == 0)) {
                    continue;
                }

                if (f == 0) {
                    if (t == 1) {
                        if (r < na) {
                            na -= r;
                            nb += r;
                        } else {
                            nb += na;
                            na = 0;
                        }
                    } else if (t == 2) {
                        if (r < na) {
                            na -= r;
                            nc += r;
                        } else {
                            nc += na;
                            na = 0;
                        }
                    }
                }

                if (f == 1) {
                    if (t == 0) {
                        if (r < nb) {
                            nb -= r;
                            na += r;
                        } else {
                            na += nb;
                            nb = 0;
                        }
                    } else if (t == 2) {
                        if (r < nb) {
                            nb -= r;
                            nc += r;
                        } else {
                            nc += nb;
                            nb = 0;
                        }
                    }
                }

                if (f == 2) {
                    if (t == 0) {
                        if (r < nc) {
                            nc -= r;
                            na += r;
                        } else {
                            na += nc;
                            nc = 0;
                        }
                    } else if (t == 1) {
                        if (r < nc) {
                            nc -= r;
                            nb += r;
                        } else {
                            nb += nc;
                            nc = 0;
                        }
                    }
                }

                if (!check[na][nb][nc]) {
                    check[na][nb][nc] = true;
                    queue.offer(na);
                    queue.offer(nb);
                    queue.offer(nc);
                }
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {
        // stoi() Test
        String str = "1";
        assert (stoi(str) == 1) : "stoi() Function Integer Parser Fail";
    }
}