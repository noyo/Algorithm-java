package pat;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 14:11
 * @see pat
 */
public class P1151 {
    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int in[];
    static Map<Integer, Integer> p = new HashMap<>();
    static Map<Integer, Integer> L = new HashMap<>();


    static void dfs(int i1, int i2, int val) throws IOException {
        int i = i1;
        for (; i <= i2; i++) {
            if (in[i] == val) {
                break;
            }
        }
        if (i > i1) {
            int left = nextInt();
            p.put(left, val);
            L.put(left, L.get(val) + 1);
            dfs(i1, i - 1, left);
        }
        if (i < i2) {
            int right = nextInt();
            p.put(right, val);
            L.put(right, L.get(val) + 1);
            dfs(i + 1, i2, right);
        }
    }

    private static void solve() throws IOException {
        int m = nextInt();
        int n = nextInt();
        in = new int[n];
        for (int i = 0; i < n; i++) {
            in[i] = nextInt();
        }
        int val = nextInt();
        p.put(val, val);
        L.put(val, 1);
        dfs(0, n - 1, val);
        for (int i = 0; i < m; i++) {
            int x = nextInt();
            int y = nextInt();
            boolean vis1 = p.containsKey(x);
            boolean vis2 = p.containsKey(y);
            if (!vis1 && !vis2) {
                pw.format("ERROR: %d and %d are not found.\n", x, y);
            } else if (!vis1) {
                pw.format("ERROR: %d is not found.\n", x);
            } else if (!vis2) {
                pw.format("ERROR: %d is not found.\n", y);
            } else {
                int px = x;
                int py = y;
                while (L.get(px) > L.get(py)) {
                    px = p.get(px);
                }
                while (L.get(py) > L.get(px)) {
                    py = p.get(py);
                }
                while (px != py) {
                    px = p.get(px);
                    py = p.get(py);
                }
                if (px == x) {
                    pw.format("%d is an ancestor of %d.\n", x, y);
                } else if (py == y) {
                    pw.format("%d is an ancestor of %d.\n", y, x);
                } else {
                    pw.format("LCA of %d and %d is %d.\n", x, y, px);
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
