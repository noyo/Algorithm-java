package eight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/5 22:02
 * @see eight
 */
public class Uva12265 {

    private static void set(int A[][][][], int i, int j, int direct, int w, int h) {
        A[i][j][direct][0] = w;
        A[i][j][direct][1] = h;
    }

    private static int get(int A[][][][], int i, int j) {
        return 2 * Math.max(Math.max(A[i][j][0][0] + A[i][j][0][1], A[i][j][1][0] + A[i][j][1][1]), A[i][j][2][0] + A[i][j][2][1]);
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            Map<Integer, Integer> cnt = new TreeMap<>();
            int A[][][][] = new int[n][m][3][2];
            for (int i = 0; i < n; i++) {
                char ch[] = sc.next().toCharArray();
                for (int j = 0; j < m; j++) {
                    if (ch[j] == '#') {
                        continue;
                    }
                    if ((i | j) == 0) {
                        set(A, i, j, 0, 1, 1);
                        set(A, i, j, 1, 1, 1);
                        set(A, i, j, 2, 1, 1);
                    } else if (i == 0){
                        set(A, i, j, 0, A[i][j - 1][0][0] + 1, 1);
                        set(A, i, j, 1, 1, 1);
                        set(A, i, j, 2, 1, 1);
                    } else if (j == 0) {
                        set(A, i, j, 0, 1, 1);
                        set(A, i, j, 1, 1, A[i - 1][j][1][1] + 1);
                        set(A, i, j, 2, 1, 1);
                    } else {
                        set(A, i, j, 0, A[i][j - 1][0][0] + 1, 1);
                        set(A, i, j, 1,1, A[i - 1][j][1][1] + 1);

                        int w, h;
                        int w1 = Math.max(Math.min(A[i][j - 1][0][0] + 1, A[i - 1][j][0][0]), 1);
                        int h2 = Math.max(Math.min(A[i - 1][j][1][1] + 1, A[i][j][1][1]), 1);
                        if (w1 > h2) {
                            w = w1;
                            h = 2;
                        } else {
                            w = 2;
                            h = h2;
                        }
                        if (A[i - 1][j][2][0] > 1 && A[i][j - 1][2][1] > 1) {
                            w1 = Math.min(A[i][j - 1][0][0] + 1, A[i - 1][j][2][0]);
                            h2 = Math.min(A[i - 1][j][1][1] + 1, A[i][j - 1][2][1]);
//                            if (w1 + A[i - 1][j][2][1] > h2 [])
                        }
                        set(A, i, j, 2, w, h);
                    }
                    int max = get(A, i, j);
                    cnt.put(max, cnt.getOrDefault(max, 0) + 1);
                }
            }
            if (cnt.size() == 0) {
                System.out.println(0);
            }
            for (int key : cnt.keySet()) {
                System.out.println(cnt.get(key) + " x " + key);
            }
        }
    }
}
