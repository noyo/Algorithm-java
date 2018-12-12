package pat;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1134 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int n, m, k;
    static int v[];
    static int left[], right[];

    static void solve() throws IOException {
        n = nextInt();
        m = nextInt();
        left = new int[m];
        right = new int[m];
        for (int i = 0; i < m; i++) {
            left[i] = nextInt();
            right[i] = nextInt();
        }
        k = nextInt();
        while (k-- > 0) {
            boolean ok = true;
            int len = nextInt();
            Set<Integer> vis = new HashSet<>();
            for (int i = 0; i < len; i++) {
                vis.add(nextInt());
            }
            for (int i = 0; i < m && ok; i++) {
                if (!vis.contains(left[i]) && !vis.contains(right[i])) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
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
