package six;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import util.IOUtils;
import util.StringUtils;

/**
 * 例题
 * 
 * @author chris
 *
 */
public class Uva210_6_1 {
	private final String PRINT = "print";
	private final String LOCK = "lock";
	private final String UNLOCK = "unlock";
	private final String END = "end";
	private final String EQUAL = "=";
	private final String FILE = "6-1 uva210";

	private int num;
	private int t[] = new int[5];
	private int perCast;
	/** 当前已执行时间，为-1 表示已被添加经block queue */
	private int cast;
	private Map<String, String> var = new HashMap<>(0);
	private List<String> data = IOUtils.readFromFile(FILE + "/in1.txt");
	private int index[];
	private Deque<Integer> deque = new ArrayDeque<>();
	private Queue<Integer> blockQueue = new LinkedList<>();
	private int curProgram;
	private boolean isLocked = false;

	/**
	 * 初始化：程序数量&程序代价
	 * 
	 * @param content
	 */
	private void init(String s[]) {
		num = Integer.parseInt(s[0]);
		for (int i = 0; i < 5; i++) {
			t[i] = Integer.parseInt(s[i + 1]);
		}
		perCast = Integer.parseInt(s[6]);
		index = new int[num];
	}

	/**
	 * 赋值
	 * 
	 * @param s
	 */
	private void value(String s[]) {
		var.put(s[0], s[2]);
	}

	/**
	 * 打印对应变量的值
	 * 
	 * @param s
	 */
	private void print(String s[]) {
//		 System.out.println(curProgram + 1 + ": " + s[1] + "="
//				 + (null == var.get(s[1]) ? 0 : var.get(s[1])));
//		 IOUtils.wirteToFile(FILE + "/out.txt", true
//				 , curProgram + 1 + ": " + (null == var.get(s[1]) ? 0 : var.get(s[1])) + "\n");
	}

	/**
	 * 处理单行代码
	 * 
	 * @param content
	 * @return
	 */
	private boolean handleCode(String content) {
		// 程序是否执行完毕
		boolean isFinished = false;
		// System.out.println(content);
		if (content.startsWith(PRINT)) {
			print(content.split(" "));
			cast += t[1];
		} else if (LOCK.equals(content)) {
			// System.out.println(LOCK + " " + curProgram);
			if (!isLocked) {
				cast += t[2];
				isLocked = true;
			} else {
				cast = -1;
				blockQueue.offer(curProgram);
			}
		} else if (UNLOCK.equals(content)) {
//			System.out.println(UNLOCK + " " + curProgram);
			if (null != blockQueue.peek()) {
				deque.add(blockQueue.poll());
			}
			isLocked = null != blockQueue.peek();
			cast += t[3];
		} else if (END.equals(content)) {
			cast += t[4];
			isFinished = true;
		} else if (content.contains(EQUAL)) {
			value(content.split(" "));
			cast += t[0];
		} else {
			throw new IllegalArgumentException();
		}
		return isFinished;
	}

	/**
	 * 程序并发执行
	 */
	private void exeProgram() {
		while (null != deque.peekFirst()) {
			curProgram = deque.pollFirst();
			cast = 0;
			int i = index[curProgram];
			boolean end = false;
			while (!(end = handleCode(data.get(i++))) && cast < perCast && cast > 0);
			if (!end && cast >= 0) {
				deque.offer(curProgram);
			}
			index[curProgram] = i;
		}
	}

	private void start() {
		int n = Integer.parseInt(data.get(0));
		int i = 1, j = 0;
		int length = data.size();
		String content;
		while (n-- > 0) {
			j = 0;
			i++;
			var.clear();
			for (; i < length; i++) {
				content = data.get(i);
				if (StringUtils.isEmpty(content)) {
					break;
				}
				if (content.charAt(0) >= '0' && content.charAt(0) <= '9') {
					init(content.split(" "));
					deque.offer(j);
					index[j++] = i + 1;
				} else if (j < num && END.equals(content)) {
					deque.offer(j);
					index[j++] = i + 1;
				}
			}
			exeProgram();

		}
	}

	public static void main(String args[]) {

		new Uva210_6_1().start();

	}
}
