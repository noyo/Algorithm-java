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
public class p1127 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static Map<Integer, Integer> lft = new HashMap<>();
    static Map<Integer, Integer> rht = new HashMap<>();
    static Map<Integer, Integer> idx = new HashMap<>();
    static int in[] = new int[30];
    static int post[] = new int[30];

    static void dfs(int i, int j, int len) {
        if (len == 1) {
            return;
        }
        int t = post[j];
        int i2 = idx.get(t);
        int l = i2 - i;
        int r = len + i - 1 - i2;
        if (l > 0) {
            lft.put(t, post[j - r - 1]);
            dfs(i, j - r - 1, l);
        }
        if (r > 0) {
            rht.put(t, post[j - 1]);
            dfs(i2 + 1, j - 1, r);
        }
    }

    static void solve() throws IOException {
        int n = nextInt();
        for (int i = 0; i < n; i++) {
            in[i] = nextInt();
            idx.put(in[i], i);
        }
        for (int i = 0; i < n; i++) {
            post[i] = nextInt();
        }
        dfs(0, n - 1, n);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(post[n - 1]);
        int cur = 0;
        StringBuilder sb = new StringBuilder();
        pw.print(post[n - 1]);
        while (!queue.isEmpty()) {
            int x = queue.size();
            List<Integer> ans = new ArrayList<>();
            while (x-- > 0) {
                int q = queue.poll();
                if (lft.containsKey(q)) {
                    queue.offer(lft.get(q));
                    ans.add(lft.get(q));
                }
                if (rht.containsKey(q)) {
                    queue.offer(rht.get(q));
                    ans.add(rht.get(q));
                }
            }
            if (cur % 2 == 0) {
                for (int key : ans)
                    pw.print(" " + key);
            } else {
                for (int i = ans.size() - 1; i >= 0; i--) {
                    pw.print(" " + ans.get(i));
                }
            }
            cur++;
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
