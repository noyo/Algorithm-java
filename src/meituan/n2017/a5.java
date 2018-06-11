package meituan.n2017;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/6 16:24
 * @see meituan.n2017
 */
public class a5 {

    private static class Tree {
        Map<Integer, Integer> cnt = new HashMap<>();
        int left;
        int right;
        Tree (int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "[" + left + ", " + right + "]";
        }

        static void add(Map<Integer, Integer> source, Map<Integer, Integer> target) {
            for (int key : source.keySet()) {
                target.put(key, source.get(key) + target.getOrDefault(key, 0));
            }
        }

        static void updateUp(Map<Integer, Tree> lineTree, int k) {
            for (int key : lineTree.get(k << 1).cnt.keySet()) {
                lineTree.get(k).cnt.put(key, lineTree.get(k << 1).cnt.get(key));
            }
            for (int key : lineTree.get(k << 1 | 1).cnt.keySet()) {
                lineTree.get(k).cnt.put(key, lineTree.get(k << 1 | 1).cnt.get(key) + lineTree.get(k).cnt.getOrDefault(key, 0));
            }
        }

        static void update(int L, int R, int l, int r, Map<Integer, Tree> lineTree, int k) {

        }

        static void build(int l, int r, Map<Integer, Tree> lineTree, int a[], int k) {
            lineTree.put(k, new Tree(l, r));
            if (l == r) {
                lineTree.get(k).cnt.put(a[l], 1);
                return;
            }
            int mid = l + (r - l) / 2;
            build(l, mid, lineTree, a, k << 1);
            build(mid + 1, r, lineTree, a, k << 1 | 1);
            updateUp(lineTree, k);
        }

        static void query(int L, int R, int l, int r, Map<Integer, Tree> lineTree, int k, Map<Integer, Integer> cnt) {
            if (L <= l && r <= R) {
                add(lineTree.get(k).cnt, cnt);
                return;
            }
            if (r < L || l > R) {
                return;
            }
            int mid = l + (r - l) / 2;
            query(L, R, l, mid, lineTree, k << 1, cnt);
            query(L, R, mid + 1, r, lineTree, k << 1 | 1, cnt);
        }
    }

    private static boolean prime(int a, int b) {
        if (a < b) {
            a ^= b;
            b ^= a;
            a ^= b;
        }
        int r = b;
        while (a % b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return r == 1;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int a[] = new int[n + 1];
        Map<Integer, Tree> lineTree = new HashMap<>(n << 1);

        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }

        Tree.build(1, n, lineTree, a, 1);

        for (int i = 1; i <= m; i++) {
            Map<Integer, Integer> cnt = new HashMap<>();
            Tree.query(sc.nextInt(), sc.nextInt(), 1, n, lineTree, 1, cnt);
            int k = sc.nextInt();
            int result = 0;
            for (int key : cnt.keySet()) {
                if (prime(cnt.get(key), k)) {
                    result++;
                }
            }
            System.out.println(result);
        }
    }
}
