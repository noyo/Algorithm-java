package meituan.n2018;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/9 18:55
 * @see meituan.n2018
 */
public class f {

    private static void helper(double a, double b, String S) {
        Map<Integer, Integer> map = new HashMap<>((int)b);
        StringBuilder sb = new StringBuilder();
        if (a < b) {
            a *= 10;
        }

        int cur = 0;
        int result = 1;
        boolean flag = false;
        int x = 0;
        while (true) {
            int tmp = (int) (a / b);
            x = (int) (a % b);
            if (x == 0) break;
            if (map.containsKey(x)) {
                flag = true;
                break;
            }

            map.put(x, sb.length());
            sb.append(tmp);
            a = x * 10;

            if (tmp == S.charAt(cur) - '0') {
                cur++;
            } else {
                result = sb.length() + 1;
                cur = 0;
            }
            if (cur == S.length()) {
                break;
            }
        }
        if (flag) {
            int start = map.get(x);
            while (cur < S.length() && start < sb.length()) {
                if (S.charAt(cur) != sb.charAt(start)) {
                    System.out.println(-1);
                    return;
                }
                cur++;
                start = (start + 1) % sb.length();
            }
        } else {
            while (cur < S.length()) {
                if (S.charAt(cur) != '0') {
                    System.out.println(-1);
                    return;
                }
                cur++;
            }
        }
        System.out.println(result);
    }


    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        double a = sc.nextDouble();
        double b = sc.nextDouble();

        helper(a, b, sc.next());
    }
}
