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
public class p1130 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static class TreeNode {
        String val;
        int left;
        int right;
        
        TreeNode(String s, int l, int r) {
            val = s;
            left = l;
            right = r;
        }
    }

    static Map<Integer, TreeNode> map = new HashMap<>();

    static StringBuilder dfs(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        boolean al = root.left != -1 || root.right != -1;
        if (al) {
            sb.append("(");
        }
        if (root.left != -1) {
            sb.append(dfs(map.get(root.left)));
        }
        sb.append(root.val);
        if (root.right != -1) {
            sb.append(dfs(map.get(root.right)));
        }
        if (al) {
            sb.append(")");
        }
        return sb;
    }

    static void solve() throws IOException {
        int n = nextInt();
        if (n == 1) {
            pw.println(next(10));
            return;
        }
        int p[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            String val = next(10);
            int l = nextInt();
            int r = nextInt();
            TreeNode node = new TreeNode(val, l, r);
            map.put(i, node);
            if (-1 != l) {
                p[l] = i;
            }
            if (-1 != r) {
                p[r] = i;
            }
        }
        int root = 1;
        for (int i = 1; i <= n; i++) {
            if (p[i] == 0) {
                root = i;
                break;
            }
        }
        StringBuilder sb = dfs(map.get(root));
        pw.println(sb.substring(1, sb.length() - 1));
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
