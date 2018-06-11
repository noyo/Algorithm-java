package nine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/8 8:52
 * @see nine
 */
public class Uva116 {

    private static int cmp(int a[], int b[], int n) {
        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                return a[i] - b[i];
            }
        }
        return 0;
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int matrix[][] = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }

            int dp[][] = new int[m][n];
            int cur[][] = new int[m][0];
            int len = 0;
            int last[] = new int[m];
            for (int j = 0; j < n; j++) {
//                for (int i = 0; i < m; i++) {
//                    Arrays.fill(sort[i], 0);
//                }
                int sort[][] = new int[m][m];
                for (int i = 0; i < m; i++) {
                    if (j == 0) {
                        dp[i][j] += matrix[i][j];
                    } else {
                        int min = (i + m - 1) % m;
                        if (dp[i][j - 1] < dp[min][j - 1] || (dp[i][j - 1] == dp[min][j - 1] && (sort[i][min] < 0 || cmp(cur[i], cur[min], len) < 0))) {
                            sort[i][min] = -1;
                            min = i;
                        }
                        if (dp[(i + 1) % m][j - 1] < dp[min][j - 1] || (dp[(i + 1) % m][j - 1] == dp[min][j - 1] && (sort[(i + 1) % m][min] < 0 ||  cmp(cur[(i + 1) % m], cur[min], len) < 0))) {
                            sort[(i + 1) % m][min] = -1;
                            min = (i + 1) % m;
                        }
                        dp[i][j] = dp[min][j - 1] + matrix[i][j];
                        last[i] = min;
                    }
                }

                int[][] tmp = cur;
                cur = new int[m][n];
                for (int i = 0; i < m; i++) {
                    cur[i] = Arrays.copyOf(tmp[last[i]], len + 1);
                    cur[i][len] = i + 1;
                }
                len++;
            }
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++) {
                min = Math.min(min, dp[i][n - 1]);
            }
            int minI = -1;
            for (int i = 0; i < m; i++) {
                if (dp[i][n - 1] == min && (minI < 0 || cmp(cur[i], cur[minI], n) < 0)) {
                    minI = i;
                }
            }
            for (int i = 0; i < n; i++) {
                if (i == n - 1) {
                    System.out.println(cur[minI][i]);
                } else {
                    System.out.print(cur[minI][i] + " ");
                }
            }
            System.out.println(dp[minI][n - 1]);
        }
    }
}
