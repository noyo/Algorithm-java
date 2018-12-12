package pat;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1092 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        char ch[] = nextLine().toCharArray();
        Map<Character, Integer> cnt = new HashMap<>();
        for (int i = 0; i < ch.length; i++) {
            cnt.put(ch[i], cnt.getOrDefault(ch[i], 0) + 1);
        }
        char t[] = nextLine().toCharArray();
        boolean ok = true;
        int k = 0;
        for (int i = 0; i < t.length; i++) {
            cnt.put(t[i], cnt.getOrDefault(t[i], 0) - 1);
            if (cnt.get(t[i]) < 0) {
                ok = false;
                k++;
            }
        }
        if (ok) {
            pw.println("Yes " + (ch.length - t.length));
        } else {
            pw.println("No " + k);
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
