package pat;

import java.io.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1066 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static TreeNode root;

    static class TreeNode {
        int val, lR;
        TreeNode lft, rht;

        TreeNode(int v) {
            val = v;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", lR=" + lR +
                    ", lft=" + lft +
                    ", rht=" + rht +
                    '}';
        }
    }

    static void insert(TreeNode p, int val) {
        if (p.val < val) {
            if (null == p.rht) {
                p.rht = new TreeNode(val);
            } else {
                insert(p.rht, val);
            }
        } else {
            if (null == p.lft) {
                p.lft = new TreeNode(val);
            } else {
                insert(p.lft, val);
            }
        }
    }

    static void lftRotate(TreeNode node, TreeNode p, boolean lft) {
        TreeNode r = node.rht;
        if (null == p) {
            root = r;
        } else if (lft) {
            p.lft = r;
        } else {
            p.rht = r;
        }
        node.rht = r.lft;
        r.lft = node;
    }

    static void rhtRotate(TreeNode node, TreeNode p, boolean lft) {
        TreeNode l = node.lft;
        if (null == p) {
            root = l;
        } else if (lft) {
            p.lft = l;
        } else {
            p.rht = l;
        }
        node.lft = l.rht;
        l.rht = node;
    }

    static int balance(TreeNode node, TreeNode p, boolean lft) {
        if (null == node) {
            return 0;
        }
        int L = balance(node.lft, node, true);
        int R = balance(node.rht, node, false);
        if (L == -1 || R == -1) {
            return -1;
        }
        if (Math.abs(L - R) == 2) {
            if (L < R) {
                if (node.rht.lR > 0) {
                    rhtRotate(node.rht, node, false);
                }
                lftRotate(node, p, lft);
            } else {
                if (node.lft.lR < 0) {
                    lftRotate(node.lft, node, true);
                }
                rhtRotate(node, p, lft);
            }
            return -1;
        }
        node.lR = L - R;
        return Math.max(L, R) + 1;
    }

    static void solve() throws IOException {
        int n = nextInt();
        root = new TreeNode(nextInt());
        for (int i = 1; i < n; i++) {
            insert(root, nextInt());
            balance(root, null, false);
        }

        pw.println(root.val);
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
