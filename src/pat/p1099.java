package pat;

import java.io.*;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1099 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void dfs(int root, int lft[], int rht[], List<Integer> res) throws IOException {
        if (root == -1) {
            return;
        }
        int l = lft[root];
        int r = rht[root];
        dfs(l, lft, rht, res);
        res.add(root);
        dfs(r, lft, rht, res);
    }

    static void solve() throws IOException {
        int n = nextInt();
        if (n == 0) {
            return;
        }
        int lft[] = new int[n];
        int rht[] = new int[n];
        for (int i = 0; i < n; i++) {
            lft[i] = nextInt();
            rht[i] = nextInt();
        }
        List<Integer> res = new ArrayList<>();
        dfs(0, lft, rht, res);
        List<Integer> lvl = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int x = queue.size();
            while (x-- > 0) {
                int p = queue.poll();
                lvl.add(p);
                int l = lft[p];
                int r = rht[p];
                if (l != -1) {
                    queue.offer(l);
                }
                if (r != -1) {
                    queue.offer(r);
                }
            }
        }

        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        Arrays.sort(a);
        int val[] = new int[n];
        for (int i = 0; i < n; i++) {
            val[res.get(i)] = a[i];
        }
        for (int i = 0; i < n; i++) {
            pw.print(val[lvl.get(i)]);
            if (i < n - 1) {
                pw.print(" ");
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
