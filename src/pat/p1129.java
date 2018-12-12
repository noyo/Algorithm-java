package pat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1129 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        int n = nextInt();
        int k = nextInt();
        int a = nextInt();
        int cnt[] = new int[n + 1];
        cnt[a]++;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> cnt[o1] == cnt[o2] ? o1 - o2 : cnt[o2] - cnt[o1]);
        queue.offer(a);
        for (int i = 1; i < n; i++) {
            a = nextInt();
            List<Integer> tmp = new ArrayList<>();
            pw.print(a + ":");
            for (int j = 0; j < k && !queue.isEmpty(); j++) {
                tmp.add(queue.peek());
                pw.print(" " + queue.poll());
            }
            pw.println();
            cnt[a]++;
            queue.addAll(tmp);
            queue.remove(a);
            queue.offer(a);
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
