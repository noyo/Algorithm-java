/**   
 * Copyright © 2018 Chris. All rights reserved.
 * 
 */
package six;

import java.util.List;
import java.util.Stack;

import javax.xml.crypto.Data;

import util.IOUtils;
import util.StringUtils;

/**   
 * @Description: 
 * 
 * @Package: six 
 * @author: Chris   
 * @date: 2018年4月3日 下午5:29:40 
 */
public class Uva514_6_2 {
	
	private List<String> data = IOUtils.readFromFile("6/2/in.txt");
	
	private boolean isCanReverse(List<Integer> list) {
		Stack<Integer> stack = new Stack<>();
		int n = list.size();
		int push = 1;
		int pop;
		for (int i = 0; i < n; i++) {
			pop = list.get(i);
			while (push < pop) {
				stack.push(push);
				push++;
			}
			if (push == pop) {
				push++;
				continue;
			}
			if (stack.isEmpty() || stack.pop() != pop) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String args[]) {
		
		Uva514_6_2 uva = new Uva514_6_2();
		for (String s : uva.data) {
			System.out.println(uva.isCanReverse(StringUtils.str2intlist(s)));
		}
		
	}
}
