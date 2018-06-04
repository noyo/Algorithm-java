package eight;

import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/5/25 10:12
 * @see eight
 */
public class Uva11054 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = Integer.parseInt(sc.nextLine());
            if (n == 0) {
                break;
            }
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            sc.nextLine();

            int nextPos = 1;
            int nextNeg = 1;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] == 0) continue;
                int sign = a[i] > 0 ? 1 : -1;
                while (a[i] != 0) {
                    int j = sign == -1 ? nextPos : nextNeg;
                    if (a[j] * sign < 0) {
                        if (Math.abs(a[j]) <= Math.abs(a[i])) {
                            sum += Math.abs(a[j]) * (j - i);
                            a[i] += a[j];
                            a[j] = 0;
                        } else {
                            sum += Math.abs(a[i]) * (j - i);
                            a[j] += a[i];
                            a[i] = 0;
                            continue;
                        }
                    }
                    if (sign == -1){
                        nextPos++;
                    } else {
                        nextNeg++;
                    }
                }
            }
            System.out.println(sum);
        }
    }
}
