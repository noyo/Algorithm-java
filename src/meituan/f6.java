package meituan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/3 15:37
 * @see meituan
 */
public class f6 {
    private static int n, m;
    private static List<Edge> edges;
    private static int left[], right[];
    private static Scanner sc;
    private static int result[];

    private static class Edge {
        int x;
        int y;
        int w;

        Edge(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        boolean equal(Edge edge) {
            return x == edge.x && y == edge.y;
        }

        @Override
        public String toString() {
            return x + " " + " " + y + " " + w;
        }
    }

    private static void init() {
        sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        left = new int[n + 1];
        right = new int[n + 1];
        edges = new ArrayList<>();
        result = new int[n + 1];
        Arrays.fill(result, -1);
        for (int i = 0; i < m; i++) {
            edges.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        edges.sort((o1, o2) -> o1.x != o2.x ? o1.x - o2.x : o1.y != o2.y ? o1.y - o2.y : o1.w - o2.w);
        for (int i = edges.size() - 1; i > 0; i--) {
            if (edges.get(i).equal(edges.get(i - 1))) {
                edges.get(i).w = -1;
            }
        }
        edges.sort(Comparator.comparingInt(o -> o.w));
    }

    private static void execu() {
        int start = 0;
        while (start < m && edges.get(start).w < 0) {
            start++;
        }
        int k = 1;
        start--;
        TreeSet<Edge> treeSet = new TreeSet<>((o1, o2) -> o1.x != o2.x ? o1.x - o2.x : o1.y - o2.y);
        for (int i = start + 1; i < m; i++) {
            Edge edge = edges.get(i);
            treeSet.add(edge);
            if ((left[edge.x] == 0 && right[edge.y] == 0)
                    || (i - start - left[edge.x] - right[edge.y] == k)) {
                result[k] = edge.w;
                k++;
                if (k > n) {
                    break;
                }
            }
            left[edge.x]++;
            right[edge.y]++;
        }
    }

    private static void output() {
        for (int i = 1; i <= n; i++) {
            System.out.print(result[i]);
            if (i < n) {
                System.out.print(" ");
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/meituan/in.txt"));
        System.setOut(new PrintStream("src/meituan/out.txt"));

        init();
        execu();
        output();
    }
}
