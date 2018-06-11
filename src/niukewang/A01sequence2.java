package niukewang;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/8 20:05
 * @see niukewang
 */
public class A01sequence2 {


    private static class Tree {
        StringBuilder num = new StringBuilder();
        int cnt = 0;
        int i = -1;
        int left;
        int right;

        Tree(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "[" + left + ", " + right + "]";
        }

        static void updateDown(Map<Integer, Tree> lineTree, int k) {
            lineTree.get(k).num.append(lineTree.get(k >> 1).num);
            lineTree.get(k).num.append(lineTree.get(k << 1 | 1).num);
        }

        static void updateUp(Map<Integer, Tree> lineTree, int k, boolean flag) {
            lineTree.get(k).num.delete(0, lineTree.get(k).num.length());
            lineTree.get(k).num.append(lineTree.get(k << 1).num);
            lineTree.get(k).num.append(lineTree.get(k << 1 | 1).num);
            lineTree.get(k).cnt = lineTree.get(k << 1).cnt + lineTree.get(k << 1 | 1).cnt;
            lineTree.get(k).cnt += count(lineTree.get(k << 1).num.toString(), lineTree.get(k << 1 | 1).num.toString());
            if (flag && k > 1) {
                updateUp(lineTree, k >> 1, true);
            }
        }

        private static int mod(int i) {
            if (i <= 1) {
                return (int) (Math.pow(2, i) % 3);
            }
            int tmp = i % 2 == 1 ? 2 : 1;
            return tmp * (mod(i >> 1) * mod(i >> 1)) % 3;
        }

        private static int count(String l, String r) {
            char left[] = (" " + l).toCharArray();
            char right[] = (" " + r).toCharArray();
            int cnt = 0;
            int dp[][] = new int[left.length + 1][right.length + 1];
            for (int i = left.length - 1; i >= 1; i--) {
                if (left[i] == '1') {
                    dp[i][0] = mod(left.length - i - 1);
                }
                dp[i][0] += dp[i + 1][0];
                for (int j = 1; j < right.length; j++) {
                    dp[i][j] = ((dp[i][j - 1] << 1) + right[j] - '0') % 3;
                    if (dp[i][j] == 0) {
                        cnt++;
                    }
                }
            }
            return cnt;
        }

        static void update(int l, int r, int i, Map<Integer, Tree> lineTree, int a[], int k) {
            if (l == r) {
                lineTree.get(k).num.replace(0, 1, a[i] + "");
                lineTree.get(k).cnt = a[l] == 0 ? 1 : 0;
                if (k > 1) {
                    updateUp(lineTree, k >> 1, true);
                }
                return;
            }
            int mid = l + (r - l) / 2;
            if (i <= mid) {
                update(l, mid, i, lineTree, a, k << 1);
            } else {
                update(mid + 1, r, i, lineTree, a, k << 1 | 1);
            }
        }

        static void build(int l, int r, Map<Integer, Tree> lineTree, int a[], int k) {
            lineTree.put(k, new Tree(l, r));
            if (l == r) {
                lineTree.get(k).cnt = a[l] == 0 ? 1 : 0;
                lineTree.get(k).num.append(a[l]);
                return;
            }
            int mid = l + (r - l) / 2;
            build(l, mid, lineTree, a, k << 1);
            build(mid + 1, r, lineTree, a, k << 1 | 1);
            updateUp(lineTree, k, false);
        }

        static int query(int L, int R, int l, int r, Map<Integer, Tree> lineTree, int k) {
            if (L <= l && r <= R) {
                return lineTree.get(k).cnt;
            }
            if (r < L || l > R) {
                return 0;
            }
            int cnt = 0;
            int mid = l + (r - l) / 2;
            cnt += query(L, R, l, mid, lineTree, k << 1);
            cnt += query(L, R, mid + 1, r, lineTree, k << 1 | 1);
            if (mid + 1 - Math.max(l, L) > 0 && mid < Math.min(r, R) && Math.min(r, R) <= lineTree.get(k).num.length()) {
                String s1 = lineTree.get(k).num.substring(Math.max(l, L) - 1, mid);
                String s2 = lineTree.get(k).num.substring(mid, Math.min(r, R));
                cnt += count(s1, s2);
            }
            return cnt;
        }
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        Map<Integer, Tree> lineTree = new HashMap<>(n << 1);
        int a[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }

        Tree.build(1, n, lineTree, a, 1);

        while (m-- > 0) {
            int op = sc.nextInt();
            if (op == 1) {
                int i = sc.nextInt();
                a[i] = 1 - a[i];
                Tree.update(1, n, i, lineTree, a, 1);
            } else {
                int l = sc.nextInt();
                int r = sc.nextInt();
                if (l < 1) {
                    l = 1;
                }
                if (r > n) {
                    r = n;
                }
                if (l > r) {
                    System.out.println(0);
                } else {
                    System.out.println(Tree.query(l, r, 1, n, lineTree, 1));
                }
            }
        }
    }
}
