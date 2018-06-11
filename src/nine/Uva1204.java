package nine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/9 9:41
 * @see nine
 */
public class Uva1204 {
    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (0 == n) {
                break;
            }
            int dp[] = new int[n * 100];
            int len = 0;
            int cur = 0;
            char last[] = null;
            for (int i = 0; i < n; i++) {
                char ch[] = sc.next().toCharArray();
                int recur = 0;
                int start = 0;
                for (int j = 0; j < ch.length; j++) {
                    if (len == 0) {
                        dp[cur++] = ch[j];
                        len++;
                        continue;
                    }
                    if (null != last && j - start < last.length) {
                        if (j >= start && ch[j] != last[j - start]) {
                            start = j - start;
                            if (ch[j] != last[j - start]) {
                                j++;
                            }
                        }
                    }
                    if (ch[j] != dp[recur]) {
                        len++;
                        recur = 0;
                    }
                    if (ch[j] == dp[recur]){
                        recur = (recur + 1) % len;
                    }
                    dp[cur++] = ch[j];
                }
                cur = len;

                last = ch;
            }
        }
    }
}
