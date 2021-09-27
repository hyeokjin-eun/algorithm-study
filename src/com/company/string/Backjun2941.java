package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2941
public class Backjun2941 {
    private static final String[] croatia = {
            "c=",   "c-",   "dz=",  "d-",
            "lj",   "nj",   "s=",   "z="
    };

    private static final String[] array = {
            "ljes=njak",
            "ddz=z=",
            "nljj",
            "c=c=",
            "dz=ak"
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
        String s = br.readLine();
        int answer = croatia(s);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int croatia(String s) {
        int i = 0;
        int cnt = 0;
        while (i < s.length()) {
            boolean check = false;
            char cs = s.charAt(i);
            for (int j = 0; j < croatia.length; j++) {
                char sc = croatia[j].charAt(0);
                if (cs == sc) {
                    int cn = croatia[j].length() - 1;
                    for (int k = 1; k < croatia[j].length(); k++) {
                        if (i + k < s.length() && s.charAt(i + k) == croatia[j].charAt(k)) {
                            cn--;
                        }
                    }

                    if (cn == 0) {
                        i += croatia[j].length();
                        check = true;
                        break;
                    }
                }
            }

            if (!check) {
                i++;
            }

            cnt++;
        }

        return cnt;
    }
}
