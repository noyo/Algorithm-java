package format;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/15 22:02
 * @see format
 */
public class Cmn {

    /**
     * ps: n >= m, choose m from n;
     */
    private static int c(int n, int m) {
        int c[] = new int[n];
        c[0] = 1;
        int min = Math.min(m, n - m);
        for (int i = 1; i <= min; i++) {
            c[i] = c[i - 1] * (n - i + 1) / i;
        }
        return c[m];
    }

    public static void main(String args[]) {

        System.out.println(c(6, 2));
        System.out.println(c(4, 2));
        System.out.println(c(5, 2));

    }
}
