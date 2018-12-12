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
public class p1102 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int n, lft[], rht[];

    static void level(int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        int idx = 0;
        while (!queue.isEmpty()) {
            int x = queue.size();
            while (x-- > 0) {
                idx++;
                int q = queue.poll();
                pw.print(q);
                if (idx < n) {
                    pw.print(" ");
                }
                if (lft[q] != -1) {
                    queue.offer(lft[q]);
                }
                if (rht[q] != -1) {
                    queue.offer(rht[q]);
                }
            }
        }
    }

    static int inorder(int root, int idx) {
        if (lft[root] != -1) {
            idx = inorder(lft[root], idx);
        }
        pw.print(root);
        idx++;
        if (idx < n) {
            pw.print(" ");
        }
        if (rht[root] != -1) {
            idx = inorder(rht[root], idx);
        }
        return idx;
    }

    static void solve() throws IOException {
        n = Integer.parseInt(nextLine());
        lft = new int[n];
        rht = new int[n];
        Arrays.fill(lft, -1);
        Arrays.fill(rht, -1);

        boolean vis[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            String s = nextLine();
            String ss[] = s.split(" ");
            int l = -1, r = -1;
            if (!ss[0].equals("-")) {
                r = Integer.parseInt(ss[0]);
            }
            if (!ss[1].equals("-")) {
                l = Integer.parseInt(ss[1]);
            }
            lft[i] = l;
            rht[i] = r;
            if (l >= 0) vis[l] = true;
            if (r >= 0) vis[r] = true;
        }
        int root = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                root = i;
                break;
            }
        }
        level(root);
        pw.println();
        inorder(root, 0);
        pw.println();
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
