package eight;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * TODO: 存在很大优化空间
 *
 * @author Chris
 * 2018/6/4 16:59
 * @see eight
 */
public class Uva714 {
    private static int divide(long M, int p[], int k, int m) {
        int val = 0;
        int max = -1;
        int i = m - 1;
        while (i >= 0) {
            if (i + 1 >= k && val + p[i] <= M) {
                val += p[i];
            } else {
                max = Math.max(max, val);
                val = p[i];
                k--;
                if (k == 0) {
                    return -1;
                }
            }
            i--;
        }
        max = Math.max(max, val);
        if (max > M) return -1;

        if (k == 1 && max == M) {
            return 0;
        }
        return 1;
    }

    public static void main(String args[]) throws FileNotFoundException {

//        System.setIn(new FileInputStream("src/in.txt"));
//        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- > 0) {
            int m = sc.nextInt();
            int k = sc.nextInt();
            if (k == 1) {
                System.out.println(sc.next());
                continue;
            }
            long M = 0;
            int p[] = new int[m];
            for (int i = 0; i < m; i++) {
                p[i] = sc.nextInt();
                M += p[i];
            }
            long low = 1;
            long high = M;
            long min = 0;
            while (low < high) {
                long mid = low + (high - low) / 2;
                int div = divide(mid, p, k, m);
                if (div == 0) {
                    min = mid;
                    high = mid;
                } else if (div < 0) {
                    low = mid;
                } else {
                    high = mid;
                }
                if (low == high - 1) break;
            }
            int val = 0;
            StringBuilder result = new StringBuilder();
            for (int i = m - 1; i >= 0; i--) {
                if (i + 1 >= k && val + p[i] <= min) {
                    val += p[i];
                    result.insert(0, i > 0 ? " " + p[i] : p[i]);
                } else {
                    result.insert(0, (i > 0 ? " " : "" ) + p[i] + " /");
                    val = p[i];
                    k--;
                }
            }
            if (result.charAt(result.length() - 1) == '/') {
                result.delete(result.length() - 2, result.length());
            }
            System.out.println(result);
        }
    }
}
