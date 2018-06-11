package nine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/8 11:05
 * @see nine
 */
public class Uva437 {

    private static class Block {
        int max;
        int min;
        int h;

        Block(int min, int max, int h) {
            this.min = min;
            this.max = max;
            this.h = h;
        }

        boolean contain(Block b) {
            return max > b.max && min > b.min;
        }
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        int C = 0;
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }

            C++;
            List<Block> blocks = new ArrayList<>();
            while (n-- > 0) {
                int dim[] = new int[3];
                dim[0] = sc.nextInt();
                dim[1] = sc.nextInt();
                dim[2] = sc.nextInt();
                Arrays.sort(dim);
                blocks.add(new Block(dim[0], dim[1], dim[2]));
//                if (dim[1] < dim[2]) {
                    blocks.add(new Block(dim[1], dim[2], dim[0]));
//                }
//                if (dim[1] > dim[0]) {
                    blocks.add(new Block(dim[0], dim[2], dim[1]));
//                }
            }
            blocks.sort((o1, o2) -> (o1.max != o2.max ? o1.max - o2.max : (o1.min != o2.min ? o1.min - o2.min : 0)));

            int dp[] = new int[blocks.size()];
            for (int i = blocks.size() - 1; i >= 0; i--) {
                dp[i] = blocks.get(i).h;
                for (int j = i + 1; j < blocks.size(); j++) {
                    if (blocks.get(j).contain(blocks.get(i))) {
                        dp[i] = Math.max(dp[i], dp[j] + blocks.get(i).h);
                    }
                }
            }
            int max = 0;
            for (int i = 0; i < dp.length; i++) {
                max = Math.max(max, dp[i]);
            }
            System.out.println("Case " + C +": maximum height = " + max);
        }
    }
}
