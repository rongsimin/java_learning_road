package com.example.java_learning.bobo;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * <p>
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 * <p>
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 */
public class LetterCombinations {
	private String[] map;

	private List<String> arrayList;

	public LetterCombinations() {
		this.map = new String[]{" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		this.arrayList = new ArrayList<>();
	}


	public List<String> letterCombinations(String digits) {
		if (digits == "") {
			return this.arrayList;
		}
		dfs(digits, 0, "");
		return this.arrayList;
	}

	private void dfs(String digits, int level, String combination) {
		if (level == digits.length()) {
			this.arrayList.add(combination);
			return;
		}
		char c = digits.charAt(level);
		for (int i = 0; i < this.map[c - '0'].length(); i++) {
			dfs(digits, level + 1, combination + this.map[c - '0'].charAt(i));
		}
	}
}
