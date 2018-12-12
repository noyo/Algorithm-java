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
public class p1089 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void merge(int a[], int len) {
        for (int i = 0; i < a.length; i += len << 1) {
            if (i + len >= a.length) {
                break;
            }
            int ll = i + len;
            int rl = Math.min(a.length, i + (len << 1));
            int l = i, r = i + len;
            int tmp[] = new int[rl - i];
            int idx = 0;
            while (l < ll || r < rl) {
                if (r >= rl || l < ll && a[l] <= a[r]) {
                    tmp[idx] = a[l++];
                } else {
                    tmp[idx] = a[r++];
                }
                idx++;
            }

            for (int j = i; j < i + tmp.length; j++) {
                a[j] = tmp[j - i];
            }
        }
    }

    static void solve() throws IOException {
        int n = nextInt();
        int s[] = new int[n];
        int t[] = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = nextInt();
        }
        for (int i = 0; i < n; i++) {
            t[i] = nextInt();
        }
        boolean insert = true;
        int i = n - 1;
        for (; i >= 0; i--) {
            if (t[i] != s[i]) {
                break;
            }
        }
        for (int j = 1; j < n; j++) {
            if (t[j] < t[j - 1]) {
                insert = j > i;
                i = j;
                break;
            }
        }
        List<Integer> ans = new ArrayList<>();
        if (insert) {
            pw.println("Insertion Sort");
            int j = 0;
            for (; j < n; j++) {
                if (t[j] < t[i]) {
                    ans.add(t[j]);
                } else {
                    break;
                }
            }
            ans.add(t[i]);
            for (; j < i; j++) {
                ans.add(t[j]);
            }
            j = i + 1;
            for (; j < n; j++) {
                ans.add(t[j]);
            }
        } else {
            pw.println("Merge Sort");
            int cur = 1;
            while (true) {
                merge(s, cur);
                boolean equal = true;
                for (int j = 0; j < n; j++) {
                    if (s[j] != t[j]) {
                        equal = false;
                        break;
                    }
                }
                cur <<= 1;
                if (equal) {
                    merge(s, cur);
                    break;
                }
            }
            for (int j = 0; j < n; j++) {
                ans.add(s[j]);
            }
        }
        for (int j = 0; j < n; j++) {
            pw.print(ans.get(j));
            if (j < n - 1) {
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
