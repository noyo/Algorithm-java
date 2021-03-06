package pat;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1126 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        int n = nextInt();
        int m = nextInt();
        int cnt[] = new int[n + 1];
        List<Integer> edges[] = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int f = nextInt();
            int t = nextInt();
            edges[f].add(t);
            edges[t].add(f);
            cnt[f]++;
            cnt[t]++;
        }
        boolean vis[] = new boolean[n + 1];
        vis[1] = true;
        int ok = n;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            int x = queue.size();
            ok -= x;
            while (x-- > 0) {
                int q = queue.poll();
                for (int i = 0; i < edges[q].size(); i++) {
                    if (vis[edges[q].get(i)]) {
                        continue;
                    }
                    vis[edges[q].get(i)] = true;
                    queue.offer(edges[q].get(i));
                }
            }
        }
        int odd = 0;
        for (int i = 1; i <= n; i++) {
            if ((cnt[i] & 1) == 1) {
                odd++;
            }
        }
        for (int i = 1; i <= n; i++) {
            pw.print(cnt[i]);
            if (i < n) {
                pw.print(" ");
            }
        }
        pw.println();
        if (ok == 0 && odd == 0) {
            pw.println("Eulerian");
        } else if (ok == 0 && odd == 2) {
            pw.println("Semi-Eulerian");
        } else {
            pw.println("Non-Eulerian");
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
