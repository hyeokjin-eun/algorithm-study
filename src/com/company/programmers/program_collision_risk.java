package com.company.programmers;

import java.util.Arrays;
import java.util.List;

// TODO : 추가 개발 필요
// link
// https://school.programmers.co.kr/learn/courses/30/lessons/340211?language=java
public class program_collision_risk {

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        return answer;
    }

    private int[] nextPoint(int[] cur, int[] end) {
        int[] next = new int[]{cur[0], cur[1]};
        if (cur[0] != end[0]) {
            if (cur[0] < end[0]) {
                next[0]++;
            } else {
                next[0]--;
            }
        } else {
            if (cur[1] < end[1]) {
                next[1]++;
            } else {
                next[1]--;
            }
        }

        return next;
    }

    private boolean isEndPoint(int[] cur, int[] end) {
        return cur[0] == end[0] && cur[1] == end[1];
    }

    public static void main(String[] args) {
        List<TestCase> all = Arrays.asList(
                new TestCase()
                        .setPoints(new int[]{3, 2}, new int[]{6, 4}, new int[]{4, 7}, new int[]{1, 4})
                        .setRoutes(new int[]{4, 2}, new int[]{1, 3}, new int[]{2, 4}),
                new TestCase()
                        .setPoints(new int[]{3, 2}, new int[]{6, 4}, new int[]{4, 7}, new int[]{1, 4})
                        .setRoutes(new int[]{4, 2}, new int[]{1, 3}, new int[]{4, 2}, new int[]{4, 3})
                new TestCase()
                        .setPoints(new int[]{2, 2}, new int[]{2, 3}, new int[]{2, 7}, new int[]{6, 6}, new int[]{5, 2})
                        .setRoutes(new int[]{2, 3, 4, 5}, new int[]{1, 3, 4, 5})
        );

        for (TestCase testCase : all) {
            program_collision_risk problem = new program_collision_risk();
            System.out.println(problem.solution(testCase.getPoints(), testCase.getRoutes()));
        }
    }

    private static class TestCase {
        int[][] points;
        int[][] routes;

        public TestCase() {
        }

        public TestCase setPoints(int[]... inputs) {
            points = new int[inputs.length][inputs[0].length];
            for (int i = 0; i < points.length; i++) {
                points[i] = Arrays.copyOf(inputs[i], inputs[i].length);
            }

            return this;
        }

        public TestCase setRoutes(int[]... inputs) {
            routes = new int[inputs.length][inputs[0].length];
            for (int i = 0; i < routes.length; i++) {
                routes[i] = Arrays.copyOf(inputs[i], inputs[i].length);
            }

            return this;
        }

        public int[][] getPoints() {
            return points;
        }

        public int[][] getRoutes() {
            return routes;
        }
    }
}
