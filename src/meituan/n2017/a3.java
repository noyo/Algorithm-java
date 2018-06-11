package meituan.n2017;

import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/6 15:43
 * @see meituan.n2017
 */
public class a3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while (T-- > 0) {
            char a[] = sc.next().toCharArray();
            char b[] = sc.next().toCharArray();
            int la, lb, ra, rb;
            int len = a.length + b.length;
            int max = 1;

            for (int i = len; i > 1; i++) {
                la = 0;
                lb = 0;
                ra = a.length - 1;
                rb = b.length - 1;
                int l = i;
                while (ra - la + 1 + rb - lb + 1 >= l) {

                }
            }
            System.out.println(max);
        }
    }
}
