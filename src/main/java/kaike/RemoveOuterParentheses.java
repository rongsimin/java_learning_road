package kaike;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author rongsimin
 * @date 2022/3/12 17:41
 */
public class RemoveOuterParentheses {

	public static void main(String[] args) {
		RemoveOuterParentheses parentheses = new RemoveOuterParentheses();
//		System.out.println(parentheses.removeOuterParentheses("(()())(())"));
		String s = "(()())(())(()(()))";
		System.out.println(parentheses.removeOuterParentheses(s));

	}

	public String removeOuterParentheses(String s) {
		int left = 1;
		int right = 0;
		int y = 0;
		Deque<String> stack = new LinkedList<>();
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				right++;
				if (right == left) {
					stack.addLast(s.substring(y + 1,2 * right - 1));
					y = 2 * right;
				}
			} else {
				left++;
			}
		}
		StringBuilder builder = new StringBuilder();
		while (!stack.isEmpty()) {
			builder.append(stack.removeFirst());
		}
		return builder.toString();

	}
}
