package eleven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/14 15:16
 * @see eleven
 */
public class Uva1151 {

    private static List<List<Edge>> qEdges = new ArrayList<>();
    private static List<Integer> cost = new ArrayList<>();

    /**
     * 最小生成树
     */
    private static class Tree {
        List<Edge> edges;
        int w;

        Tree(List<Edge> edges, int w) {
            this.edges = edges;
            this.w = w;
        }

        void clear() {
            edges.clear();
        }
    }

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

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int distance(Point p) {
            return (int) (Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    private static List<Edge> init(Scanner sc) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        qEdges.clear();
        cost.clear();
        for (int i = 0; i < q; i++) {
            int qn = sc.nextInt();
            if (qn <= 1) {
                continue;
            }
            cost.add(sc.nextInt());

            List<Edge> edgeList = new ArrayList<>(qn * (qn - 1) / 2);
            List<Integer> vertex = new ArrayList<>(qn);
            for (int j = 0; j < qn; j++) {
                int v = sc.nextInt();
                for (int k = 0; k < j; k++) {
                    edgeList.add(new Edge(vertex.get(k), v, 0));
                }
                vertex.add(v);
            }
            qEdges.add(edgeList);
        }

        List<Edge> edges = new ArrayList<>();
        Point[] points = new Point[n + 1];
        for (int i = 1; i <= n; i++) {
            points[i] = new Point(sc.nextInt(), sc.nextInt());
            for (int j = 1; j < i; j++) {
                edges.add(new Edge(j, i, points[j].distance(points[i])));
            }
        }
        edges.sort(Comparator.comparingInt(o -> o.w));
        return edges;
    }

    /**
     * 获取根节点
     */
    private static int getRoot(int p[], int x) {
        if (p[x] == x) {
            return x;
        }
        return p[x] = getRoot(p, p[x]);
    }

    /**
     * 构建最小生成树
     */
    private static Tree generateMinTree(List<Edge> edges) {
        List<Edge> tree = new ArrayList<>();
        int w = 0;
        int n = edges.size();
        int m = 0;
        int p[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            int u = getRoot(p, edge.u);
            int v = getRoot(p, edge.v);
            if (u == v) {
                continue;
            }
            p[u] = v;
            m++;
            tree.add(edge);
            w += edge.w;
            if (m == n - 1) {
                break;
            }
        }
        return new Tree(tree, w);
    }

    private static int dfs(Tree minTree, int s, int q, int w) {

        if (s == q) {
            return minTree.w;
        }

        List<Edge> edges = new ArrayList<>(qEdges.get(s));
        edges.addAll(minTree.edges);

        Tree cur = generateMinTree(edges);
        cur.w += cost.get(s) + w;

        return Math.min(dfs(minTree, s + 1, q, w), dfs(cur, s + 1, q, w + cost.get(s)));
    }

    private static void solve(List<Edge> edges) {
        System.out.println(dfs(generateMinTree(edges), 0, qEdges.size(), 0));
    }


    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while (T-- > 0) {
            solve(init(sc));
            if (T > 0) {
                System.out.println();
            }
        }
    }
}
