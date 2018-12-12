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
public class p1138 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int n;
    static int pre[];
    static int in[];
    static Map<Integer, Integer> mapIdx = new HashMap<>();

    static int dfs(int i, int i2, int len) {
        if (len == 1) {
            return i;
        }
        if (pre[i] == in[i2]) {
            return dfs(i + 1, i2 + 1, len - 1);
        }
        return dfs(i + 1, i2, mapIdx.get(pre[i]) - i2);
    }

    static void solve() throws IOException {
        n = nextInt();
        pre = new int[n];
        in = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = nextInt();
        }
        for (int i = 0; i < n; i++) {
            in[i] = nextInt();
            mapIdx.put(in[i], i);
        }

        pw.format("%d", pre[dfs(0, 0, n)]);
    }

    public static void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("src/in.txt"));
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
