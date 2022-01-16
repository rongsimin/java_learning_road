package com.example.java_learning.algorithms.week1;

/**
 * @author rongsimin
 * @date 2021/12/5 17:01
 */
public class Rotate {
	public void rotate(int[][] matrix) {
		// 水平翻转
		int length = matrix.length;
		for (int i = 0; i < length / 2; i++) {
			for (int j = 0; j < length; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[length - i - 1][j];
				matrix[length - i - 1][j] = temp;
			}
		}
		// 沿主对角线翻转 i = j
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < i; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
	}
}
