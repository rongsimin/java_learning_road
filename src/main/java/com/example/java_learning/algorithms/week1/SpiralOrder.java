package com.example.java_learning.algorithms.week1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rongsimin
 * @date 2021/12/5 17:25
 * 54. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 */
public class SpiralOrder {
	/**
	 * 第一次做，做不出来
	 */
	public static List<Integer> spiralOrder(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		List<Integer> result = new ArrayList<>(m * n);
		boolean[][] crossed = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (crossed[i][j]) {
					return result;
				}
				result.add(matrix[i][j]);
			}
		}
		return result;
	}

	/**
	 * 第一次做，做不出来，没看题解，得到一点提示，就是往四个方向打转罢了，再写
	 * 还是写不出来
	 */
	public static List<Integer> spiralOrder2(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		List<Integer> result = new ArrayList<>(m * n);
		boolean[][] crossed = new boolean[m][n];
		// 一开始往右 ，往下、往左、往上依次 (front + 1) % 4
		int front = 0;
		int upBond = 0;
		int leftBond = 0;
		int rightBond = m - 1;
		int downBond = n - 1;
		while (upBond <= downBond || leftBond <= rightBond) {

		}
		return result;
	}

	/**
	 * 第一次做，做不出来，照着题解写一遍
	 */
	public static List<Integer> spiralOrder3(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		List<Integer> result = new ArrayList<>(m * n);
		int top = 0;
		int bottom = m - 1;
		int left = 0;
		int right = n - 1;
		while (top <= bottom && left <= right) {
			for (int i = left; i <= right; i++) {
				result.add(matrix[top][i]);
			}
			top++;
			for (int i = top; i <= bottom; i++) {
				result.add(matrix[i][right]);
			}
			right--;
			for (int i = right; i >= left; i--) {
				result.add(matrix[bottom][i]);
			}
			bottom--;
			for (int i = bottom; i >= top; i--) {
				result.add(matrix[i][left]);
			}
			left++;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(spiralOrder3(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
	}
}
