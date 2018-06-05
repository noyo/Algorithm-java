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


    private static boolean dfs(int match[][], Integer team[], int n) {
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

        boolean vst[] = new boolean[n + 1];
        helper(match, gray, black, vst, 0, newTeam);
        int left = gray.size();
        for (int i = 0; i < gray.size(); i++) {
            if (vst[gray.get(i)]) {
                left--;
            }
        }
        helper(match, gray, vst, 0, left, newTeam);
        for (int i = 0; i < gray.size(); i++) {
            if (!vst[gray.get(i)]) {
                result.add(new int[] {1, gray.get(i)});
                break;
            }
        }
        return dfs(match, (Integer[]) newTeam.toArray(), n / 2);
    }

    private static boolean helper(int[][] match, List<Integer> gray, boolean[] vst, int s, int left, List<Integer> newTeam) {
        if (left == 1) {
            return true;
        }
        for (int i = s; i < gray.size(); i++) {
            if (vst[gray.get(i)]) {
                continue;
            }
            vst[gray.get(i)] = true;
            for (int j = i + 1; j < gray.size(); j++) {
                boolean flag = match[gray.get(i)][gray.get(j)] == 0;
                if (vst[gray.get(j)] || flag && match[gray.get(j)][gray.get(i)] == 0) {
                    continue;
                }
                result.add(new int[]{gray.get(i), gray.get(j)});
                vst[gray.get(j)] = true;
                if (flag) {
                    newTeam.add(gray.get(j));
                } else {
                    newTeam.add(gray.get(i));
                }
                if (helper(match, gray, vst, i + 1, left - 2, newTeam)) {
                    return true;
                }
                newTeam.remove(newTeam.size() - 1);
                result.remove(result.size() - 1);
                vst[gray.get(j)] = false;
            }
            vst[gray.get(i)] = false;
        }
        return false;
    }

    private static boolean helper(int[][] match, List<Integer> gray, List<Integer> black, boolean[] vst, int s, List<Integer> newTeam) {
        if (s == black.size()) {
            return true;
        }
        for (int i = s; i < black.size(); i++) {
            for (int j = 0; j < gray.size(); j++) {
                if (vst[gray.get(j)] || match[black.get(i)][gray.get(j)] == 0) {
                    continue;
                }
                vst[gray.get(j)] = true;
                result.add(new int[]{gray.get(j), black.get(i)});
                newTeam.add(gray.get(j));
                if (helper(match, gray, black, vst, s + 1, newTeam)) {
                    return true;
                }
                newTeam.remove(newTeam.size() - 1);
                result.remove(result.size() - 1);
                vst[gray.get(j)] = false;
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
            Integer team[] = new Integer[n];
            for (int i = 1; i <= n; i++) {
                team[i - 1] = i;
                char ch[] = sc.next().toCharArray();
                for (int j = 1; j <= n; j++) {
                    match[i][j] = ch[j - 1] - '0';
                }
            }

            result.clear();
            dfs(match, team, n);
            for (int i = result.size() - 1; i >= 0; i--) {
                int a[] = result.get(i);
                System.out.println(a[0] + " " + a[1]);
            }
        }
    }
}
