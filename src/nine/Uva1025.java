package nine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
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

    private static int dp(List<Integer> L[], List<Integer> R[], int t[], int S, int MAX) {
        if (MAX < 0) {
            return -1;
        }
        int w = -1;
        List<Integer> left = L[S];
        if (null != left) {
            for (int i = left.size() - 1; i >= 0; i--) {
                if (MAX >= left.get(i)) {
                    int d = dp(L, R, t, S - 1, left.get(i) - t[S - 1]);
                    if (d >= 0) {
                        w = Math.min(w == -1 ? MAX : w, MAX - left.get(i) + d);
                    }
                }
            }
        } else {
            w = left.get(0);
        }
        List<Integer> right = R[S];
        if (null != right) {
            for (int i = right.size() - 1; i >= 0; i--) {
                if (MAX >= right.get(i)) {
                    int d = dp(L, R, t, S + 1, right.get(i) - t[S]);
                    if (d >= 0) {
                        w = Math.min(w == -1 ? MAX : w, MAX - right.get(i) + d);
                    }
                }
            }
        }
        return w;
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int N = sc.nextInt();
            if (N == 0) {
                break;
            }
            int T = sc.nextInt();
            int t[] = new int[N];
            int sum[] = new int[N + 1];
            for (int i = 1; i < N; i++) {
                t[i] = sc.nextInt();
                sum[i] = sum[i] + t[i - 1];
            }
            int M1 = sc.nextInt();
            int d[] = new int[M1 + 1];
            List<Integer> L[] = new List[N + 1];
            for (int i = 1; i <= M1; i++) {
                d[i] = sc.nextInt();
                for (int j = 2; j <= N; j ++) {
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

            System.out.println(dp(L, R, t, N, T));
        }
    }
}
