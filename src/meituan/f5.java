package meituan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/5/30 18:14
 * @see meituan
 */
public class f5 {
    private static int n;
    private static int m;
    private static List<Edge>[] graph;
    private static int min = Integer.MAX_VALUE;

    private static class Edge {
        int dest;
        int cost;
        int st;
        int et;

        Edge(int dest, int cost, int st, int et) {
            this.dest = dest;
            this.cost = cost;
            this.st = st;
            this.et = et;
        }
    }

    private static void output() {
        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
        System.out.println(min);
    }

    private static void createGraph(Scanner sc) {
        for (int i = 0; i < m; i++) {
            int source = sc.nextInt();
            int dest = sc.nextInt();
            int cost = sc.nextInt();
            String time[] = sc.next().split(":");
            int startTime = Integer.parseInt(time[0]) * 2 + Integer.parseInt(time[1]) / 30;
            time = sc.next().split(":");
            int endTime = Integer.parseInt(time[0]) * 2 + Integer.parseInt(time[1]) / 30;
            graph[source].add(new Edge(dest, cost, startTime, endTime));
        }
    }

    private static boolean dfs(int source, int dest, int cost, int time, boolean delay) {

        if (source == dest) {
            if (!delay) {
                min = Math.min(min, cost);
            }
            return true;
        }
        if (time == 48) {
            return false;
        }

        List<Edge> edges = graph[source];
        int avai = -1;
        for (int i = edges.size() - 1; i >= 0; i--) {
            Edge edge = edges.get(i);
            if (edge.st <= time) {
                break;
            }
            if (avai > 0) {
                if (edge.st >= edges.get(avai).st) {
                    continue;
                }
                dfs(edge.dest, dest, cost + edge.cost, edge.et, false);
            } else if (!delay) {
                avai = dfs(edge.dest, dest, 0, edge.et, true) ? i : -1;
            } else if (dfs(edge.dest, dest, 0, edge.et, true)) {
                return true;
            }
        }

        return false;
    }

    private static void getMinCosts() {
        for (int i = 1; i <= n; i++) {
            graph[i].sort(Comparator.comparingInt(o -> o.st));
        }
        dfs(1, n, 0, -1, false);
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        createGraph(sc);
        getMinCosts();

        output();
    }
}
