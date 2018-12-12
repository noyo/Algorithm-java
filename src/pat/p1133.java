package pat;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1133 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        int link[] = new int[100001];
        Arrays.fill(link, -1);
        Map<Integer, Integer> val = new HashMap<>();
        int first = nextInt();
        int n = nextInt();
        int k = nextInt();
        for (int i = 0; i < n; i++) {
            int from = nextInt();
            int v = nextInt();
            int next = nextInt();
            val.put(from, v);
            link[from] = next;
        }
        int ans1[] = new int[100001];
        int ans2[] = new int[100001];
        int ans3[] = new int[100001];
        Arrays.fill(ans1, -1);
        Arrays.fill(ans2, -1);
        Arrays.fill(ans3, -1);
        int cur = first;
        int p1 = -1, p2 = -1, p3 = -1;
        int s1 = -1, s2 = -1, s3 = -1;
        while (cur != -1) {
            int v = val.get(cur);
            if (v < 0) {
                if (s1 == -1) {
                    s1 = cur;
                } else {
                    ans1[p1] = cur;
                }
                p1 = cur;
            } else if (v <= k) {
                if (s2 == -1) {
                    s2 = cur;
                } else {
                    ans2[p2] = cur;
                }
                p2 = cur;
            } else {
                if (s3 == -1) {
                    s3 = cur;
                } else {
                    ans3[p3] = cur;
                }
                p3 = cur;
            }
            cur = link[cur];
        }
        int cnt = 0;
        while (s1 != -1) {
            cnt++;
            int next = ans1[s1];
            if (next == -1) {
                if (cnt < n) {
                    if (s2 != -1) {
                        next = s2;
                    } else {
                        next = s3;
                    }
                }
            }
            if (next == -1) {
                pw.format("%05d %d %d\n", s1, val.get(s1), next);
            } else
            pw.format("%05d %d %05d\n", s1, val.get(s1), next);
            s1 = ans1[s1];
        }
        while (s2 != -1) {
            cnt++;
            int next = ans2[s2];
            if (next == -1 && cnt < n) {
                next = s3;
            }
            if (next == -1) {
                pw.format("%05d %d %d\n", s2, val.get(s2), next);
            } else
                pw.format("%05d %d %05d\n", s2, val.get(s2), next);
            s2 = ans2[s2];
        }
        while (s3 != -1) {
            if (ans3[s3] == -1) {
                pw.format("%05d %d %d\n", s3, val.get(s3), s3 = ans3[s3]);
            } else
                pw.format("%05d %d %05d\n", s3, val.get(s3), s3 = ans3[s3]);
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
