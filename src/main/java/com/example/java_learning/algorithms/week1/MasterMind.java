package com.example.java_learning.algorithms.week1;

import java.util.Arrays;

/**
 * @author rongsimin
 * @date 2021/12/4 16:45
 * 面试题 16.15. 珠玑妙算
 * 珠玑妙算游戏（the game of master mind）的玩法如下。
 * <p>
 * 计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。要是猜对某个槽的颜色，则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。
 * <p>
 * 给定一种颜色组合solution和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。
 * <p>
 * 示例：
 * <p>
 * 输入： solution="RGBY",guess="GGRR"
 * 输出： [1,1]
 * 解释： 猜中1次，伪猜中1次。
 * 提示：
 * <p>
 * len(solution) = len(guess) = 4
 * solution和guess仅包含"R","G","B","Y"这4种字符
 */
public class MasterMind {
	/**
	 * 第一次做不出来，主要原因是太想一次遍历完成，发现写不下去代码
	 */
	public static int[] masterMind1(String solution, String guess) {
		int right = 0;
		int wrong = 0;

		int length = solution.length();
		boolean[] hited = new boolean[length];
		boolean[] used = new boolean[length];
		for (int i = 0; i < length; i++) {
			if (solution.charAt(i) == guess.charAt(i)) {
				right++;
				hited[i] = true;
				used[i] = true;
			}
		}
		for (int i = 0; i < length; i++) {
			if (hited[i]) {
				continue;
			}
			for (int j = 0; j < length; j++) {
				if (!used[j] && guess.charAt(i) == solution.charAt(j)) {
					wrong++;
					used[j] = true;
					break;
				}
			}
		}
		return new int[]{right, wrong};
	}

	/**
	 * 第二次，看完题解后再次做
	 */
	public static int[] masterMind(String solution, String guess) {
		int length = solution.length();

		int right = 0;
		int wrong = 0;
		boolean[] rights = new boolean[length];
		boolean[] used = new boolean[length];
		// 遍历一次找到猜中的次数
		for (int i = 0; i < length; i++) {
			if (solution.charAt(i) == guess.charAt(i)) {
				right++;
				rights[i] = true;
				used[i] = true;
			}
		}
		// 再遍历找到伪猜中的次数
		for (int i = 0; i < length; i++) {
			if (used[i]) {
				continue;
			}
			for (int j = 0; j < length; j++) {
				if (!used[j] && solution.charAt(i) == guess.charAt(j)) {
					// 没有用过这个元素，且
					used[j] = true;
					wrong++;
					break;
				}
			}
		}
		return new int[]{right, wrong};
	}

	public static void main(String[] args) {
//		System.out.println(Arrays.toString(masterMind("RGBY", "GGRR")));
		System.out.println(Arrays.toString(masterMind("BRBB", "RBGY")));
		System.out.println(Arrays.toString(masterMind("RGRB", "BBBY")));
	}
}
