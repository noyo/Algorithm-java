package eleven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/19 10:41
 * @see eleven
 */
public class Uva10048 {

    private static void input(Scanner sc, int n, int m,int d[][]) {
        for (int i = 1; i <= n; i++) {
            Arrays.fill(d[i], 10001);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            d[u][v] = w;
            d[v][u] = w;
        }
    }

    private static void floyd(int n, int d[][]) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    d[i][j] = Math.min(d[i][j], Math.max(d[i][k], d[k][j]));
                }
            }
        }
    }

    private static void output(int Q, Scanner sc, int max, int d[][], int c) {
        if (c > 1) {
            System.out.println();
        }
        System.out.format("Case #%d\n", c);

        for (int i = 0; i < Q; i++) {
            int val = d[sc.nextInt()][sc.nextInt()];
            if (val > max) {
                System.out.println("no path");
            } else {
                System.out.println(val);
            }
        }
    }

    private static void solve(Scanner sc) {
        int c = 1;
        int max = 10000;
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int Q = sc.nextInt();
            if ((n | m | Q) == 0) {
                break;
            }
            int d[][] = new int[n + 1][n + 1];

            input(sc, n, m, d);
            floyd(n, d);
            output(Q, sc, max, d, c++);
        }
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        solve(sc);
    }
}
