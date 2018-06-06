package eight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * TODO 未完成
 *
 * @author Chris
 * 2018/6/5 14:30
 * @see eight
 */
public class Uva1609 {

    private static List<int[]> result = new ArrayList<>();


    private static boolean dfs(int match[][], int team[], int n) {
        if (2 == n) {
            result.add(new int[] {team[0], team[1]});
            return true;
        }
        List<Integer> black = new ArrayList<>();
        List<Integer> gray = new ArrayList<>();
        ArrayList<Integer> newTeam = new ArrayList<>();
        newTeam.add(1);
        for (int i = 1; i < n; i++) {
            if (match[1][team[i]] == 0) {
                black.add(team[i]);
            } else {
                gray.add(team[i]);
            }
        }

        boolean vst[] = new boolean[1024 + 1];
        List<Integer> bLeft = new ArrayList<>();
        helper(match, gray, black, vst, newTeam, bLeft);
        for (int i = 0; i < bLeft.size() - 1; i += 2) {
            result.add(new int[] {bLeft.get(i), bLeft.get(i + 1)});
            newTeam.add(match[bLeft.get(i)][bLeft.get(i + 1)] == 1 ? bLeft.get(i) : bLeft.get(i + 1));
        }
        for (int i = 0; i < gray.size(); i++) {
            if (!vst[gray.get(i)] && match[1][gray.get(i)] == 1) {
                result.add(new int[] {1, gray.get(i)});
                vst[gray.get(i)] = true;
                break;
            }
        }
        helper(match, gray, vst, newTeam);
        int newT[] = new int[n / 2];
        for (int i = 0; i < newTeam.size(); i++) {
            newT[i] = newTeam.get(i);
        }
        return dfs(match, newT, n / 2);
    }

    private static void helper(int[][] match, List<Integer> gray, boolean[] vst, List<Integer> newTeam) {
        for (int i = 0; i < gray.size(); i++) {
            if (vst[gray.get(i)]) {
                continue;
            }
            vst[gray.get(i)] = true;
            for (int j = i + 1; j < gray.size(); j++) {
                if (vst[gray.get(j)]) {
                    continue;
                }
                boolean flag = match[gray.get(i)][gray.get(j)] == 0;
                result.add(new int[]{gray.get(i), gray.get(j)});
                vst[gray.get(j)] = true;
                if (flag) {
                    newTeam.add(gray.get(j));
                } else {
                    newTeam.add(gray.get(i));
                }
                break;
            }
        }
    }

    private static boolean helper(int[][] match, List<Integer> gray, List<Integer> black, boolean[] vst, List<Integer> newTeam, List<Integer> bLeft) {
        for (int i = 0; i < black.size(); i++) {
            for (int j = 0; j < gray.size(); j++) {
                if (vst[gray.get(j)] || match[gray.get(j)][black.get(i)] == 0) {
                    continue;
                }
                vst[gray.get(j)] = true;
                vst[black.get(i)] = true;
                result.add(new int[]{gray.get(j), black.get(i)});
                newTeam.add(gray.get(j));
                break;
            }
            if (!vst[black.get(i)]) {
                bLeft.add(black.get(i));
            }
        }
        return false;
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int match[][] = new int[n + 1][n + 1];
            int team[] = new int[n];
            for (int i = 1; i <= n; i++) {
                team[i - 1] = i;
                char ch[] = sc.next().toCharArray();
                for (int j = 1; j <= n; j++) {
                    match[i][j] = ch[j - 1] - '0';
                }
            }

            result.clear();
            dfs(match, team, n);
            for (int a[] : result) {
                System.out.println(a[0] + " " + a[1]);
            }
        }
    }
}
