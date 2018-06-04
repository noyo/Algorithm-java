package eight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
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
            int start = -s + 1;
            int min = s;
            for (int i = 1; i <= n; i++) {
                int num = sc.nextInt();
                int len = i - start + 1;
                pre[i] = last[num];

                if (pre[i] == 0 || pre[i] < start) {
                    if (len == s) {
                        start++;
                    }
                } else {
                    if (start > 0 && (pre[pre[i]] > 0 && i - pre[pre[i]] <= s)) {
                        min = 0;
                        break;
                    }
                    min = Math.min(min, pre[i] + 1 - start);
                    start = pre[i] + 1;
                }
                last[num] = i;
            }
            System.out.println(min);
        }
    }
}
