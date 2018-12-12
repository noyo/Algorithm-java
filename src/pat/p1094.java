package pat;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1094 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        int n = nextInt();
        int m = nextInt();
        List<Integer> children[] = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            children[i] = new ArrayList<>();
        }
        boolean vis[] = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            int p = nextInt();
            int k = nextInt();
            for (int j = 0; j < k; j++) {
                int a = nextInt();
                children[p].add(a);
                vis[a] = true;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                queue.offer(i);
            }
        }
        int level = 0;
        int max = 0;
        int lvl = 0;
        while (!queue.isEmpty()) {
            int x = queue.size();
            lvl++;
            if (x > max) {
                max = x;
                level = lvl;
            }
            max = Math.max(max, x);
            while (x-- > 0) {
                int q = queue.poll();
                for (int key : children[q]) {
                    queue.offer(key);
                }
            }
        }
        pw.println(max + " " + level);
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
