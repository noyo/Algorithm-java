package meituan.n2018.Bturn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/23 11:55
 * @see meituan.n2018.Bturn
 */
public class C {

    private static int f(int cnt) {
        return ((cnt + 1) * cnt) / 2 - 1;
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        if (s.length() == 1) {
            System.out.println(1);
            return;
        }

        int cnt = 0;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                cnt++;
                if (cnt == 2) {
                    index = i;
                }
            }
        }
        if (index == 0) {
            index = s.length();
        }

        System.out.println(f(s.length())
                - (cnt + index - 1 == s.length() ? index - 1 : index));
    }
}
