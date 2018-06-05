package eight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/4 23:48
 * @see eight
 */
public class Uva12174 {


    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            sc.nextLine();
            int s = sc.nextInt();
            int n = sc.nextInt();
            if (s <= 1) {
                sc.nextLine();
                System.out.println(s);
                continue;
            }
            int last[] = new int[s + 1];
            int pre[] = new int[n + 1];
            int notOk[] = new int[s + 1];
            int start = -s;
            boolean flag = true;

            Arrays.fill(pre, - 1);
            Arrays.fill(last, - 1);
            for (int i = 0; i < n; i++) {
                int num = sc.nextInt();
                int len = i - start;
                pre[i] = last[num];

                if (pre[i] == -1 || len == s && pre[i] < start) {
                    start++;
                } else {
                    if (pre[pre[i]] >= 0 && i - pre[pre[i]] <= s) {
                        flag = false;
                        break;
                    }
                    for (int j = i - s + 1; j <= pre[i]; j++) {
                        notOk[((j + s) % s) + 1] = 1;
                    }
                    start = pre[i] + 1;
                }
                last[num] = i;
            }
            if (!flag) {
                System.out.println(0);
                continue;
            }
            int min = s;
            for (int i = 1; i <= s; i++) {
                min -= notOk[i];
            }
            System.out.println(min);
        }
    }
}
