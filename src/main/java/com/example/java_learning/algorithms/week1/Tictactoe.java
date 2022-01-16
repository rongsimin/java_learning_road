package com.example.java_learning.algorithms.week1;

/**
 * @author rongsimin
 * @date 2021/12/4 17:44
 * 面试题 16.04. 井字游戏
 * 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
 * <p>
 * 以下是井字游戏的规则：
 * <p>
 * 玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
 * "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
 * <p>
 * 示例 1：
 * <p>
 * 输入： board = ["O X"," XO","X O"]
 * 输出： "X"
 * 示例 2：
 * <p>
 * 输入： board = ["OOX","XXO","OXO"]
 * 输出： "Draw"
 * 解释： 没有玩家获胜且不存在空位
 * 示例 3：
 * <p>
 * 输入： board = ["OOX","XXO","OX "]
 * 输出： "Pending"
 * 解释： 没有玩家获胜且仍存在空位
 * 提示：
 * <p>
 * 1 <= board.length == board[i].length <= 100
 * 输入一定遵循井字棋规则
 */
public class Tictactoe {
	/**
	 * 第一次做，情况想复杂了，想不出来，其实整体思路就是遍历行，遍历列，对角线，看谁赢了
	 * 看了题解之后发现有些代码还是写不出来，只能再多熟悉下
	 */
	public static String tictactoe2(String[] board) {
		int length = board.length;
		char[][] boards = new char[length][length];
		for (int i = 0; i < length; i++) {
			boards[i] = board[i].toCharArray();
		}
		// 发现结果后返回
		boolean result = false;

		// 检查行
		for (int i = 0; i < length; i++) {
			if (boards[i][0] == ' ') {
				continue;
			}
			for (int j = 0; j < length; j++) {
				if (boards[i][j] != boards[i][0]) {
					break;
				}
			}
		}
		// 检查列
		// 对角线(左上 -> 右下)
		// 对角线(左下 -> 右上)
		return null;
	}

	/**
	 * 第二次做，不再看题解自己做试试
	 */
	public static String tictactoe(String[] board) {
		int length = board.length;
		char[][] boards = new char[length][length];
		for (int i = 0; i < length; i++) {
			boards[i] = board[i].toCharArray();
		}
		boolean determinate = false;
		// 检查行
		for (int i = 0; i < length; i++) {
			if (boards[i][0] == ' ') {
				continue;
			}
			determinate = true;
			for (int j = 1; j < length; j++) {
				if (boards[i][0] != boards[i][j]) {
					determinate = false;
					break;
				}
			}
			if (determinate) {
				return "" + boards[i][0];
			}

		}
		// 检查列
		for (int i = 0; i < length; i++) {
			if (boards[0][i] == ' ') {
				continue;
			}
			determinate = true;
			for (int j = 1; j < length; j++) {
				if (boards[0][i] != boards[j][i]) {
					determinate = false;
					break;
				}
			}
			if (determinate) {
				return "" + boards[0][i];
			}
		}
		// 对角线(左上 -> 右下)
		if (boards[0][0] != ' ') {
			determinate = true;
			for (int i = 1; i < length; i++) {
				if (boards[i][i] != boards[0][0]) {
					determinate = false;
					break;
				}
			}
			if (determinate) {
				return "" + boards[0][0];
			}
		}
		// 对角线(左下 -> 右上)
		if (boards[length - 1][0] != ' ') {
			determinate = true;
			for (int j = 1, i = length - 2; j < length && i >= 0; i--, j++) {
				if (boards[i][j] != boards[length - 1][0]) {
					determinate = false;
					break;
				}
			}
			if (determinate) {
				return "" + boards[length - 1][0];
			}
		}
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (boards[i][j] == ' ') {
					return "Pending";
				}
			}
		}
		return "Draw";
	}


	public static void main(String[] args) {
		System.out.println(tictactoe(new String[]{"O X", " XO", "X O"}));
		System.out.println(tictactoe(new String[]{"OOX","XXO","OXO"}));
		System.out.println(tictactoe(new String[]{"OOX","XXO","OX "}));
	}

}
