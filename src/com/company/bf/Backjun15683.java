package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/15683
public class Backjun15683 {
    private static final String[] array = {
            "4 6\n" +
            "0 0 0 0 0 0\n" +
            "0 0 0 0 0 0\n" +
            "0 0 1 0 6 0\n" +
            "0 0 0 0 0 0",
            "6 6\n" +
            "0 0 0 0 0 0\n" +
            "0 2 0 0 0 0\n" +
            "0 0 0 0 6 0\n" +
            "0 6 0 0 2 0\n" +
            "0 0 0 0 0 0\n" +
            "0 0 0 0 0 5",
            "6 6\n" +
            "1 0 0 0 0 0\n" +
            "0 1 0 0 0 0\n" +
            "0 0 1 0 0 0\n" +
            "0 0 0 1 0 0\n" +
            "0 0 0 0 1 0\n" +
            "0 0 0 0 0 1",
            "6 6\n" +
            "1 0 0 0 0 0\n" +
            "0 1 0 0 0 0\n" +
            "0 0 1 5 0 0\n" +
            "0 0 5 1 0 0\n" +
            "0 0 0 0 1 0\n" +
            "0 0 0 0 0 1",
            "1 7\n" +
            "0 1 2 3 4 5 6",
            "3 7\n" +
            "4 0 0 0 0 0 0\n" +
            "0 0 0 2 0 0 0\n" +
            "0 0 0 0 0 0 4",
            "2 2\n" +
            "0 0\n" +
            "0 0"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] check = new int[N][M];
        ArrayList<Pair> cctv = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                check[i][j] = num;
                if (num == 6) {
                    check[i][j] = -2;
                }

                if (num != 0 && num != 6) {
                    cctv.add(new Pair(j, i, num));
                }
            }
        }

        int answer = recursion(0, cctv.size(), check, N, M, cctv);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int recursion(int index, int max, int[][] check, int N, int M, ArrayList<Pair> cctv) {
        if (max == index) {
            int temp = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (check[i][j] == 0) {
                        temp++;
                    }
                }
            }

            return temp;
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            check(check, cctv.get(index).x, cctv.get(index).y, cctv.get(index).t, i, index + 1, N, M, true);
            answer = Math.min(recursion(index + 1, max, check, N, M, cctv), answer);
            check(check, cctv.get(index).x, cctv.get(index).y, cctv.get(index).t, i, index + 1, N, M, false);
        }

        return answer;
    }

    private static void check(int[][] check, int x, int y, int type, int turn, int index, int N, int M, boolean is) {
        check[y][x] = -1;
        if (type == 1) {
            if (turn == 0) {
                xup(check, x, y, index, is, M);
            } else if (turn == 1) {
                xdown(check, x, y, index, is);
            } else if (turn == 2) {
                yup(check, x, y, index, is, N);
            } else if (turn == 3) {
                ydown(check, x, y, index, is);
            }
        } else if (type == 2) {
            if (turn % 2 == 0) {
                xup(check, x, y, index, is, M);
                xdown(check, x, y, index, is);
            } else {
                yup(check, x, y, index, is, N);
                ydown(check, x, y, index, is);
            }
        } else if (type == 3) {
            if (turn == 0) {
                ydown(check, x, y, index, is);
                xup(check, x, y, index, is, M);
            } else if (turn == 1) {
                xup(check, x, y, index, is, M);
                yup(check, x, y, index, is, N);
            } else if (turn == 2) {
                yup(check, x, y, index, is, N);
                xdown(check, x, y, index, is);
            } else if (turn == 3) {
                xdown(check, x, y, index, is);
                ydown(check, x, y, index, is);
            }
        } else if (type == 4) {
            if (turn == 0) {
                ydown(check, x, y, index, is);
                xup(check, x, y, index, is, M);
                xdown(check, x, y, index, is);
            } else if (turn == 1) {
                ydown(check, x, y, index, is);
                xup(check, x, y, index, is, M);
                yup(check, x, y, index, is, N);
            } else if (turn == 2) {
                yup(check, x, y, index, is, N);
                xup(check, x, y, index, is, M);
                xdown(check, x, y, index, is);
            } else if (turn == 3) {
                ydown(check, x, y, index, is);
                xdown(check, x, y, index, is);
                yup(check, x, y, index, is, N);
            }
        } else if (type == 5) {
            xup(check, x, y, index, is, M);
            xdown(check, x, y, index, is);
            yup(check, x, y, index, is, N);
            ydown(check, x, y, index, is);
        }
    }

    private static void xup(int[][] check, int x, int y, int index, boolean is, int M) {
        if (is) {
            for (int i = x + 1; i < M; i++) {
                if (check[y][i] == -2) {
                    break;
                }

                if (check[y][i] == 0) {
                    check[y][i] = index;
                }
            }
        } else {
            for (int i = x + 1; i < M; i++) {
                if (check[y][i] == -2) {
                    break;
                }

                if (check[y][i] == index) {
                    check[y][i] = 0;
                }
            }
        }
    }

    private static void xdown(int[][] check, int x, int y, int index, boolean is) {
        if (is) {
            for (int i = x - 1; 0 <= i; i--) {
                if (check[y][i] == -2) {
                    break;
                }

                if (check[y][i] == 0) {
                    check[y][i] = index;
                }
            }
        } else {
            for (int i = x - 1; 0 <= i; i--) {
                if (check[y][i] == -2) {
                    break;
                }

                if (check[y][i] == index) {
                    check[y][i] = 0;
                }
            }
        }
    }

    private static void yup(int[][] check, int x, int y, int index, boolean is, int N) {
        if (is) {
            for (int i = y + 1; i < N; i++) {
                if (check[i][x] == -2) {
                    break;
                }

                if (check[i][x] == 0) {
                    check[i][x] = index;
                }
            }
        } else {
            for (int i = y + 1; i < N; i++) {
                if (check[i][x] == -2) {
                    break;
                }

                if (check[i][x] == index) {
                    check[i][x] = 0;
                }
            }
        }
    }

    private static void ydown(int[][] check, int x, int y, int index, boolean is) {
        if (is) {
            for (int i = y - 1; 0 <= i; i--) {
                if (check[i][x] == -2) {
                    break;
                }

                if (check[i][x] == 0) {
                    check[i][x] = index;
                }
            }
        } else {
            for (int i = y - 1; 0 <= i; i--) {
                if (check[i][x] == -2) {
                    break;
                }

                if (check[i][x] == index) {
                    check[i][x] = 0;
                }
            }
        }
    }

    private static class Pair {
        int x;
        int y;
        int t;
        public Pair(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
}