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
public class p1057 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int n;
    static Stack<Integer> tack = new Stack<>();
    static int cnt[] = new int[100001];
    static int C[] = new int[100001];

    static int lowbit(int x) {
        return x & (-x);
    }

    static void add(int x, int c) {
        C[x] += c;
        while (x + lowbit(x) <= 100000) {
            x += lowbit(x);
            C[x] += c;
        }
    }

    static int getSum(int x) {
        int sum = C[x];
        while (x - lowbit(x) > 0) {
            x -= lowbit(x);
            sum += C[x];
        }
        return sum;
    }

    static int getMid(int siz) {
        int low = 1;
        int high = 100000;
        while (low < high) {
            int mid = (low + high) / 2;
            if (getSum(mid) < siz) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    static void solve() throws IOException {
        n = nextInt();
        while (n-- > 0) {
            String s = nextLine();
            switch (s.charAt(1)) {
                case 'o':
                    if (tack.empty()) {
                        pw.format("Invalid\n");
                    } else {
                        pw.format("%d\n", tack.peek());
                        add(tack.pop(), -1);
                    }
                    break;
                case 'u':
                    int num = Integer.parseInt(s.substring(s.lastIndexOf(" ") + 1));
                    tack.push(num);
                    add(num, 1);
                    break;
                case 'e':
                    if (tack.empty()) {
                        pw.format("Invalid\n");
                    } else {
                        pw.format("%d\n", getMid((tack.size() + 1) / 2));
                    }
                    break;
                default:
                    pw.format("Invalid\n");
                    break;
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
