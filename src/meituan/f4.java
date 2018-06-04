package meituan;

import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/5/28 21:30
 * @see meituan
 */
public class f4 {
    private static int result[];
    private static int lost[] = new int[2];
    private static int n, m, k, C;
    private static double weight[], score[][];
    private static List<Score> total;

    private static class Score {
        double score[];
        double faw = 0;
        int index;

        Score(int i) {
            index = i;
            score = new double[C + 1];
        }
    }

    private static void output() {
        for (int i = 0; i < n; i++) {
            System.out.println(result[i]);
        }
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        C = sc.nextInt();
        score = new double[n][m];
        weight = new double[m];
        double W = 0;
        for (int i = 0; i < m; i++) {
            weight[i] = sc.nextDouble();
            W += weight[i];
        }
        for (int i = 0; i < m; i++) {
            weight[i] /= W;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                score[i][j] = sc.nextDouble();
                if (score[i][j] == -1) {
                    lost[0] = i;
                    lost[1] = j;
                }
            }
        }

        result = new int[n];
        Arrays.fill(result, 1);
        total = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            total.add(new Score(i));
        }
    }

    private static void getMark() {
        for (int j = 0; j < m; j++) {
            if (j == lost[1] || weight[j] == 0) {
                continue;
            }
            double max = 0;
            for (int i = 0; i < n; i++) {
                max = Math.max(max, score[i][j]);
            }
            if (max == 0) {
                continue;
            }
            for (int i = 0; i < n; i++) {
                total.get(i).faw += weight[j] * score[i][j] / max;
            }
        }
    }

    private static void getTotal() {
        int j = lost[1];
        if (weight[j] == 0) {
            C = 0;
        }
        double max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, score[i][j]);
        }
        for (int mark = 0; mark <= C; mark++) {
            score[lost[0]][j] = mark;
            max = Math.max(max, mark);
            for (int i = 0; i < n; i++) {
                total.get(i).score[mark] = total.get(i).faw;
                if (max > 0) {
                    total.get(i).score[mark] += weight[j] * score[i][j] / max;
                }
            }
        }
    }


    private static void getOutput() {
        Arrays.fill(result, 0);
        k = n - k - 1;
        for (int mark = 0; mark <= C; mark++) {
            int finalI = mark;
            total.sort(Comparator.comparingDouble(o -> o.score[finalI]));
            double critical = total.get(k).score[mark];
            boolean flag = total.get(k + 1).score[mark] == critical;
            for (int i = 0; i < n; i++) {
                int output = result[total.get(i).index];
                if (total.get(i).score[mark] > critical) {
                    output = (output == 0) ? 1 : (output == 2 ? 3 : output);
                } else if (flag && total.get(i).score[mark] == critical) {
                    output = 3;
                } else {
                    output = (output == 0) ? 2 : (output == 1 ? 3 : output);
                }
                result[total.get(i).index] = output;
            }
        }
    }

    public static void main(String args[]) {

        init();

        if (k == n) {
            output();
            return;
        }

        getMark();
        getTotal();

        getOutput();

        output();
    }
}
