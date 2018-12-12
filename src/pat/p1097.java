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
public class p1097 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        int s = nextInt();
        int n = nextInt();
        int a[] = new int[100000];
        int vv[] = new int[100000];
        Set<Integer> vis = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int pre = nextInt();
            int val = nextInt();
            int nxt = nextInt();
            a[pre] = nxt;
            vv[pre] = val;
        }
        List<Integer> ans = new ArrayList<>();
        List<Integer> re = new ArrayList<>();
        int cur = s;
        while (cur != -1) {
            if (!vis.contains(Math.abs(vv[cur]))) {
                ans.add(cur);
            } else {
                re.add(cur);
            }
            vis.add(Math.abs(vv[cur]));
            cur = a[cur];
        }
        ans.add(cur);
        re.add(cur);
        cur = s;
        for (int i = 1; i < ans.size() - 1; i++) {
            pw.format("%05d %d %05d\n", cur, vv[cur], ans.get(i));
            cur = ans.get(i);
        }
        pw.format("%05d %d %d\n", cur, vv[cur], -1);
        if (re.size() == 1) {
            return;
        }
        cur = re.get(0);
        for (int i = 1; i < re.size() - 1; i++) {
            pw.format("%05d %d %05d\n", cur, vv[cur], re.get(i));
            cur = re.get(i);
        }
        pw.format("%05d %d %d\n", cur, vv[cur], -1);
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
