package pat;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1123 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static TreeNode root;

    static class TreeNode {
        int val;
        int lR;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", lR=" + lR +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    static void insert(TreeNode root, int x) {
        if (x < root.val) {
            if (null == root.left) {
                root.left = new TreeNode(x);
            } else {
                insert(root.left, x);
            }
        } else {
            if (null == root.right) {
                root.right = new TreeNode(x);
            } else {
                insert(root.right, x);
            }
        }
    }

    static void leftRotate(TreeNode node, TreeNode p, boolean left) {
        TreeNode r = node.right;
        if (null != p) {
            if (left) {
                p.left = r;
            } else {
                p.right = r;
            }
        } else {
            root = r;
        }
        node.right = r.left;
        r.left = node;
    }

    static void rightRotate(TreeNode node, TreeNode p, boolean left) {
        TreeNode l = node.left;
        if (null != p) {
            if (left) {
                p.left = l;
            } else {
                p.right = l;
            }
        } else {
            root = l;
        }
        node.left = l.right;
        l.right = node;
    }

    static int balance(TreeNode node, TreeNode p, boolean left) {
        if (null == node) {
            return 0;
        }
        int L = balance(node.left, node, true);
        int R = balance(node.right, node, false);
        if (L == -1 || R == -1) {
            return -1;
        }
        if (Math.abs(L - R) == 2) {
            if (L > R) {
                if (node.left.lR < 0) {
                    leftRotate(node.left, node, true);
                }
                rightRotate(node, p, left);
            } else {
                if (node.right.lR > 0) {
                    rightRotate(node.right, node, false);
                }
                leftRotate(node, p, left);
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
            balance(root, null, true);
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean ok = true;
        boolean finished = false;
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int x = queue.size();
            while (x-- > 0) {
                TreeNode node = queue.poll();
                sb.append(node.val).append(" ");
                if (null != node.left) {
                    if (finished) {
                        ok = false;
                    }
                    queue.offer(node.left);
                } else {
                    finished = true;
                }
                if (null != node.right) {
                    if (finished) {
                        ok = false;
                    }
                    queue.offer(node.right);
                } else {
                    finished = true;
                }
            }
        }

        pw.println(sb.toString().trim());
        if (ok) {
            pw.println("YES");
        } else {
            pw.println("NO");
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
