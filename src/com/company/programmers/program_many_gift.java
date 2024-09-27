package com.company.programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// link
// https://school.programmers.co.kr/learn/courses/30/lessons/258712
public class program_many_gift {

    private Map<String, Integer> index;
    private int[][] score;
    private int[] cnt;

    public int solution(String[] friends, String[] gifts) {
        setIndex(friends);
        setScoreAndCnt(gifts);
        return getAnswer(friends);
    }

    private void setIndex(String[] friends) {
        index = new HashMap<>();
        for (int i = 0; i < friends.length; i++) {
            index.put(friends[i], i);
        }
    }

    private void setScoreAndCnt(String[] gifts) {
        score = new int[index.size()][index.size()];
        cnt = new int[index.size()];
        for (String gift : gifts) {
            String[] temp = gift.split(" ");
            String from = temp[0];
            String to = temp[1];
            cnt[index.get(from)]++;
            cnt[index.get(to)]--;
            score[index.get(from)][index.get(to)] += 1;
        }
    }

    private int getAnswer(String[] friends) {
        int[] answers = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                if (isSame(score[i][j], score[j][i])) {
                    if (isSendLargerCnt(cnt[i], cnt[j])) {
                        answers[i] += 1;
                    } else if (isReceiveLargerCnt(cnt[i], cnt[j])) {
                        answers[j] += 1;
                    }
                } else {
                    if (compare(score[i][j], score[j][i])) {
                        answers[i] += 1;
                    } else {
                        answers[j] += 1;
                    }
                }
            }
        }

        int answer = 0;
        for (int num : answers) {
            answer = Math.max(num, answer);
        }

        return answer;
    }

    private boolean isSame(int send, int receive) {
        return send == receive;
    }

    private boolean compare(int send, int receive) {
        return send > receive;
    }

    private boolean isSendLargerCnt(int sendCnt, int receiveCnt) {
        return sendCnt > receiveCnt;
    }

    private boolean isReceiveLargerCnt(int sendCnt, int receiveCnt) {
        return sendCnt < receiveCnt;
    }

    public static void main(String[] args) {
        List<TestCase> all = Arrays.asList(
                new TestCase(
                        Arrays.asList("muzi", "ryan", "frodo", "neo"),
                        Arrays.asList("muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi")
                ),
                new TestCase(
                        Arrays.asList("joy", "brad", "alessandro", "conan", "david"),
                        Arrays.asList("alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david")
                ),
                new TestCase(
                        Arrays.asList("a", "b", "c"),
                        Arrays.asList("a b", "b a", "c a", "a c", "a c", "c a")
                )
        );

        for (TestCase testCase : all) {
            program_many_gift problem = new program_many_gift();
            System.out.println(problem.solution(testCase.getFriends(), testCase.getGifts()));
        }
    }

    private static class TestCase {
        List<String> friends;
        List<String> gifts;

        public TestCase(List<String> friends, List<String> gifts) {
            this.friends = friends;
            this.gifts = gifts;
        }

        public String[] getFriends() {
            return friends.toArray(new String[0]);
        }

        public String[] getGifts() {
            return gifts.toArray(new String[0]);
        }
    }
}
