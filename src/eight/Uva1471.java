package eight;

import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/3 15:38
 * @see eight
 */
public class Uva1471 {

    private static int defense(int a[], int n) {

        int max = 0;
        int tail[] = new int[n];
        tail[0] = 1;
        for (int i = 1; i < n; i++) {
            if (a[i] > a[i - 1]) {
                tail[i] = tail[i - 1] + 1;
            } else {
                tail[i] = 1;
            }
        }
        int head[] = new int[n];
        head[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (a[i] < a[i + 1]) {
                head[i] = head[i + 1] + 1;
            } else {
                head[i] = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            max = Math.max(max, tail[i]);
            for (int j = i + 2; j < n; j++) {
                if (a[j] <= a[i]) {
                    continue;
                }
                max = Math.max(max, tail[i] + head[j]);
            }
        }

        return max;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        while (m-- > 0) {
            int n = sc.nextInt();
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            System.out.println(defense(a, n));
        }
    }

}
