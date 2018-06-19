package eleven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/19 9:34
 * @see eleven
 */
public class Uva247 {

    private static void input(Scanner sc, int m, Map<String, Integer> vertex
            , Map<Integer, String> map, int d[][]) {
        int k = 0;
        for (int i = 0; i < m; i++) {
            String u = sc.next();
            String v = sc.next();
            if (!vertex.containsKey(u)) {
                map.put(k, u);
                vertex.put(u, k++);
            }
            if (!vertex.containsKey(v)) {
                map.put(k, v);
                vertex.put(v, k++);
            }
            d[vertex.get(u)][vertex.get(v)] = 1;
        }
    }

    private static void floyd(int n, int d[][]) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (d[i][j] == 0 && (d[i][k] & d[k][j]) == 1) {
                        d[i][j] = 1;
                    }
                }
            }
        }
    }

    private static void divide(int n, int d[][], List<List<Integer>> result) {
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; !flag && j < result.size(); j++) {
                for (int k = 0; k < result.get(j).size(); k++) {
                    if (d[result.get(j).get(k)][i] == 1 && d[i][result.get(j).get(k)] == 1) {
                        result.get(j).add(i);
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                result.add(list);
            }
        }
    }

    private static void output(Map<Integer,String> map, List<List<Integer>> result) {
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(map.get(result.get(i).get(j)));
                if (j < result.get(i).size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    private static void solve(Scanner sc) {
        Map<String, Integer> vertex = new HashMap<>();
        Map<Integer, String> map = new HashMap<>();
        int d[][];
        List<List<Integer>> result = new ArrayList<>();
        int c = 1;
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (0 == n) {
                break;
            }
            if (c > 1) {
                System.out.println();
            }
            System.out.format("Calling circles for data set %d:\n", c++);
            int m = sc.nextInt();
            if (m == 0) {
                continue;
            }

            vertex.clear();
            map.clear();
            d = new int[n][n];
            result.clear();

            input(sc, m, vertex, map, d);
            floyd(n, d);
            divide(n, d, result);
            output(map, result);
        }
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        solve(sc);
    }
}
