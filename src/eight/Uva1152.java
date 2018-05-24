package eight;

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
 * date: 2018/5/24 16:20
 */
public class Uva1152 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int size = Integer.parseInt(sc.nextLine().trim());
        while (size-- > 0) {
            sc.nextLine();
            int n = Integer.parseInt(sc.nextLine().trim());
            int a[] = new int[n];
            int b[] = new int[n];
            int c[] = new int[n];
            int d[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
                b[i] = sc.nextInt();
                c[i] = sc.nextInt();
                d[i] = sc.nextInt();
                sc.nextLine();
            }
            Map<Integer, Integer> map = new HashMap<>(n * n);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int key = a[i] + b[j];
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
            }

            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sum += map.getOrDefault(-(c[i] + d[j]), 0);
                }
            }
            System.out.println(sum);
            if (size > 0) System.out.println();
        }

    }
}
