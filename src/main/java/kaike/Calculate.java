package kaike;

/**
 * 表达式求值 (1 + 2) * (3 + (5 - 6 / 3))
 *
 * @author rongsimin
 * @date 2022/3/12 9:53
 */

public class Calculate {

	public static void main(String[] args) {
		System.out.println(new Calculate().calculate("(1+2)*(3*(5-6/3))"));
	}

	public int calculate(String s) {
		if (s == null) {
			return 0;
		}
		return calculate(s, 0, s.length() - 1);
	}

	private int calculate(String s, int left, int right) {
		if (left > right) {
			return 0;
		}
		int minPriority = Integer.MAX_VALUE;
		int priority = Integer.MAX_VALUE;
		// delta表示增量，当遇到(，delta增加，那么之后的操作符都加上这个delta，就可以表示这个操作符号的优先级了
		int delta = 0;
		int minIndex = -1;
		for (int i = left; i <= right; i++) {
			if (s.charAt(i) == '+' || s.charAt(i) == '-') {
				priority = 1 + delta;
			} else if (s.charAt(i) == '*' || s.charAt(i) == '/') {
				priority = 2 + delta;
			} else if (s.charAt(i) == '(') {
				delta += 100;
			} else if (s.charAt(i) == ')') {
				delta -= 100;
			}
			if (minPriority > priority) {
				minPriority = priority;
				minIndex = i;
			}
		}
		if (minIndex == -1) {
			// 没有操作符号+,-,*,/
			int result = 0;
			for (int i = left; i <= right; i++) {
				// 这里很容易误认为是一个纯数值的字符串，但其实还会有()
				if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
					result = result * 10 + (s.charAt(i) - '0');
				}
			}
			return result;
		}
		System.out.println("find minPriority :" + s.charAt(minIndex));
		int a = calculate(s, left, minIndex - 1);
		int b = calculate(s, minIndex + 1, right);
		System.out.println(a);
		System.out.println(b);
		return calc(a, b, s.charAt(minIndex));
	}

	private int calc(int a, int b, char op) {
		if (op == '+') {
			return a + b;
		} else if (op == '-') {
			return a - b;
		} else if (op == '*') {
			return a * b;
		} else {
			return a / b;
		}
	}
}
