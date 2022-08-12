package com.company.string;

import java.io.*;

public class Backjun17478 {
    private static final String[] array = {
            "2",
            "4"
    };
    private static IOBuffered ioBuffered;
    private static int count;
    private static StringBuilder stringBuilder;

    private static final String STR0 = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
    private static final String STR1 = "\"재귀함수가 뭔가요?\"";
    private static final String STR2_1 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
    private static final String STR2_2 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
    private static final String STR2_3 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
    private static final String STR3 = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
    private static final String STR4 = "라고 답변하였지.";

    public static void main(String[] args) throws IOException {
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
        //TODO Algorithm Start
        ioBuffered = IOBuffered.create(input);
        setData();
        setAnswer();
        printAnswer();
    }

    private static void setData() throws IOException {
        count = Integer.parseInt(ioBuffered.readLine());
    }

    private static void setAnswer() {
        stringBuilder = new StringBuilder();
        append(STR0);
        recursion(0);
    }

    private static void recursion(int cnt) {
        if (cnt == count) {
            appendString(STR1, cnt);
            appendString(STR3, cnt);
            appendString(STR4, cnt);
            return;
        }

        appendString(STR1, cnt);
        appendString(STR2_1, cnt);
        appendString(STR2_2, cnt);
        appendString(STR2_3, cnt);
        recursion(cnt + 1);
        appendString(STR4, cnt);
    }

    private static void prefix(int cnt) {
        for (int i = 0; i < cnt; i++) {
            stringBuilder.append("____");
        }
    }

    private static void append(String s) {
        stringBuilder.append(s);
        stringBuilder.append("\n");
    }

    private static void appendString(String s, int cnt) {
        prefix(cnt);
        append(s);
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(stringBuilder.toString());
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

        /**
         * IOBuffered Create
         * @param input Input String
         * @return IOBuffered Instance
         */
        public static IOBuffered create(String input) throws IOException {
            return new IOBuffered(input);
        }

        /**
         * BufferedReader Read Line
         * @return BufferedReader.readLIne
         */
        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        /**
         * Console Print Out (BufferedWriter.write())
         */
        public void print(String s) throws IOException {
            write(s);
            flush();
        }

        private void write(String s) throws IOException {
            //TODO Answer Write Implement
            bufferedWriter.write(s);
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
