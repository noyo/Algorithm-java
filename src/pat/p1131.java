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
public class p1131 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static class Edge {
        int to, line;

        Edge(int t, int l) {
            to = t;
            line = l;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "to=" + to +
                    ", line=" + line +
                    '}';
        }
    }

    static int MAX = 10000;
    @SuppressWarnings("unchecked")
    static List<Edge>[] edges = new List[MAX];
    static int n;
    static int dist[], cnt[], minDist, minCnt;
    static List<int[]> lines = new ArrayList<>();

    static void dfs(int st, List<int[]> ls, int target) {
        if (st == target) {
            if (dist[target] < minDist || (minDist == dist[target] && ls.size() < minCnt)) {
                minDist = dist[target];
                minCnt = ls.size();
                lines.clear();
                lines.addAll(new ArrayList<>(ls));
            }
            return;
        }
        List<Edge> es = edges[st];
        for (int i = 0; i < es.size(); i++) {
            Edge e = es.get(i);
            if (dist[e.to] != -1 && dist[e.to] < dist[st] + 1) {
                continue;
            }
            boolean add = false;
            dist[e.to] = dist[st] + 1;
            if (ls.size() == 0 || ls.get(ls.size() - 1)[0] != e.line) {
                ls.add(new int[]{e.line, st});
                add = true;
            }
            cnt[e.to] = ls.size();
            dfs(e.to, ls, target);

            if (add) {
                ls.remove(ls.size() - 1);
            }
        }
    }

    static class Route {
        int u;
        List<int[]> lines = new ArrayList<>();

        Route(int uu) {
            u = uu;
        }

        @Override
        public String toString() {
            return "Route{" +
                    "u=" + u +
                    ", lines=" + lines +
                    '}';
        }
    }

    static void bfs(int st, List<int[]> ls, int target) {
        Queue<Route> queue = new LinkedList<>();
        queue.offer(new Route(st));
        boolean ok = false;
        int dst = 0;
        Arrays.fill(dist, -1);
        dist[st] = 0;
        while (!ok && !queue.isEmpty()) {
            dst++;
            int x = queue.size();
            while (x-- > 0) {
                Route route = queue.poll();
                List<Edge> es = edges[route.u];
                if (null == es || es.size() == 0) {
                    continue;
                }
                for (int i = 0; i < es.size(); i++) {
                    Edge e = es.get(i);
                    if (dist[e.to] != -1 && dist[e.to] < dst - 1) {
                        continue;
                    }
                    if (route.lines.size() == 0 || route.lines.get(route.lines.size() - 1)[0] != e.line) {
                        route.lines.add(new int[]{e.line, route.u});
                    }
                    dist[e.to] = dst;

                    if (e.to == target) {
                        ok = true;
                        if (ls.size() == 0 || ls.size() > route.lines.size()) {
                            ls.clear();
                            ls.addAll(new ArrayList<>(route.lines));
                        }
                        continue;
                    }
                    Route r = new Route(e.to);
                    r.lines = new ArrayList<>(route.lines);
                    queue.offer(r);
                }
            }
            if (ok) {
                queue.clear();
            }
        }
    }

    static void solve() throws IOException {
        n = nextInt();
        for (int i = 0; i < MAX; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            int m = nextInt();
            int st = nextInt();
            for (int j = 2; j <= m; j++) {
                int nxt = nextInt();
                edges[st].add(new Edge(nxt, i));
                edges[nxt].add(new Edge(st, i));
                st = nxt;
            }
        }
        int k = nextInt();
        dist = new int[MAX];
        cnt = new int[MAX];
        for (int i = 0; i < k; i++) {
            Arrays.fill(dist, -1);
            Arrays.fill(cnt, -1);
            lines.clear();
            int st = nextInt();
            int to = nextInt();
            dist[st] = 0;
            cnt[st] = 0;
            minDist = MAX;
            minCnt = MAX;
            dfs(st, new ArrayList<>(), to);
            pw.println(minDist);
//            bfs(st, lines, to);
//            pw.println(dist[to]);
            for (int j = 0; j < lines.size(); j++) {
                int t = j == lines.size() - 1 ? to : lines.get(j + 1)[1];
                pw.format("Take Line#%d from %04d to %04d.\n", lines.get(j)[0], lines.get(j)[1], t);
            }
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
