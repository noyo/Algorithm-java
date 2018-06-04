package eight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/4 19:29
 * @see eight
 */
public class Uva12627 {

    private static long get(int K, int A) {
        if (A == 0) {
            return 0;
        }
        if (K == 0) {
            return 1;
        }
        long a;
        if (A < (1 << K - 1)) {
            a = 2 * get(K - 1, A);
        } else {
            a = (long) (2 * Math.pow(3, K - 1)) + get(K - 1, A - (1 << K - 1));
        }
        return a;
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int K = sc.nextInt();
            int A = sc.nextInt();
            int B = sc.nextInt();

            long a = get(K, A - 1);
            long b = get(K, B);

            System.out.println("Case " + i + ": " + (b - a));
        }
    }
}
