package meituan;

import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/5/26 19:11
 * @see meituan
 */
public class f3 {
    private final static int N = 16;
    private static double result[] = new double[N + 1];

    private static void dfs(double[][] ratios, int[] team, int k, double ratio, int newTeam[], int i) {
        if (i == k) {
            play(ratios, newTeam, k, ratio);
            return;
        }
        newTeam[i] = team[i << 1];
        dfs(ratios, team, k, ratio * ratios[team[i << 1]][team[i << 1 | 1]], newTeam, i + 1);
        newTeam[i] = team[i << 1 | 1];
        dfs(ratios, team, k,ratio * ratios[team[i << 1 | 1]][team[i << 1]], newTeam, i + 1);
    }

    private static void play(double[][] ratios, int[] team, int k, double ratio) {

        if (k == 1) {
            result[team[0]] += ratio;
            return;
        }
        dfs(ratios, team, k / 2, ratio, new int[k / 2], 0);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        double ratios[][] = new double[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                ratios[i][j] = sc.nextDouble();
            }
        }
        play(ratios, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10
                , 11, 12, 13, 14, 15, 16}, 16, 1.0);

        for (int i = 1; i <= N; i++) {
            System.out.format("%.10f", result[i]);
            if (i != N) {
                System.out.print(" ");
            }
        }
    }
}
