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
public class PacificAtlantic {

	private List<List<Integer>> res = new ArrayList<>();

	private boolean[][] canPacific;

	private boolean[][] canAtlantic;

	private int m;

	private int n;

	public static void main(String[] args) {
		int[][] heights = {{2, 1}, {1, 2}};
		System.out.println(new PacificAtlantic().pacificAtlantic(heights));
		heights = new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
		System.out.println(new PacificAtlantic().pacificAtlantic(heights));
	}

	public List<List<Integer>> pacificAtlantic(int[][] heights) {
		m = heights.length;
		n = heights[0].length;
		canPacific = new boolean[m][n];
		canAtlantic = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			dfs(heights, canPacific, i, 0, heights[i][0]);
			dfs(heights, canAtlantic, i, n - 1, heights[i][n - 1]);
		}

		for (int i = 0; i < n; i++) {
			dfs(heights, canPacific, 0, i, heights[0][i]);
			dfs(heights, canAtlantic, m - 1, i, heights[m - 1][i]);
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (canPacific[i][j] && canAtlantic[i][j]) {
					Integer[] point = new Integer[2];
					point[0] = i;
					point[1] = j;
					res.add(Arrays.asList(point));
				}
			}
		}

		return res;
	}

	private void dfs(int[][] heights, boolean[][] isVisited, int i, int j, int height) {
		if (!inArea(i, j) || isVisited[i][j] || heights[i][j] < height) {
			return;
		}
		isVisited[i][j] = true;
		dfs(heights, isVisited, i - 1, j, heights[i][j]);
		dfs(heights, isVisited, i + 1, j, heights[i][j]);
		dfs(heights, isVisited, i, j - 1, heights[i][j]);
		dfs(heights, isVisited, i, j + 1, heights[i][j]);
	}

	private boolean inArea(int i, int j) {
		return i >= 0 && i < m && j >= 0 && j < n;
	}
}
