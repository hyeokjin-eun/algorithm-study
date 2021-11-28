package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/5635
public class Backjun5635 {
    private static final String[] array = {
            "5\n" +
            "Mickey 1 10 1991\n" +
            "Alice 30 12 1990\n" +
            "Tom 15 8 1993\n" +
            "Jerry 18 9 1990\n" +
            "Garfield 20 9 1990"
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
        int T = stoi(br.readLine());
        Student[] students = new Student[T];
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int day = stoi(st.nextToken());
            int month = stoi(st.nextToken());
            int year = stoi(st.nextToken());
            students[t] = new Student(name, year, month, day);
        }

        Arrays.sort(students, Student::compareTo);
        bw.write(students[students.length - 1].name);
        bw.write("\n");
        bw.write(students[0].name);
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static class Student implements Comparable<Student> {
        String name;
        Birthday birthday;

        public Student(String name, int year, int month, int day) {
            this.name = name;
            this.birthday = new Birthday(year, month, day);
        }

        private int getYear() {
            return birthday.getYear();
        }

        private int getMonth() {
            return birthday.getMonth();
        }

        private int getDay() {
            return birthday.getDay();
        }

        @Override
        public int compareTo(Student o) {
            if (this.getYear() == o.getYear()) {
                if (this.getMonth() == o.getMonth()) {
                    return this.getDay() - o.getDay();
                }

                return this.getMonth() - o.getMonth();
            }

            return this.getYear() - o.getYear();
        }
    }

    private static class Birthday {
        int year;
        int month;
        int day;
        public Birthday(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        private int getYear() {
            return this.year;
        }

        private int getMonth() {
            return this.month;
        }

        private int getDay() {
            return this.day;
        }
    }

    private static void test() {
        Student student = new Student("test", 2000,12,25);
        assert (student.name.equals("test"));
        assert (student.getYear() == 2000);
        assert (student.getMonth() == 12);
        assert (student.getDay() == 25);
    }
}