package seven;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/4/24 10:32
 * @see seven
 */
public class Uva725_7_1 {
    static char[] ch;

    private static boolean isUnique(String s) {
        ch = s.toCharArray();
        Arrays.sort(ch);
        int l = ch.length;
        for (int i = 0; i < l; i++) {
            if (ch[i] - '0' != i) {
                return false;
            }
        }
        return true;
    }

    private static void print(int n) {
        int used[] = new int[10];
        int mul;
        for (int num = 1234; num < 98766; num++) {
            mul = num * n;
            if (mul < 98766 && isUnique(String.format("%5d%05d", mul, num))) {
                System.out.println(String.format("%5d / %05d", mul, num));
            }
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            long time = System.currentTimeMillis();
            if (1 != n) print(n);
            System.out.println(System.currentTimeMillis() - time);
        }
    }

}
