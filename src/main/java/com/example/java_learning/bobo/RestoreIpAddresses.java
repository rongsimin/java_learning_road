package com.example.java_learning.bobo;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/restore-ip-addresses/
 * <p>
 * 93. 复原 IP 地址
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * <p>
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 * <p>
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 * <p>
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 20
 * s 仅由数字组成
 */
public class RestoreIpAddresses {

	public static void main(String[] args) {
		System.out.println(new RestoreIpAddresses().restoreIpAddresses("25525511135"));
	}

	public List<String> restoreIpAddresses(String s) {
		if (s.length() < 4) {
			return new ArrayList<>();
		}
		List<String> res = new ArrayList<>();
		dfs(res, s, 0, 0, "");
		return res;
	}

	private void dfs(List<String> res, String s, int level, int index, String temp) {
		if (level == 3) {
			/*if (isValidIp(temp)) {
				res.add(temp);
			}*/
			System.out.println(temp);
			res.add(temp + s.substring(index));
			return;
		}

		for (int i = 0; i < s.length() - 1; i++) {
			dfs(res, s, level + 1, index + 1,temp + s.charAt(i) + '.');
//			dfs(res, s, level, temp + s.charAt(i));
		}

	}

	private boolean isValidIp(String temp) {

		return false;
	}

}
