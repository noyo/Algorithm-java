package pat;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1064 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int lft[] = new int[2001];
    static int rht[] = new int[2001];

    static int dfs(int a[], int i, int j) {
        if (i > j) {
            return -1;
        }
        if (i == j) {
            return a[i];
        }
        int len = j - i + 1;
        int bit = 1;
        while (len >= (1 << (bit + 1)) - 1) {
            bit++;
        }
        int L = (1 << (bit - 1)) - 1;
        int bot = len - (1 << bit) + 1;
        L += Math.min(1 << (bit - 1), bot);
        int val = a[i + L];
        lft[val] = dfs(a, i, i + L - 1);
        rht[val] = dfs(a, i + L + 1, j);
        return a[i + L];
    }

    static void solve() throws IOException {
        int n = nextInt();
        int a[] = new int[n];
        Arrays.fill(lft, -1);
        Arrays.fill(rht, -1);
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        Arrays.sort(a);
        int root = dfs(a, 0, n - 1);
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int x = queue.size();
            while (x-- > 0) {
                int node = queue.poll();
                sb.append(node).append(" ");
                if (-1 != lft[node]) {
                    queue.offer(lft[node]);
                }
                if (-1 != rht[node]) {
                    queue.offer(rht[node]);
                }
            }
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
