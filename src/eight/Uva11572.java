package eight;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/5/26 0:20
 * @see eight
 */
public class Uva11572 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- > 0) {
            int m = sc.nextInt();
            int a[] = new int[m];
            for (int i = 0; i < m; i++) {
                a[i] = sc.nextInt();
            }
            sc.nextLine();
            int max = 0;
            int pre = 0;
            Set<Integer> set = new HashSet<>(1 << 20);
            for (int i = 0; i < m; i++) {
                if (!set.contains(a[i])) {
                    set.add(a[i]);
                    max = Math.max(max, set.size());
                } else {
                    while (a[pre] != a[i]) {
                        set.remove(a[pre]);
                        pre++;
                    }
                    pre++;
                }
            }
            System.out.println(max);
        }
    }
}
