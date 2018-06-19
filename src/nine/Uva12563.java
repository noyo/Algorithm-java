package nine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/17 12:43
 * @see nine
 */
public class Uva12563 {

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int k = 1; k <= T; k++) {
            int n = sc.nextInt();
            int t = sc.nextInt();

            int a[] = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = sc.nextInt();
            }

            int dp[][] = new int[n + 1][t + 1];
            for (int j = 1; j <= t; j++) {
                dp[0][j] = -1;
            }
            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= t; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= a[i] && dp[i - 1][j - a[i]] >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - a[i]] + 1);
                    }
                    cnt = Math.max(cnt, dp[i][j]);
                }
            }
            for (int i = t - 1; i >= 0; i--) {
                if (dp[n][i] == cnt) {
                    System.out.format("Case %d: %d %d\n", k, cnt + 1, i + 678);
                    break;
                }
            }
        }
    }
}
