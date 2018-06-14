package eleven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: eleven
 * author: Chris
 * date: 2018/6/14 9:21
 */
public class Uva1395 {

    private static List<Edge> edges = new ArrayList<>();

    private static class Edge {
        int u;
        int v;
        int w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString() {
            return u + " " + v + " " + w;
        }
    }

    private static void init(int p[], int n, int m, Scanner sc) {
        edges.clear();
        for (int i = 0; i < m; i++) {
            edges.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        edges.sort(Comparator.comparingInt(o -> o.w));
    }

    private static int getParent(int p[], int u) {
        if (p[u] == u) {
            return u;
        }
        return p[u] = getParent(p, p[u]);
    }

    private static int generate(int p[], int n, int i, int m) {
        int sum = 0;
        int min = edges.get(i).w;
        for (; i < m; i++) {
            Edge edge = edges.get(i);
            int x = getParent(p, edge.u);
            int y = getParent(p, edge.v);
            if (x == y) {
                continue;
            }
            p[x] = edges.get(i).v;
            sum++;
            if (sum == n - 1) {
                break;
            }
        }
        if (sum < n - 1) {
            return Integer.MAX_VALUE;
        }
        return edges.get(i).w - min;
    }

    private static void solve(int[] p, int n, int m) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= m - n + 1; i++) {
            for (int j = 1; j <= n; j++) {
                p[j] = j;
            }
            min = Math.min(min, generate(p, n, i, m));
        }
        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
        System.out.println(min);
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream(("src/out.txt")));

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            if ((n | m) == 0) {
                break;
            }

            int p[] = new int[n + 1];
            init(p, n, m, sc);

            solve(p, n, m);
        }
    }
}
