package ieee.IEEEE2018_01;

import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/12 0:58
 * @see ieee.IEEEE2018_01
 */
public class A3 {
    private final static int MOD = 10000007;

    private static int[][] multiMatrix(int a[][], int b[][]) {
        int c[][] = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    c[i][j] = (c[i][j] + a[i][j + k] * b[j + k][i]) % MOD;
                }
            }
        }
        return c;
    }

    private static int[][] powMatrix(int a[][], int k) {
        if (k == 1) {
            return a;
        }
        int r = k % 2;
        int c[][] = powMatrix(a, k / 2);
        c = multiMatrix(c, c);
        if (r == 1) {
            c = multiMatrix(a, c);
        }
        return c;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        int first = Integer.MIN_VALUE;
        int sec = Integer.MIN_VALUE;
        long sum = 0;
        while (n-- > 0) {
            int val = sc.nextInt();
            sum = (sum + val) % MOD;
            if (val >= sec) {
                first = sec;
                sec = val;
            } else if (val > first) {
                first = val;
            }
        }
        if (first == Integer.MIN_VALUE) {
            first = sec;
        }
        if (k > 0) {
            if (sec <= 0) {
                sum = (sum + (first + sec) * (long) k) % MOD;
            } else {
                while (k > 0 && first < 0) {
                    int j = sec + first;
                    first = Math.min(Math.max(j, first), sec);
                    sec = Math.max(j, sec);
                    sum = (sum + j) % MOD;
                    k--;
                }
                if (k == 1) {
                    sum = (sum + first + sec) % MOD;
                } else if (k > 1) {

                    int a[][] = new int[][] {{0, 1}, {1, 1}};
                    int c1[][] = powMatrix(a, k + 1);
                    int c2[][] = multiMatrix(a, c1);
                    sum = (sum + first * (c1[1][1] - 1) + sec * (c2[1][1] - 1)) % MOD;
                }
            }
        }
        System.out.print(sum);
    }
}
