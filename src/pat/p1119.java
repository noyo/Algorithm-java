package pat;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1119 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static boolean ok = true;
    static int pre[] = new int[31];
    static int post[] = new int[31];
    static StringBuilder sb = new StringBuilder();
    static Map<Integer, Integer> idx = new HashMap<>();

    static void dfs(int i1, int i2, int j2) {
        if (i2 == j2) {
            sb.append(post[i2]).append(" ");
            return;
        }
        int i = idx.get(pre[i1 + 1]);
        if (i == j2 - 1) {
            ok = false;
            dfs(i1 + 1, i2, j2 - 1);
            sb.append(post[j2]).append(" ");
        } else if (i == i2) {
            sb.append(post[i]).append(" ");
            sb.append(post[j2]).append(" ");
            dfs(i1 + 2, i2 + 1, j2 - 1);
        } else {
            dfs(i1 + 1, i2, i);
            sb.append(post[j2]).append(" ");
            dfs(i1 + i - i2 + 2, i + 1, j2 - 1);
        }
    }

    static void solve() throws IOException {
        int n = nextInt();
        for (int i = 0; i < n; i++) {
            pre[i] = nextInt();
        }
        for (int i = 0; i < n; i++) {
            post[i] = nextInt();
            idx.put(post[i], i);
        }
        dfs(0, 0, n - 1);

        if (ok) {
            pw.println("Yes");
        } else {
            pw.println("No");
        }

        pw.println(sb.toString().trim());
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
