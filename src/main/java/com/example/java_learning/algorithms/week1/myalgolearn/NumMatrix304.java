package com.example.java_learning.algorithms.week1.myalgolearn;

/**
 * https://leetcode-cn.com/problems/range-sum-query-2d-immutable/
 */
public class NumMatrix304 {
	private int[][] preSum;

	public NumMatrix304(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		if (m == 0 || n == 0) {
			return;
		}
		preSum = new int[m + 1][n + 1];
		for (int i = 1; i < preSum.length; i++) {
			for (int j = 1; j < preSum[0].length; j++) {
				// 从(0,0)到(i,j)的求和
				preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] + matrix[i - 1][j - 1] - preSum[i - 1][j - 1];
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		return preSum[row2 + 1][col2 + 1] - preSum[row2 + 1][col1] - preSum[row1][col2 + 1] + preSum[row1][col1];
	}
}
