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
 * @date: 2018上午11:00:46
 */
public class Uva11988_6_4 {

	private List<String> data = IOUtils.readFromFile("6/4/in.txt");

	private void print(String s) {
		Str head = new Str(' ');
		Str end = head;
		Str cur = head;
		Str curNext = null;
		int l = s.length();
		char ch;
		for (int i = 0; i < l; i++) {
			ch = s.charAt(i);
			switch (ch) {
			case '[':
				cur = head;
				curNext = head.next;
				break;
			case ']':
				cur = end;
				curNext = null;
				break;
			default:
				cur.next = new Str(ch);
				cur = cur.next;
				if (null == curNext) {
					end = cur;
				} else {
					cur.next = curNext;
				}
				break;
			}
		}
		head.print();
	}

	public static void main(String args[]) {

		Uva11988_6_4 uva = new Uva11988_6_4();
		for (String s : uva.data) {
			uva.print(s);
		}
	}

	class Str {
		char ch;
		Str next;

		public Str(char ch) {
			this.ch = ch;
		}
		
		public void print() {
			System.out.println(toString());
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			String s = "";
			Str head = this.next;
			while (null != head) {
				s += head.ch;
				head = head.next;
			}
			return s;
		}
	}

}
