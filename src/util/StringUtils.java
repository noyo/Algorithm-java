package util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Description: 字符串工具类
 * 
 * @Package: util 
 * @author: Chris   
 * @date: 2018年4月4日 下午3:05:01
 */
public class StringUtils {

	/**
	 * 判空
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		return null == string || string.length() == 0;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static List<Integer> str2intlist(String str) {
		if (null == str) throw new IllegalArgumentException();
		
		String ss[] = str.split(" ");
		List<Integer> ans = new ArrayList<>();
		for (String s : ss) {
			ans.add(Integer.parseInt(s));
		}
		return ans;
	}
	
}
