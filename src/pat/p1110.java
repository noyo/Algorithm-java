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
public class p1110 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        int n = nextInt();
        int p[] = new int[n];
        int lft[] = new int[n];
        int rht[] = new int[n];
        Arrays.fill(p, - 1);
        Arrays.fill(lft, - 1);
        Arrays.fill(rht, - 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                String s = next(2);
                if (s.charAt(0) != '-') {
                    p[Integer.parseInt(s)] = i;
                    if (j == 0) {
                        lft[i] = Integer.parseInt(s);
                    } else {
                        rht[i] = Integer.parseInt(s);
                    }
                }
            }
        }
        int root = 0;
        int last = 0;
        for (int i = 0; i < n; i++) {
            if (p[i] == -1) {
                root = i;
                break;
            }
        }
        boolean ok = true;
        boolean finished = false;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int x =  queue.size();
            while (x-- > 0) {
                int node = queue.poll();
                last = node;
                if (-1 != lft[node]) {
                    queue.offer(lft[node]);
                } else {
                    finished = true;
                }
                if (-1 != rht[node]) {
                    if (finished) ok = false;
                    queue.offer(rht[node]);
                } else {
                    finished = true;
                }
                if (!ok) {
                    x = 0;
                    queue.clear();
                }
            }
        }
        if (ok) {
            pw.print("YES " + last);
        } else {
            pw.print("NO " + root);
        }
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
