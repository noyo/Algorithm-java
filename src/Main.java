import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: PACKAGE_NAME
 * author: Chris
 * date: 2018/5/22 15:40
 */
public class Main {

    private static int cmp(int sum[], int l1, int r1, int l2, int r2) {
        return (sum[r1] - sum[l1 - 1]) * (r2 - l2 + 1) - (sum[r2] - sum[l2 - 1]) * (r1 - l1 + 1);
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        while (m-- > 0) {
            int n = sc.nextInt();
            int L = sc.nextInt();
            char[] ch = (" " + sc.next()).toCharArray();
            int sum[] = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                sum[i] = sum[i - 1] + ch[i] - '0';
            }

            int low = 1;
            int high = L;
            int bot = 0;
            int top = 0;
            int p[] = new int[n + 1];
            for (int i = L; i <= n; i++) {
                while (top - bot > 1 && cmp(sum, p[top - 2], i - L, p[top - 1], i - L) > 0) {
                    top--;
                }
                p[top++] = i - L + 1;
                while (top - bot > 1 && cmp(sum, p[bot], i, p[bot + 1], i) <= 0) {
                    bot++;
                }
                int cmp = cmp(sum, p[bot], i, low, high);
                if (cmp > 0 || (cmp == 0 && i - p[bot] < high - low)) {
                    low = p[bot];
                    high = i;
                }
            }
            System.out.println(low + " " + high);
        }
    }
}
