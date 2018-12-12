package pat;

import java.io.*;
import java.util.Stack;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1051 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        int m = nextInt();
        int n = nextInt();
        int k = nextInt();
        int a[] = new int[n];
        Stack<Integer> stack = new Stack<>();
        while (k-- > 0) {
            stack.clear();
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            boolean ok = true;
            int idx = 1;
            for (int i = 0; i < n; i++) {
                if (stack.empty() || stack.peek() != a[i]) {
                    while (idx <= n && idx != a[i]) {
                        stack.push(idx++);
                        if (stack.size() == m) {
                            ok = false;
                            break;
                        }
                    }
                    if (idx > n) {
                        ok = false;
                        break;
                    }
                    idx++;
                } else {
                    stack.pop();
                }
            }
            if (ok) {
                pw.println("YES");
            } else {
                pw.println("NO");
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
