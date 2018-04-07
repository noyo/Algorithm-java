/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 */
package six;

import util.IOUtils;

import java.util.List;

public class Uva679_6_6 {

    List<String> data = IOUtils.readFromFile("6/6/in.txt");
    int tree[];

    private int dfs(int deep) {
        int index = 1;
        int val;
        for (int i = 1; i < deep; i++) {
            val = tree[index];
            tree[index] = 1 - val;
            index = index * 2 + val;
        }
        return index;
    }

    private void init(String[] ss) {

        int deep = Integer.parseInt(ss[0]);
        tree = new int[(1 << deep) + 1];
        int a = Integer.parseInt(ss[1]);
        for (int i = 1; i < a; i++) {
            dfs(deep);
        }
        System.out.println(dfs(deep));
    }

    public static void main(String args[]) {

        Uva679_6_6 uva = new Uva679_6_6();
        for (String s : uva.data) {
            uva.init(s.split(" "));
        }
    }
}
