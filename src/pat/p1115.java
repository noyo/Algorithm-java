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
public class p1115 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int val[] = new int[2001];
    static int lft[] = new int[2001];
    static int rht[] = new int[2001];

    static void insert(int root, int x) {
        if (val[root] < val[x]) {
            if (rht[root] == -1) {
                rht[root] = x;
            } else {
                insert(rht[root], x);
            }
        } else {
            if (lft[root] == -1) {
                lft[root] = x;
            } else {
                insert(lft[root], x);
            }
        }
    }

    static void solve() throws IOException {
        int n = nextInt();
        Arrays.fill(lft, -1);
        Arrays.fill(rht, -1);
        val[0] = nextInt();
        for (int i = 1; i < n; i++) {
            val[i] = nextInt();
            insert(0, i);
        }
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int x = queue.size();
            ans.add(x);
            while (x-- > 0) {
                int q = queue.poll();
                if (lft[q] != -1) {
                    queue.offer(lft[q]);
                }
                if (rht[q] != -1) {
                    queue.offer(rht[q]);
                }
            }
        }
        pw.format("%d + %d = %d", ans.get(ans.size() - 1), ans.size() == 1 ? 0 : ans.get(ans.size() - 2)
                , ans.get(ans.size() - 1) + (ans.size() == 1 ? 0 : ans.get(ans.size() - 2)));
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
