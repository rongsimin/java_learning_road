package kaike;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author rongsimin
 * @date 2022/3/13 11:45
 * https://leetcode-cn.com/problems/exclusive-time-of-functions/
 * 636. 函数的独占时间
 * 有一个 单线程 CPU 正在运行一个含有 n 道函数的程序。每道函数都有一个位于  0 和 n-1 之间的唯一标识符。
 * <p>
 * 函数调用 存储在一个 调用栈 上 ：当一个函数调用开始时，它的标识符将会推入栈中。而当一个函数调用结束时，它的标识符将会从栈中弹出。标识符位于栈顶的函数是 当前正在执行的函数 。每当一个函数开始或者结束时，将会记录一条日志，包括函数标识符、是开始还是结束、以及相应的时间戳。
 * <p>
 * 给你一个由日志组成的列表 logs ，其中 logs[i] 表示第 i 条日志消息，该消息是一个按 "{function_id}:{"start" | "end"}:{timestamp}" 进行格式化的字符串。例如，"0:start:3" 意味着标识符为 0 的函数调用在时间戳 3 的 起始开始执行 ；而 "1:end:2" 意味着标识符为 1 的函数调用在时间戳 2 的 末尾结束执行。注意，函数可以 调用多次，可能存在递归调用 。
 * <p>
 * 函数的 独占时间 定义是在这个函数在程序所有函数调用中执行时间的总和，调用其他函数花费的时间不算该函数的独占时间。例如，如果一个函数被调用两次，一次调用执行 2 单位时间，另一次调用执行 1 单位时间，那么该函数的 独占时间 为 2 + 1 = 3 。
 * <p>
 * 以数组形式返回每个函数的 独占时间 ，其中第 i 个下标对应的值表示标识符 i 的函数的独占时间。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
 * 输出：[3,4]
 * 解释：
 * 函数 0 在时间戳 0 的起始开始执行，执行 2 个单位时间，于时间戳 1 的末尾结束执行。
 * 函数 1 在时间戳 2 的起始开始执行，执行 4 个单位时间，于时间戳 5 的末尾结束执行。
 * 函数 0 在时间戳 6 的开始恢复执行，执行 1 个单位时间。
 * 所以函数 0 总共执行 2 + 1 = 3 个单位时间，函数 1 总共执行 4 个单位时间。
 * 示例 2：
 * <p>
 * 输入：n = 1, logs = ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
 * 输出：[8]
 * 解释：
 * 函数 0 在时间戳 0 的起始开始执行，执行 2 个单位时间，并递归调用它自身。
 * 函数 0（递归调用）在时间戳 2 的起始开始执行，执行 4 个单位时间。
 * 函数 0（初始调用）恢复执行，并立刻再次调用它自身。
 * 函数 0（第二次递归调用）在时间戳 6 的起始开始执行，执行 1 个单位时间。
 * 函数 0（初始调用）在时间戳 7 的起始恢复执行，执行 1 个单位时间。
 * 所以函数 0 总共执行 2 + 4 + 1 + 1 = 8 个单位时间。
 * 示例 3：
 * <p>
 * 输入：n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"]
 * 输出：[7,1]
 * 解释：
 * 函数 0 在时间戳 0 的起始开始执行，执行 2 个单位时间，并递归调用它自身。
 * 函数 0（递归调用）在时间戳 2 的起始开始执行，执行 4 个单位时间。
 * 函数 0（初始调用）恢复执行，并立刻调用函数 1 。
 * 函数 1在时间戳 6 的起始开始执行，执行 1 个单位时间，于时间戳 6 的末尾结束执行。
 * 函数 0（初始调用）在时间戳 7 的起始恢复执行，执行 1 个单位时间，于时间戳 7 的末尾结束执行。
 * 所以函数 0 总共执行 2 + 4 + 1 = 7 个单位时间，函数 1 总共执行 1 个单位时间。
 * 示例 4：
 * <p>
 * 输入：n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"]
 * 输出：[8,1]
 * 示例 5：
 * <p>
 * 输入：n = 1, logs = ["0:start:0","0:end:0"]
 * 输出：[1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 100
 * 1 <= logs.length <= 500
 * 0 <= function_id < n
 * 0 <= timestamp <= 109
 * 两个开始事件不会在同一时间戳发生
 * 两个结束事件不会在同一时间戳发生
 * 每道函数都有一个对应 "start" 日志的 "end" 日志
 */
public class ExclusiveTime {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6");
//		System.out.println(Arrays.toString(new ExclusiveTime().exclusiveTime(2, list)));
		list = Arrays.asList("0:start:0","1:start:5","2:start:6","3:start:9","4:start:11","5:start:12","6:start:14","7:start:15","1:start:24","1:end:29","7:end:34","6:end:37","5:end:39","4:end:40","3:end:45","0:start:49","0:end:54","5:start:55","5:end:59","4:start:63","4:end:66","2:start:69","2:end:70","2:start:74","6:start:78","0:start:79","0:end:80","6:end:85","1:start:89","1:end:93","2:end:96","2:end:100","1:end:102","2:start:105","2:end:109","0:end:114");
		System.out.println(Arrays.toString(new ExclusiveTime().exclusiveTime(8, list)));
	}

	public int[] exclusiveTime(int n, List<String> logs) {
		int[] res = new int[n];
		// 存储函数id，当start时入栈，当end时出栈
		Deque<Integer> stack = new LinkedList<>();
		int lastTimeStamp = 0;
		for (int i = 0; i < logs.size(); i++) {
			int left = 0;
			int right = logs.get(i).length() - 1;
			int id = 0;
			int timestamp = 0;
			while (left <= right) {
				char ch = logs.get(i).charAt(left);
				if (ch == ':') {
					break;
				}
				id = id * 10 + (ch - '0');
				left++;
			}
			int num = 1;
			// 123
			while (left <= right) {
				char ch = logs.get(i).charAt(right);
				if (ch == ':') {
					break;
				}
				timestamp = (ch - '0') * num + timestamp;
				num *= 10;
				right--;
			}
//			System.out.println(id + ":" + (right - left == 6 ? "start" : "end") + ":" + timestamp);
			if (right - left == 6) {
				if (!stack.isEmpty()) {
					res[stack.getLast()] += timestamp - lastTimeStamp;
				}
				stack.addLast(id);
				lastTimeStamp = timestamp;
			} else {
				stack.removeLast();
				res[id] += timestamp - lastTimeStamp + 1;
				lastTimeStamp = timestamp + 1;
			}
		}
		return res;
	}

	private class Node {
		public int id;
		public int timestamp;
		public boolean isStart;

		public Node(int id, int timestamp, boolean isStart) {
			this.id = id;
			this.timestamp = timestamp;
			this.isStart = isStart;
		}
	}
}
