package com.example.java_learning.algorithms.week1;

/**
 * @author rongsimin
 * @date 2021/12/4 13:31
 * 面试题 01.05. 一次编辑
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * first = "pales"
 * second = "pal"
 * 输出: False
 */
public class OneEditAway {

	/**
	 * 第一次做题，有个地方想错了，编辑一次的意思是，最多变化一个字母（增删改），那么
	 * 最多只有一个元素不一样，且其它元素的的"位置"是完全相同的
	 * 而不是说,只要字符串里面有一个字符不一样即可，如 abc 与 bca，完全相同，但是不可通过一次编辑达到
	 */
	public static boolean oneEditAway2(String first, String second) {
		if (first == null || second == null) {
			return false;
		}
		// 如果两个字符串相同呢？那么不符合，false
		if (first.equals(second)) {
			return true;
		}
		// 如果两个字符串长度相差大于1，肯定false
		int fLength = first.length();
		int sLength = second.length();
		int diff = fLength - sLength;
		if (diff > 1 || diff < -1) {
			return false;
		}
		int[] count = new int[256];
		for (int i = 0; i < fLength; i++) {
			count[first.charAt(i) - 'A']++;
		}
		for (int i = 0; i < sLength; i++) {
			count[second.charAt(i) - 'A']--;
		}
		int notZero = 0;
		for (int i = 0; i < count.length; i++) {
			if (count[i] != 0) {
				notZero++;
			}
		}

		// 相差不大于1，那么就遍历两个字符串，如果只有一个字符不一样，那么true
		return notZero <= 2;
	}

	public static boolean oneEditAway(String first, String second) {
		if (first == null || second == null) {
			return false;
		}
		// 如果两个字符串的长度差大于等于2，就一定false
		int fLength = first.length();
		int sLength = second.length();
		if (Math.abs(fLength - sLength) > 1) {
			return false;
		}
		// 遍历两个字符串，找到第一个字符不相同的地方
		int i = 0;
		int j = 0;
		while (i < fLength && j < sLength && first.charAt(i) == second.charAt(j)) {
			i++;
			j++;
		}
		// 此时两种情况，一是遍历完了全部相同，那么返回true,二是碰到第一个不相同的了,不管哪个，比较两个长度都是要的
		if (fLength == sLength) {
			i++;
			j++;
		} else if (fLength > sLength) {
			i++;
		} else {
			j++;
		}
		// 其余的必须全部相同
		while (i < fLength && j < sLength) {
			if (first.charAt(i) != second.charAt(j)) {
				return false;
			}
			i++;
			j++;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(oneEditAway("pales", "pal"));
		System.out.println(oneEditAway("pale", "ple"));
		System.out.println(oneEditAway("pales", "pals"));
		System.out.println(oneEditAway("", ""));
		System.out.println(oneEditAway("a", "b"));
		System.out.println(oneEditAway("abc", "bca"));
	}
}
