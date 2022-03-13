package com.example.java_learning.bobo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations/
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 */
public class Permute {
	private List<List<Integer>> res = new ArrayList<>();
	private boolean[] used;

	public List<List<Integer>> permute(int[] nums) {
		if (nums.length == 1) {
			List<Integer> list = new ArrayList<>();
			list.add(nums[0]);
			res.add(list);
			return res;
		}
		used = new boolean[nums.length];
		dfs(nums, 0, new LinkedList<>());
		return res;
	}

	/**
	 * @param nums    目标数组中寻找所有的全排列组合
	 * @param index   当前遍历到的数组下标
	 * @param perList 用来保存存储了index - 1个元素的全排列，加入当前遍历到的数组下标对应的元素后，变成index个元素
	 */
	private void dfs(int[] nums, int index, LinkedList<Integer> perList) {
		System.out.println(index + ":" + perList);
		if (index == nums.length) {
			res.add((List<Integer>) perList.clone());
			System.out.println("get " + perList + " , return");
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (!used[i]) {
				perList.addLast(nums[i]);
				used[i] = true;
//				dfs(nums, index + 1, (LinkedList<Integer>) perList.clone());
				System.out.println("index = " + index + ",used :" + i + ",nums[i] = " + nums[i]);
				dfs(nums, index + 1, perList);
				perList.removeLast();
				used[i] = false;
			}
		}

//		System.out.println(index + " = " + "complete, return");
	}

	public static void main(String[] args) {
		int[] ints = {1, 2, 3};
		final Permute permute = new Permute();
		System.out.println(permute.permute(ints));
		System.out.println(Arrays.toString(permute.used));
	}
}
