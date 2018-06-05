package eight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: eight
 * author: Chris
 * date: 2018/6/5 10:25
 */
public class Uva1608 {

    private static boolean isNonBoring(int[] pre, int next[], int left, int right) {
        if (left >= right) {
            return true;
        }
        for(int d = 0; left+d <= right-d; d++) {
            if(pre[left + d] < left && next[left + d] > right) {
                return isNonBoring(pre, next, left, left + d - 1) && isNonBoring(pre, next, left + d + 1, right);
            }
            if(left+d == right-d) {
                break;
            }
            if(pre[right - d] < left && next[right - d] > right) {
                return isNonBoring(pre, next, left, right - d - 1) && isNonBoring(pre, next, right - d + 1, right);
            }
        }

        return false;
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            sc.nextLine();
            int n = sc.nextInt();
            long a[] = new long[n + 1];
            int pre[] = new int[n + 1];
            int next[] = new int[n + 1];
            Map<Long, Integer> cur = new HashMap<>();

            for (int i = 1; i <= n; i++) {
                a[i] = sc.nextLong();
            }
            for (int i = 1; i <= n; i++) {
                pre[i] = cur.getOrDefault(a[i], 0);
                cur.put(a[i], i);
            }
            cur.clear();
            for (int i = n; i > 0; i--) {
                next[i] = cur.getOrDefault(a[i], n + 1);
                cur.put(a[i], i);
            }
            if (!isNonBoring(pre, next, 1, n)) {
                System.out.println("boring");
            } else {
                System.out.println("non-boring");
            }
        }
    }
}
