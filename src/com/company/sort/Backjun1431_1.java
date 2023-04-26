package com.company.sort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Backjun1431_1 {
    private static final String[] array = {
            "5\n" +
            "ABCD\n" +
            "145C\n" +
            "A\n" +
            "A910\n" +
            "Z321",
            "2\n" +
            "Z19\n" +
            "Z20",
            "4\n" +
            "34H2BJS6N\n" +
            "PIM12MD7RCOLWW09\n" +
            "PYF1J14TF\n" +
            "FIPJOTEA5",
            "5\n" +
            "ABCDE\n" +
            "BCDEF\n" +
            "ABCDA\n" +
            "BAAAA\n" +
            "ACAAA"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static List<Temp> temp;
    private static List<String> answer;

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
        ioBuffered = IOBuffered.create(input);
        setData();
        setAnswer();
        printAnswer();
    }

    private static void setData() throws IOException {
        N = stoi(ioBuffered.readLine());
        temp = new ArrayList<>();
        answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String s = ioBuffered.readLine();
            int length = s.length();
            int sum = 0;
            for (int j = 0; j < s.length(); j++) {
                if ('0' <= s.charAt(j) && s.charAt(j) <= '9') {
                    sum += s.charAt(j) - '0';
                }
            }

            temp.add(new Temp(length, sum, s));
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = temp.stream().sorted().map(Temp::getTemp).collect(Collectors.toList());
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }
}

class IOBuffered {
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;

    private IOBuffered(String input) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
        bufferedReader = new BufferedReader(inputStreamReader);
        bufferedWriter = new BufferedWriter(outputStreamWriter);
    }

    public static IOBuffered create(String input) throws IOException {
        return new IOBuffered(input);
    }

    public String readLine() throws IOException {
        return bufferedReader.readLine();
    }

    public void print(List<String> answer) throws IOException {
        write(answer);
        flush();
    }

    private void write(List<String> answer) throws IOException {
        for (int i = 0; i < answer.size(); i++) {
            bufferedWriter.write(answer.get(i));
            if (i != answer.size() - 1) {
                bufferedWriter.write("\n");
            }
        }
    }

    private void flush() throws IOException {
        bufferedWriter.flush();
    }
}

class Temp implements Comparable<Temp> {
    private final int length;
    private final int sum;
    private final String temp;

    public Temp(int length, int sum, String temp) {
        this.length = length;
        this.sum = sum;
        this.temp = temp;
    }

    public static Temp create(int length, int sum, String temp) {
        return new Temp(length, sum, temp);
    }

    public int getLength() {
        return length;
    }

    public int getSum() {
        return sum;
    }

    public String getTemp() {
        return temp;
    }

    @Override
    public int compareTo(Temp o) {
        if (this.length == o.length)
            return this.sum == o.sum ? this.temp.compareTo(o.temp) : this.sum - o.sum;
        return this.length - o.length;
    }
}
