package kaike;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author rongsimin
 * @date 2022/3/12 11:43
 */
public class ValidateStackSequences {
	/**
	 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
	 * 输出：true
	 * 解释：我们可以按以下顺序执行：
	 * push(1), push(2), push(3), push(4), pop() -> 4,
	 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
	 */
	public boolean validateStackSequences(int[] pushed, int[] popped) {
		// pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
		// i,j分别在两个数组中，如果pushed[i] != popped[j]
		// 那么i++,直到数组结束，说明全部入栈了，此时看j是不是也到最后了，如果是，那么true
		int i = 0, j = 0;
		int length = popped.length;
		Deque<Integer> stack = new LinkedList<>();
		while (i < length) {
			if (!stack.isEmpty() && popped[j] == stack.getLast()) {
				stack.removeLast();
				j++;
			} else {
				stack.addLast(pushed[i]);
				i++;
			}
		}
		while (j < length) {
			if (popped[j++] != stack.removeLast()) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[] pushed = {1, 2, 3, 4, 5};
		int[] popped = {4, 5, 3, 2, 1};
		System.out.println(new ValidateStackSequences().validateStackSequences(pushed, popped));
	}
}
