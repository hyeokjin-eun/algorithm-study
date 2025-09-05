package com.company.example;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Backjun1991 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cur =  convertInt(st.nextToken());
            map[cur][0] = convertInt(st.nextToken());
            map[cur][1] = convertInt(st.nextToken());
        }

        ArrayList<Integer> preorder = new ArrayList<>();
        preorderSearch(preorder, map, 0);
        print(preorder, n , bw);
        bw.write("\n");

        ArrayList<Integer> inorder = new ArrayList<>();
        inorderSearch(inorder, map, 0);
        print(inorder, n , bw);
        bw.write("\n");

        ArrayList<Integer> postorder = new ArrayList<>();
        postorderSearch(postorder, map, 0);
        print(postorder, n , bw);
        bw.write("\n");

        bw.flush();
    }

    private static int convertInt(String s) {
        if (s.charAt(0) == '.') {
            return -1;
        }

        return s.charAt(0) - 'A';
    }

    private static void preorderSearch(ArrayList<Integer> ans, int[][] map, int cur) {
        if (cur == -1) return;
        ans.add(cur);
        preorderSearch(ans, map, map[cur][0]);
        preorderSearch(ans, map, map[cur][1]);
    }

    private static void inorderSearch(ArrayList<Integer> ans, int[][] map, int cur) {
        if (cur == -1) return;
        inorderSearch(ans, map, map[cur][0]);
        ans.add(cur);
        inorderSearch(ans, map, map[cur][1]);
    }

    private static void postorderSearch(ArrayList<Integer> ans, int[][] map, int cur) {
        if (cur == -1) return;
        postorderSearch(ans, map, map[cur][0]);
        postorderSearch(ans, map, map[cur][1]);
        ans.add(cur);
    }

    private static void print(ArrayList<Integer> answer, int n, BufferedWriter bw) throws IOException {
        for (int i = 0; i < n; i++) {
            bw.write(answer.get(i) + 'A');
        }
    }
}
