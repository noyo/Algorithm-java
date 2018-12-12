package pat;

import java.io.*;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1120 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int count(int num) {
        int cnt = 0;
        while (num > 0) {
            cnt += num % 10;
            num /= 10;
        }
        return cnt;
    }

    static void solve() throws IOException {
        int n = nextInt();
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = count(nextInt());
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        for (int key : cnt.keySet()) {
            if (cnt.get(key) > 1) {
            }
            ans.add(key);
        }
        ans.sort(Comparator.naturalOrder());
        pw.println(ans.size());
        pw.print(ans.get(0));
        for (int i = 1; i < ans.size(); i++) {
            pw.print(" " + ans.get(i));
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
