package nine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: nine
 * author: Chris
 * date: 2018/6/7 10:35
 */
public class Uva1025 {

    private static boolean hasTrain[][][];

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        int C = 0;
        final int INF = 100000;
        while (sc.hasNext()) {
            C++;
            int N = sc.nextInt();
            if (N == 0) {
                break;
            }
            int T = sc.nextInt();
            int t[] = new int[N];
            int sum[] = new int[N + 1];
            for (int i = 1; i < N; i++) {
                t[i] = sc.nextInt();
                sum[i + 1] = sum[i] + t[i];
            }
            int M1 = sc.nextInt();
            hasTrain = new boolean[T + 1][N + 1][2];
            for (int i = 1; i <= M1; i++) {
                int d = sc.nextInt();
                for (int j = 1; j < N; j++) {
                    if (d <= T) {
                        hasTrain[d][j][0] = true;
                    } else {
                        break;
                    }
                    d += t[j];
                }
            }
            int M2 = sc.nextInt();
            for (int i = 1; i <= M2; i++) {
               int e = sc.nextInt();
               for (int j = N; j > 1; j--) {
                   if (e <= T) {
                       hasTrain[e][j][1] = true;
                   } else {
                       break;
                   }
                   e += t[j - 1];
               }
            }

            int dp[][] = new int[T + 1][N + 1];
            for (int i = 1; i < N; i++) {
                dp[T][i] = INF;
            }
            dp[T][N] = 0;
            for (int i = T - 1; i >= 0; i--) {
                for (int j = 1; j <= N; j++) {
                    dp[i][j] = dp[i + 1][j] + 1;
                    if (j < N && hasTrain[i][j][0] && i + t[j] <= T) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + t[j]][j + 1]);
                    }
                    if (j > 1 && hasTrain[i][j][1] && i + t[j - 1] <= T) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + t[j - 1]][j - 1]);
                    }
                }
            }

            int wait = dp[0][1];
            System.out.print("Case Number " + C + ": ");
            if (wait >= INF) {
                System.out.println("impossible");
            } else {
                System.out.println(wait);
            }
        }
    }

    /*private static int find(int target, List<Integer> a, int s, int e) {
        while (s < e) {
            int m = (s + e) / 2;
            if (a.get(m) > target) {
                e = m;
            } else {
                s = m;
            }
            if (s == e - 1) {
                break;
            }
        }
        return s;
    }

    private static int find2(int target, int a[], int s, int e) {
        while (s < e) {
            int m = (s + e) / 2;
            if (a[m] > target) {
                e = m;
            } else {
                s = m;
            }
            if (s == e - 1) {
                break;
            }
        }
        return s;
    }

    private static int dp(int first[], List<Integer> L[], List<Integer> R[], int t[], int S, int MAX) {
        if (MAX < 0) {
            return -1;
        }
        int w = -1;
        List<Integer> left = L[S];
        if (null != left) {
            if (MAX >= left.get(0)) {
                int i = find(MAX, left, 0, left.size() - 1);
                int d = dp(first, L, R, t, S - 1, left.get(i) - t[S - 1]);
                if (d >= 0) {
                    w = Math.min(MAX, MAX - left.get(i) + d);
                }
            }
        } else if (MAX >= first[1]) {
            int i = find2(MAX, first, 1, first.length - 1);
            w = first[i];
        }
        List<Integer> right = R[S];
        if (null != right && MAX >= right.get(0)) {
            int i = find(MAX, right, 0, right.size() - 1);
            int d = dp(first, L, R, t, S + 1, right.get(i) - t[S]);
            if (d >= 0) {
                w = Math.min(w == -1 ? MAX : w, MAX - right.get(i) + d);
            }
        }
        return w;
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        int C = 0;
        while (sc.hasNext()) {
            C++;
            int N = sc.nextInt();
            if (N == 0) {
                break;
            }
            int T = sc.nextInt();
            int t[] = new int[N];
            int sum[] = new int[N + 1];
            for (int i = 1; i < N; i++) {
                t[i] = sc.nextInt();
                sum[i + 1] = sum[i] + t[i];
            }
            int M1 = sc.nextInt();
            int d[] = new int[M1 + 1];
            List<Integer> L[] = new List[N + 1];
            for (int i = 1; i <= M1; i++) {
                d[i] = sc.nextInt();
                for (int j = 2; j <= N; j++) {
                    if (null == L[j]) {
                        L[j] = new ArrayList<>();
                    }
                    L[j].add(d[i] + sum[j]);
                }
            }
            int M2 = sc.nextInt();
            int e[] = new int[M2 + 1];
            List<Integer> R[] = new List[N + 1];
            for (int i = 1; i <= M2; i++) {
                e[i] = sc.nextInt();
                for (int j = N - 1; j >= 1; j--) {
                    if (null == R[j]) {
                        R[j] = new ArrayList<>();
                    }
                    R[j].add(e[i] + sum[N] - sum[j]);
                }
            }
            int wait = dp(d, L, R, t, N, T);
            System.out.print("Case Number " + C + ": ");
            if (wait < 0) {
                System.out.println("impossible");
            } else {
                System.out.println(wait);
            }
        }
    }*/
}
