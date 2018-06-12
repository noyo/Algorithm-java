package ten;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/11 18:37
 * @see ten
 */
public class Uva111582 {

    private static int mod(int a, long b, int mod) {
        if (b <= 1) {
            return (int) Math.pow(a, b);
        }
        int c = (int) (b % 2);
        return (int) (Math.pow(a, c) * Math.pow(mod(a, b / 2, mod), 2) % mod);
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        long time = System.currentTimeMillis();
        int f[][] = new int[1001][1001 * 6];
        int len[] = new int[1001];
        for (int i = 2; i <= 1000; i++) {
            f[i][0] = 0;
            f[i][1] = 1;
            for (int j = 2; ; j++) {
                f[i][j] = (f[i][j - 1] + f[i][j - 2]) % i;
                if (f[i][j] == 1 && f[i][j - 1] == 0) {
                    len[i] = j - 1;
                    break;
                }
            }
        }
//        System.out.println(System.currentTimeMillis() - time);
//        System.out.println();

        int T = sc.nextInt();
        while (T-- > 0) {
            BigInteger bigA = sc.nextBigInteger();
            BigInteger b = sc.nextBigInteger();
            int n = sc.nextInt();

            if (bigA.doubleValue() == 0 || n == 1) {
                System.out.println(0);
                continue;
            }

            int mod = len[n];
            int j = 0;
            int a = bigA.mod(new BigInteger(String.valueOf(mod))).intValue();
            if (b.doubleValue() <= 1) {
                j = (int) (Math.pow(a, b.intValue()) % mod);
            } else {
                int c = b.mod(new BigInteger(String.valueOf(2))).intValue();
                j = (int) (Math.pow(a, c) * Math.pow(mod(a, b.divide(new BigInteger(String.valueOf(2))).longValue(), mod), 2) % mod);
            }
            System.out.println(f[n][j]);
        }
//        System.out.println();
//        System.out.println(System.currentTimeMillis() - time);
    }
}
