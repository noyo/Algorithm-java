package pat;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1143 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int n, q, a[], nxt[];
    static int dp[][], lvl[];
    static Map<Integer, Integer> idx = new HashMap<>();

    static void dfs(int i, int j) {
        if (i >= j) {
            return;
        }
        int r = nxt[i];
        if (r != i + 1) {
            dp[0][i + 1] = i;
            lvl[i + 1] = lvl[i] + 1;
        }
        if (r != -1 && r <= j) {
            dp[0][r] = i;
            lvl[r] = lvl[i] + 1;
            dfs(i + 1, r - 1);
            dfs(r, j);
        } else {
            dfs(i + 1, j);
        }
    }

    static void solve() throws IOException {
        q = nextInt();
        n = nextInt();
        a = new int[n];
        nxt = new int[n];
        dp = new int[14][n];
        lvl = new int[n];
        Arrays.fill(a, -1);
        Arrays.fill(nxt, -1);
        Arrays.fill(lvl, -1);
        for (int i = 0; i < 14; i++) {
            Arrays.fill(dp[i], -1);
        }
        Stack<Integer> tack = new Stack<>();
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
            idx.put(a[i], i);
            if (!tack.empty() && a[tack.peek()] <= a[i]) {
                while (!tack.empty() && a[tack.peek()] < a[i]) {
                    nxt[tack.pop()] = i;
                }
            }
            tack.push(i);
        }
        dp[0][0] = 0;
        lvl[0] = 1;
        dfs(0, n - 1);
        for (int i = 1; i < 14; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i - 1][j] == -1) {
                    continue;
                }
                dp[i][j] = dp[i - 1][dp[i - 1][j]];
            }
        }

        while (q-- > 0) {
            int from = nextInt(), to = nextInt();
            int formVal = from;
            int toVal = to;
            from = idx.getOrDefault(from, -1);
            to = idx.getOrDefault(to, -1);
            if (from == -1 && to == -1) {
                pw.format("ERROR: %d and %d are not found.\n", formVal, toVal);
            } else if (-1 == from) {
                pw.format("ERROR: %d is not found.\n", formVal);
            } else if (-1 == to) {
                pw.format("ERROR: %d is not found.\n", toVal);
            } else {
                int f = from, t = to;
                boolean re = false;
                if (lvl[f] < lvl[t]) {
                    re = true;
                    f ^= t;
                    t ^= f;
                    f ^= t;
                }
                int d = lvl[f] - lvl[t];
                int cur = 0;
                while (d > 0) {
                    if ((d & 1) == 1) {
                        f = dp[cur][f];
                    }
                    d >>= 1;
                    cur++;
                }
                if (re) {
                    f ^= t;
                    t ^= f;
                    f ^= t;
                }
                for (int i = 13; i >= 0; i--) {
                    if (dp[i][f] != dp[i][t]) {
                        f = dp[i][f];
                        t = dp[i][t];
                    }
                }
                if (f != t) {
                    f = dp[0][f];
                }
                if (f == from) {
                    pw.format("%d is an ancestor of %d.\n", formVal, toVal);
                } else if (f == to) {
                    pw.format("%d is an ancestor of %d.\n", toVal, formVal);
                } else {
                    pw.format("LCA of %d and %d is %d.\n", formVal, toVal, a[f]);
                }
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
