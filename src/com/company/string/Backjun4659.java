package com.company.string;

import java.io.*;

// linik
// https://www.acmicpc.net/problem/4659
public class Backjun4659 {
    private static final char[] sa = new char[]{'a', 'e', 'i', 'o', 'u'};
    private static final String[] array = {
            "a\n" +
            "tv\n" +
            "ptoui\n" +
            "bontres\n" +
            "zoggax\n" +
            "wiinq\n" +
            "eep\n" +
            "houctuh\n" +
            "end"
    };

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
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        while (true) {
            String str = br.readLine();
            if (str.equals("end")) {
                break;
            }

            char last = ' ';
            boolean ok = contains(str);
            int ja = 0;
            int mo = 0;
            for (int i = 0; i < str.length(); i++) {
                boolean vowel = vowel(str.charAt(i));
                if (vowel) {
                    ja = 0;
                    mo++;
                } else {
                    mo = 0;
                    ja++;
                }

                if (last != 'e' && last != 'o' && last == str.charAt(i)) {
                    ok = false;
                    break;
                }

                if (ja == 3 || mo == 3) {
                    ok = false;
                    break;
                }

                last = str.charAt(i);
            }

            StringBuilder sb = new StringBuilder();
            sb.append("<").append(str).append(">").append(" is");
            if (!ok) {
                sb.append(" not");
            }

            sb.append(" acceptable.");
            bw.write(sb.toString());
            bw.write("\n");
        }

        bw.flush();
    }

    private static boolean contains(String s) {
        for (char ch : s.toCharArray()) {
            if (vowel(ch)) {
                return true;
            }
        }

        return false;
    }

    private static boolean vowel(char s) {
        for (char value : sa) {
            if (s == value) {
                return true;
            }
        }

        return false;
    }
}