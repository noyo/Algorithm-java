package format;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/12 16:11
 * @see format
 */
public class KMP {

    private static int[] next(String s) {
        int n = s.length();

        int[] next = new int[n];
        next[0] = -1;

        int i = 0;
        int k = -1;
        while (i < s.length() - 1) {
            if (k == -1 || s.charAt(i) == s.charAt(k)) {
                next[++i] = ++k;
            } else {
                k = next[k];
            }
        }

        return next;
    }

    private static int kmp(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();
        int next[] = next(t);

        int i = 0;
        int j = 0;
        while (j < n2 && i + n2 - j <= n1) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
                if (j < 0) {
                    i++;
                    j = 0;
                }
            }
        }

        if (j < n2) {
            return -1;
        }
        return i - j;
    }
}
