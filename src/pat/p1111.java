package pat;

import java.io.*;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1111 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int n, m, s, t, minD, minT;
    static List<int[]> edges[] = new List[500];
    static int dist[] = new int[500];
    static int time[] = new int[500];
    static List<Integer> distRes = new ArrayList<>();
    static List<Integer> timeRes = new ArrayList<>();

    static void dfs(int from, boolean dfsD, List<Integer> route) {
        if (from == t) {
            if (dfsD) {
                distRes.clear();
                distRes.addAll(route);
            } else {
                timeRes.clear();
                timeRes.addAll(route);
            }
            return;
        }
        List<int[]> es = edges[from];
        for (int i = 0; i < es.size(); i++) {
            int to = es.get(i)[0];
            int d = es.get(i)[1];
            int c = es.get(i)[2];
            int curD = dist[from] + d;
            int curT = time[from] + c;
            if (dfsD) {
                if (dist[to] == -1 || dist[to] > curD || (dist[to] == curD && time[to] > curT)) {
                    dist[to] = curD;
                    time[to] = curT;
                    dfs(dfsD, route, to);
                }
            } else {
                curD = dist[from] + 1;
                if (time[to] == -1 || time[to] > curT || (time[to] == curT && dist[to] > curD)) {
                    dist[to] = curD;
                    time[to] = curT;
                    dfs(dfsD, route, to);
                }
            }
        }
    }

    private static void dfs(boolean dfsD, List<Integer> route, int to) {
        route.add(to);
        dfs(to, dfsD, route);
        route.remove(route.size() - 1);
    }

    static int[] dijkstra(boolean dijD) {
        int pre[] = new int[500];
        Arrays.fill(pre, -1);
        List<int[]> es = edges[s];
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2)
                -> dijD ? (dist[o1] - dist[o2]) : (time[o1] - time[o2]));
        for (int i = 0; i < es.size(); i++) {
            int to = es.get(i)[0];
            int d = es.get(i)[1];
            int c = es.get(i)[2];
            pre[to] = s;
            dist[to] = d;
            time[to] = c;
            queue.offer(to);
        }
        boolean vis[] = new boolean[500];
        vis[s] = true;
        while (!queue.isEmpty()) {
            int from = queue.poll();
            es = edges[from];
            vis[from] = true;
            for (int i = 0; i < es.size(); i++) {
                int to = es.get(i)[0];
                if (vis[to]) {
                    continue;
                }
                int d = es.get(i)[1];
                int c = es.get(i)[2];
                int curD = dist[from] + d;
                int curT = time[from] + c;
                if (dijD) {
                    if (dist[to] == -1 || dist[to] > curD || (dist[to] == curD && time[to] > curT)) {
                        dist[to] = curD;
                        time[to] = curT;
                        pre[to] = from;
                        queue.offer(to);
                    }
                } else {
                    curD = dist[from] + 1;
                    if (time[to] == -1 || time[to] > curT || (time[to] == curT && dist[to] > curD)) {
                        dist[to] = curD;
                        time[to] = curT;
                        pre[to] = from;
                        queue.offer(to);
                    }
                }
            }
        }
        return pre;
    }

    static void solve() throws IOException {
        n = nextInt();
        m = nextInt();

        for (int i = 0; i < 500; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int from = nextInt();
            int to = nextInt();
            int way = nextInt();
            int d = nextInt();
            int c = nextInt();
            edges[from].add(new int[]{to, d, c});
            if (way == 0) {
                edges[to].add(new int[]{from, d, c});
            }
        }
        s = nextInt();
        t = nextInt();
        Arrays.fill(dist, -1);
        Arrays.fill(time, -1);
        dist[s] = 0;
        time[s] = 0;
//        dfs(s, true, new ArrayList<>());
        int pre1[] = dijkstra(true);
        minD = dist[t];

        Arrays.fill(dist, -1);
        Arrays.fill(time, -1);
        dist[s] = 0;
        time[s] = 0;
//        dfs(s, false, new ArrayList<>());
        int pre2[] = dijkstra(false);
        minT = time[t];
        output(pre1, pre2);

        boolean ok = distRes.size() == timeRes.size();
        if (ok) {
            for (int i = 0; i < distRes.size(); i++) {
                if (!distRes.get(i).equals(timeRes.get(i))) {
                    ok = false;
                    break;
                }
            }
        }
        if (ok) {
            pw.format("Distance = %d; Time = %d: %d", minD, minT, s);
            for (Integer distRe : distRes) {
                pw.format(" -> %d", distRe);
            }
        } else {
            pw.format("Distance = %d: %d", minD, s);
            for (Integer distRe : distRes) {
                pw.format(" -> %d", distRe);
            }
            pw.println();
            pw.format("Time = %d: %d", minT, s);
            for (Integer timeRe : timeRes) {
                pw.format(" -> %d", timeRe);
            }
        }
    }

    private static void output(int[] pre1, int[] pre2) {
        int cur = t;
        while (cur != s) {
            distRes.add(0, cur);
            cur = pre1[cur];
        }
        cur = t;
        while (cur != s) {
            timeRes.add(0, cur);
            cur = pre2[cur];
        }
    }

    public static void main(String args[]) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new OutputStreamWriter(System.out));
        st = new StreamTokenizer(br);

        solve();
        pw.flush();
    }

    static String next(int len) throws IOException {
        int b = br.read();
        while (b == '\n' || b == ' ' || b == '\r' || b == '\t') {
            b = br.read();
        }
        char ch[] = new char[len];
        ch[0] = (char) b;
        int idx = 1;
        while (idx < len && (b = br.read()) != ' ' && b != '\n' && b != '\r' && b != '\t') {
            ch[idx++] = (char) b;
        }
        return String.valueOf(ch).trim();
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String nextLine() throws IOException {
        return br.readLine();
    }

    static double nextDouble() throws IOException {
        st.nextToken();
        return st.nval;
    }
}
