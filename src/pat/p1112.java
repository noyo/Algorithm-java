package pat;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1112 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        int k = Integer.parseInt(nextLine());
        char ch[] = nextLine().toCharArray();
        int n = ch.length;

        int cnt[] = new int[n + 1];
        Set<Character> error = new HashSet<>();
        Set<Character> right = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i <= n; i++) {
            cnt[i] = 1;
            if (i > 0) {
                if (i < n && ch[i] == ch[i - 1]) {
                    cnt[i] += cnt[i - 1];
                } else {
                    if (cnt[i - 1] % k != 0) {
                        right.add(ch[i - 1]);
                    }
                }
            }
        }
        for (int i = 0; i < n; ) {
            if (right.contains(ch[i])) {
                sb2.append(ch[i++]);
            } else {
                if (!error.contains(ch[i])) {
                    error.add(ch[i]);
                    sb.append(ch[i]);
                }
                sb2.append(ch[i]);
                i += k;
            }
        }
        pw.println(sb.toString());
        pw.println(sb2.toString());
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
