package pat;

import java.io.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1088 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static long gcd(long a, long b) {
        if (a < b) {
            a ^= b;
            b ^= a;
            a ^= b;
        }
        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    static void solve() throws IOException {
        String ss[] = nextLine().split(" ");
        long a = Integer.parseInt(ss[0].substring(0, ss[0].indexOf("/")));
        long b = Integer.parseInt(ss[0].substring(ss[0].indexOf("/") + 1, ss[0].length()));
        long c = Integer.parseInt(ss[1].substring(0, ss[1].indexOf("/")));
        long d = Integer.parseInt(ss[1].substring(ss[1].indexOf("/") + 1, ss[1].length()));
        if (a != 0) {
            long gcd1 = gcd(a, b);
            a /= gcd1;
            b /= gcd1;
        }
        if (c != 0) {
            long gcd2 = gcd(c, d);
            c /= gcd2;
            d /= gcd2;
        }
        printAdd(a, b, c, d);
        printSub(a, b, c, d);
        printMul(a, b, c, d);
        printDiv(a, b, c, d);
    }

    private static void printAdd(long a, long b, long c, long d) {
        printNum(a, b);
        pw.print(" + ");
        printNum(c, d);
        pw.print(" = ");

        long top = a * d + c * b;
        long bot = b * d;
        long gcd = gcd(top, bot);
        top /= gcd;
        bot /= gcd;
        printNum(top, bot);

        pw.println();
    }

    private static void printSub(long a, long b, long c, long d) {
        printNum(a, b);
        pw.print(" - ");
        printNum(c, d);
        pw.print(" = ");

        long top = a * d - c * b;
        long bot = b * d;
        long gcd = gcd(top, bot);
        top /= gcd;
        bot /= gcd;
        printNum(top, bot);

        pw.println();
    }

    private static void printMul(long a, long b, long c, long d) {
        printNum(a, b);
        pw.print(" * ");
        printNum(c, d);
        pw.print(" = ");

        long top = a * c;
        long bot = b * d;
        long gcd = gcd(top, bot);
        top /= gcd;
        bot /= gcd;
        printNum(top, bot);

        pw.println();
    }

    private static void printDiv(long a, long b, long c, long d) {
        printNum(a, b);
        pw.print(" / ");
        printNum(c, d);
        pw.print(" = ");
        if (c == 0) {
            pw.println("Inf");
            return;
        }

        long top = a * d;
        long bot = b * c;
        long gcd = gcd(top, bot);
        top /= gcd;
        bot /= gcd;
        printNum(top, bot);

        pw.println();
    }

    private static void printNum(long a, long b) {
        boolean sign = a * b < 0;
        if (sign) {
            pw.print("(-");
        }
        a = Math.abs(a);
        b = Math.abs(b);
        if (a / b != 0) {
            pw.print(a / b);
            if (a % b != 0) {
                pw.print(" ");
            }
        }
        if (a % b != 0) {
            pw.print(a % b + "/" + b);
        } else if (a / b == 0) {
            pw.print(0);
        }
        if (sign) {
            pw.print(")");
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
