package pat;

import java.io.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1122 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        int n = nextInt();
        int m = nextInt();
        boolean link[][] = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int f = nextInt();
            int t = nextInt();
            link[t][f] = link[f][t] = true;
        }
        int k = nextInt();
        for (int i = 0; i < k; i++) {
            int l = nextInt();
            boolean ok = true;
            int pre = nextInt();
            boolean vis[] = new boolean[n + 1];
            vis[pre] = true;
            int first = pre;
            for (int j = 1; j < l; j++) {
                int cur = nextInt();
                if (vis[cur] && j != l - 1
                        || j == l - 1 && cur != first
                        || !link[pre][cur]
                        || cur >= l) {
                    ok = false;
                }
                vis[cur] = true;
                pre = cur;
            }
            if (ok) {
                pw.println("YES");
            } else {
                pw.println("NO");
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
