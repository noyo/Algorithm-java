/**   
 * Copyright © 2018 Chris. All rights reserved.
 * 
 */
package six;

import java.util.List;

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

	public static void main(String args[]) {
		
		
		
	}
	
	
	
}

class Matrix {
		int n;
		int m;
		
		public Matrix(int n, int m) {
			this.n = n;
			this.m = m;
		}
	}