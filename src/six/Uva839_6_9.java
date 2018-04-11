package six;

import util.IOUtils;

import java.util.List;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: six
 * author: Chris
 * date: 2018/4/11 14:45
 */
public class Uva839_6_9 {

    private List<String> data = IOUtils.readFromFile("6/9/in.txt");
    private int index = 0;
    private String in[][];

    private boolean isBalance(CustInteger w) {
        boolean leftBal = true, rightBal = true;
        int w1, w2;
        String t[] = in[index];
        w1 = Integer.parseInt(t[0]);
        w2 = Integer.parseInt(t[2]);
//        int tmp = index;
        index++;
        CustInteger left = new CustInteger(w1);
        CustInteger right = new CustInteger(w2);
//        System.out.println(tmp + " " + left.val + d1 + right.val + d2);
        if (0 == w1) leftBal = isBalance(left);
        if (0 == w2) rightBal = isBalance(right);
//        System.out.println(tmp + " " + left.val + d1 + right.val + d2);
        w.val = left.val + right.val;
        return leftBal && rightBal && (left.val * Integer.parseInt(t[1]) == right.val * Integer.parseInt(t[3]));
    }

    public static void main(String args[]) {
        Uva839_6_9 uva = new Uva839_6_9();
        int size = uva.data.size();
        uva.in = new String[size][];
        for (int i = 0; i < size; i++) {
            uva.in[i] = uva.data.get(i).split(" ");
        }
        System.out.println(uva.isBalance(new CustInteger(0)));
    }

    static class CustInteger {
        int val;

        CustInteger(int val) {
            this.val = val;
        }
    }
}
