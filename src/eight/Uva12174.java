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
            int last[] = new int[s + 1];
            int pre[] = new int[n + 1];
            int ok[] = new int[s];
            int start = -s;
            boolean flag = true;

            Arrays.fill(pre, - 1);
            for (int i = 0; i < n; i++) {
                int num = sc.nextInt();
                int len = i - start + 1;
                pre[i] = last[num];

                if (pre[i] == -1 || pre[i] < start) {
                    if (len == s) {
                        start++;
                    }
                } else {
                    if (pre[pre[i]] >= 0 && i - pre[pre[i]] <= s) {
                        flag = false;
                        break;
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
            for (int i = 0; i < s; i++) {

            }
            System.out.println(min);
        }
    }
}
