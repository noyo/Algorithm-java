package pat;

import java.io.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1136 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static StringBuilder sb;

    static boolean isPal(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    static String add(String s) {
        int len = s.length();
        char ans[] = new char[len + 1];
        int idx = len;
        int r = 0;
        int i = 0, j = len - 1;
        for (; j >= 0; j--, i++) {
            ans[idx--] = (char) ((s.charAt(j) - '0' + s.charAt(i) - '0' + r) % 10 + '0');
            r = (s.charAt(j) - '0' + s.charAt(i) - '0' + r) / 10;
        }
        if (r > 0) {
            ans[0] = (char) (r + '0');
            return String.valueOf(ans);
        } else {
            return String.valueOf(ans, 1, len);
        }
    }

    static void solve() throws IOException {
        sb = new StringBuilder(nextLine());
        for (int i = 0; i < 10; i++) {
            if (isPal(sb.toString())) {
                break;
            }
            String tmp = add(sb.toString());
            pw.format("%s + %s = %s\n", sb.toString(), sb.reverse(), tmp);
            sb.delete(0, sb.length());
            sb.append(tmp);
        }
        if (!isPal(sb.toString())) {
            pw.format("Not found in 10 iterations.");
        } else {
            pw.format("%s is a palindromic number.", sb.toString());
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
