package com.example.java_learning.bobo.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author rongsimin
 * @date 2022/3/5 11:02
 * https://leetcode-cn.com/problems/pacific-atlantic-water-flow/
 * <p>
 * 417. 太平洋大西洋水流问题
 * 有一个 m × n 的长方形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 * <p>
 * 这个岛被分割成一个个方格网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 * <p>
 * 岛上雨水较多，如果相邻小区的高度 小于或等于 当前小区的高度，雨水可以直接向北、南、东、西流向相邻小区。水可以从海洋附近的任何细胞流入海洋。
 * <p>
 * 返回 网格坐标 result 的 2D列表 ，其中 result[i] = [ri, ci] 表示雨水可以从单元格 (ri, ci) 流向 太平洋和大西洋 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * 示例 2：
 * <p>
 * 输入: heights = [[2,1],[1,2]]
 * 输出: [[0,0],[0,1],[1,0],[1,1]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 */
public class PacificAtlantic2 {

	private List<List<Integer>> res = new ArrayList<>();

	private boolean[][] canPacific;

	private boolean[][] canAtlantic;


	public List<List<Integer>> pacificAtlantic(int[][] heights) {
		int m = heights.length;
		int n = heights[0].length;
		canPacific = new boolean[m][n];
		canAtlantic = new boolean[m][n];
		// 最左列
		for (int i = 0; i < m; i++) {
			canPacific[i][0] = true;
		}
		// 最右列
		for (int i = 0; i < m; i++) {
			canAtlantic[i][n - 1] = true;
		}
		// 最上行
		for (int i = 0; i < n; i++) {
			canPacific[0][i] = true;
		}
		// 最下行
		for (int i = 0; i < n; i++) {
			canAtlantic[m - 1][i] = true;
		}
		for (int i = 0; i < heights.length; i++) {
			for (int j = 0; j < heights[0].length; j++) {
				if (dfs1(heights, i, j, heights[i][j]) && dfs2(heights, i, j, heights[i][j])) {
					Integer[] point = new Integer[2];
					point[0] = i;
					point[1] = j;
					res.add(Arrays.asList(point));
				}
			}
		}
		return res;
	}

	/**
	 * 往下或者往右，流向大西洋
	 */
	private boolean dfs2(int[][] heights, int i, int j, int height) {
		if (i == heights.length || j == heights[0].length) {
			return true;
		}
		// 往下
		boolean down = false;
		if (i < heights.length - 1 && height >= heights[i + 1][j]) {
			down = dfs2(heights, i + 1, j, height);
		}
		// 往右
		boolean right = false;
		if (j < heights[0].length - 1 && height >= heights[i][j + 1]) {
			right = dfs2(heights, i, j + 1, height);
		}
		return down || right;
	}

	/**
	 * 往上或者往左，流向太平洋
	 */
	private boolean dfs1(int[][] heights, int i, int j, int height) {
		if (i < 0 || j < 0) {
			return true;
		}
		// 往上
		boolean up = true;
		if (i < heights.length - 1 && height >= heights[i + 1][j]) {
			up = dfs2(heights, i + 1, j, height);
		} else {
			up = false;
		}
		// 往左
		boolean left = false;
		if (j < heights[0].length - 1 && height >= heights[i][j + 1]) {
			left = dfs2(heights, i, j + 1, height);
		}
		return up || left;
	}
}
