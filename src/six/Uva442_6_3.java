/**   
 * Copyright © 2018 Chris. All rights reserved.
 * 
 */
package six;

import java.util.List;
import java.util.Stack;

import util.IOUtils;

/**   
 * @Description: 例题
 * 
 * @Package: six 
 * @author: Chris   
 * @date: 2018年4月4日 下午4:14:11 
 */
public class Uva442_6_3 {
	
	private List<String> data = IOUtils.readFromFile("6/3/in.txt");
	private static Matrix A, B, C, D, E, F, G, H, I;
	
	/**
	 * 测试数据
	 */
	static {
		A = new Matrix(50, 10);
		B = new Matrix(10, 20);
		C = new Matrix(20, 5);
		D = new Matrix(30, 35);
		E = new Matrix(35, 15);
		F = new Matrix(15, 5);
		G = new Matrix(5, 10);
		H = new Matrix(10, 20);
		I = new Matrix(20, 25);
	}
	
	private Matrix createMartix(char c) {
		switch (c) {
		case 'A':
			return A;
		case 'B':
			return B;
		case 'C':
			return C;
		case 'D':
			return D;
		case 'E':
			return E;
		case 'F':
			return F;
		case 'G':
			return G;
		case 'H':
			return H;
		case 'I':
			return I;
		}
		return null;
	}

	private long executeCount(char[] s) {
		Stack<Matrix> params = new Stack<>();
		long product = 0;
		for (char c : s) {
			if (c == '(') {
				continue;
			}
			if (c == ')') {
				Matrix B = params.pop();
				Matrix A = params.peek();
				int muti = A.multi(B);
				if (muti == -1) return muti;
				product += muti;
			} else {
				params.push(createMartix(c));
			}
		}
		return product;
	}
	
	public static void main(String args[]) {
		
		Uva442_6_3 uva = new Uva442_6_3();
		
		for (String s : uva.data) {
			System.out.println(uva.executeCount(s.toCharArray()));
		}
		
	}
	
	
	
}

class Matrix {
		int n;
		int m;
		
		public Matrix(int n, int m) {
			this.n = n;
			this.m = m;
		}
		
		/**
		 * 矩阵乘法
		 * 
		 * @param A
		 * @param B
		 * @return
		 */
		public int multi(Matrix B) {
			if (null == B) throw new IllegalArgumentException("the Matrix can't be null");
			if (m != B.n) {
				return -1;
			}
			return n * m * B.n;
		}
	}