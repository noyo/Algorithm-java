package meituan;

import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/5/26 18:16
 * @see meituan
 */
public class f2 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int l = n - m;
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < k; i++) {
            int val = sc.nextInt() * m + sc.nextInt() * l;
            if (val >= max) {
                max = val;
                index = i;
            }
        }
        for (int i = 0; i < k; i++) {
            if (i == index) {
                System.out.print(n);
            } else {
                System.out.print(0);
            }
            if (i != k - 1) {
                System.out.print(" ");
            }
        }
    }
}
