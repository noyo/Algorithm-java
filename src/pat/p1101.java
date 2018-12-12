package pat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1101 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        int n = nextInt();
        int a[] = new int[n];
        int lft[] = new int[n];
        int rht[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
            if (i == 0) {
                lft[i] = a[i];
            } else {
                lft[i] = Math.max(a[i], lft[i - 1]);
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                rht[i] = a[i];
            } else {
                rht[i] = Math.min(a[i], rht[i + 1]);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if ((i == 0 || lft[i - 1] < a[i]) && (i == n - 1 || rht[i + 1] > a[i])) {
                ans.add(i);
            }
        }
        pw.println(ans.size());
        for (int i = 0; i < ans.size(); i++) {
            pw.print(a[ans.get(i)]);
            if (i < ans.size() - 1) {
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
