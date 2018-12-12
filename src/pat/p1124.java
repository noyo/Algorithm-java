package pat;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1124 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        int m = nextInt();
        int n = nextInt();
        int s = nextInt();
        Set<String> vis = new HashSet<>();
        List<String> ans = new ArrayList<>();
        int idx = 0;
        int cur = 1;
        while (idx < m && cur++ < s) {
            next(20);
            idx++;
        }
        cur = 0;
        while (idx++ < m) {
            String name = next(20);
            if (cur % n == 0) {
                if (vis.contains(name)) {
                    continue;
                }
                ans.add(name);
                vis.add(name);
            }
            cur++;
        }
        for (int i = 0; i < ans.size(); i++) {
            pw.println(ans.get(i));
        }
        if (ans.size() == 0) {
            pw.println("Keep going...");
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
