package format;

import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/5/31 21:07
 * @see PACKAGE_NAME
 */
public class KM {
    private static final int MAX_VAL = 1 << 9;
    private static int n, m;
    private static int W[][];
    private static int lx[], ly[];
    private static int left[];
    private static boolean S[], T[];
    private static Scanner sc;

    private static void init() {
        sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        W = new int[n + 1][n + 1];
        lx = new int[n + 1];
        ly = new int[n + 1];
        left = new int[n + 1];
        S = new boolean[n + 1];
        T = new boolean[n + 1];
        for (int i = 1; i <= m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            W[x][y] = Math.max(W[x][y], MAX_VAL - sc.nextInt());
        }
    }

    private static boolean match(int i) {
        S[i] = true;
        for (int j = 1; j <= n; j++) {
            if (lx[i] + ly[j] == W[i][j] && !T[j]) {
                T[j] = true;
                if (left[j] == 0 || match(left[j])) {
                    left[j] = i;
                    return true;
                }
            }
        }
        return false;
    }

    private static void update() {
        int a = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (S[i]) {
                for (int j = 1; j <= n; j++) {
                    if (!T[j]) {
                        a = Math.min(a, lx[i] + ly[j] - W[i][j]);
                    }
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            if (S[i]) {
                lx[i] -= a;
            }
            if (T[i]) {
                ly[i] += a;
            }
        }
    }

    private static void KM() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                lx[i] = Math.max(lx[i], W[i][j]);
            }
        }

        for (int i = 1; i <= n; i++) {
            for (; ; ) {
                for (int j = 1; j <= n; j++) {
                    S[j] = T[j] = false;
                }
                if (match(i)) {
                    break;
                } else {
                    update();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String args[]) {
        init();
        KM();
        for (int i = 1; i <= n; i++) {
            System.out.println(MAX_VAL - W[left[i]][i]);
        }
    }
}
