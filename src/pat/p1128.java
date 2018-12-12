package pat;

import java.io.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1128 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        int k = nextInt();
        while (k-- > 0) {
            int n = nextInt();
            boolean ok = true;
            boolean R[] = new boolean[n + 1];
            boolean RL[] = new boolean[n << 1 | 1];
            boolean LL[] = new boolean[n << 1 | 1];
            for (int i = 1; i <= n; i++) {
                int val = nextInt();
                if (!ok) {
                    continue;
                }
                if (R[val] || RL[val + i] || LL[val + n - i + 1]) {
                    ok = false;
                    continue;
                }
                R[val] = true;
                RL[val + i] = true;
                LL[val + n - i + 1] = true;
            }

            pw.format("%s\n", ok ? "YES" : "NO");
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
