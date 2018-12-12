package pat;

import java.io.*;
import java.util.Arrays;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1135 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int n, k;
    static int lft[], rht[], a[];
    static int deep = -1;

    static void dfs(int i, int j) {
        if (i == j) {
            return;
        }
        int r = i + 1;
        while (r <= j && Math.abs(a[r]) < Math.abs(a[i])) {
            r++;
        }
        if (r > i + 1) {
            lft[i] = i + 1;
            dfs(i + 1, r - 1);
        }
        if (r <= j) {
            rht[i] = r;
            dfs(r, j);
        }
    }

    static boolean dfs2(int p, int d) {
        if (a[p] > 0) {
            d++;
        }
        boolean ok = true;
        if (lft[p] == -1 && rht[p] == -1) {
            if (deep == -1) {
                deep = d;
                return true;
            }
            return deep == d;
        }
        if (a[p] < 0
                && (lft[p] != -1 && a[lft[p]] < 0
                || rht[p] != -1 && a[rht[p]] < 0)) {
            return false;
        }
        if (lft[p] == -1 || rht[p] == -1) {
            if (deep == -1) {
                deep = d;
            }
            if (a[p] < 0 || deep != d) {
                return false;
            }
        }
        deep = -1;
        if (lft[p] != -1) {
            ok = dfs2(lft[p], d);
        }
        if (rht[p] != -1) {
            ok &= dfs2(rht[p], d);
        }
        return ok;
    }

    static void solve() throws IOException {
        k = nextInt();
        lft = new int[31];
        rht = new int[31];
        while (k-- > 0) {
            Arrays.fill(lft, -1);
            Arrays.fill(rht, -1);
            n = nextInt();
            a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            if (a[0] < 0) {
                pw.format("No\n");
                continue;
            }
            dfs(0, n - 1);
            if (dfs2(0, 0)) {
                pw.format("Yes\n");
            } else {
                pw.format("No\n");
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
