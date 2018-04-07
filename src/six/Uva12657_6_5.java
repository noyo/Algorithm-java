/**   
 * Copyright © 2018 Chris. All rights reserved.
 * 
 */
package six;

import java.util.List;

import util.IOUtils;

/**
 * @Description:
 * 
 * @Package: six
 * @author: Chris
 * @date: 2018下午8:16:38
 */
public class Uva12657_6_5 {

	private int preBoxes[];
	private int rearBoxes[];
	private boolean isReverse = false;
	private List<String> data = IOUtils.readFromFile("6/5/in.txt");
	private int n = Integer.parseInt(data.get(0));	
	
	private void print() {
		int i = 0;
		int sum = 0;
		if (isReverse && n % 2 == 0) i = rearBoxes[i];
		do {
			sum += rearBoxes[i];
			i = rearBoxes[rearBoxes[i]];
		} while (i != 0 && rearBoxes[i] != 0);
//		int sum = 0;
//		int rear = 0;
//		for (int i = 1; i <= n; i++) {
//			rear = rearBoxes[rear];
//			if (i % 2 == 1) {
//				sum += rear;
//			}
//		}
//		if (isReverse && n % 2 == 0 ) {
//			sum = (n * (n + 1)) / 2 - sum;
//		}
		System.out.println(sum);
	}
	
	private void link(int x, int y) {
		if (x == y) return;
		rearBoxes[x] = y;
		preBoxes[y] = x;
	}

	private void execCmd(int cmd, int x, int y) {
		switch (cmd) {
		case 1:
			if (rearBoxes[x] != y) {
				link(preBoxes[x], rearBoxes[x]);
				link(preBoxes[y], x);
				link(x, y);
			}
			break;
		case 2:
			if (preBoxes[x] != y) {
				link(preBoxes[x], rearBoxes[x]);
				link(x, rearBoxes[y]);
				link(y, x);
			}
			break;
		case 3:
			if (rearBoxes[y] == x) {
				int tmp = x;
				x = y;
				y = tmp;
			}
			if (rearBoxes[x] == y) {
				link(preBoxes[x], y);
				link(x, rearBoxes[y]);
				link(y, x);
			} else {
				int preY = preBoxes[y];
				int rearY = rearBoxes[y];
				link(preBoxes[x], y);
				link(y, rearBoxes[x]);
				link(preY, x);;
				link(x, rearY);
			}
			break;
		case 4:
			isReverse = !isReverse;
			break;
		}
	}

	private void init() {
		preBoxes = new int[n + 1];
		rearBoxes = new int[n + 1];
		for (int i = 0; i < n;) {
			link(i, ++i);
		}
		rearBoxes[n] = 0;
		preBoxes[0] = n;
	}

	public static void main(String args[]) {

		Uva12657_6_5 uva = new Uva12657_6_5();
		
		uva.init();
		int size = uva.data.size();
		int cmd, x = 0, y = 0;
		for (int i = 1; i < size; i++) {
			String[] ss = uva.data.get(i).split(" ");
			cmd = ss[0].charAt(0) - '0';
			if (ss.length == 3) {
				x = ss[1].charAt(0) - '0';
				y = ss[2].charAt(0) - '0';
			}
			if (uva.isReverse && cmd < 3) {
				cmd = 3 - cmd;
			}
			uva.execCmd(cmd, x, y);
		}
		uva.print();
	}

}
