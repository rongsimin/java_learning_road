package com.example.java_learning.labulado;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 */
public class Codec {
	private String encoder;

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder builder = new StringBuilder();
		return encode(root, builder);
	}

	private String encode(TreeNode root, StringBuilder builder) {
		if (root == null) {
			return builder.append("#").append(",").toString();
		}
		String rootStr = builder.append(root.val).append(",").toString();
		String left = encode(root.left, builder);
		String right = encode(root.right, builder);
		return rootStr + left + right;
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data == null) {
			return null;
		}
		String[] split = data.split(",");
		LinkedList<String> linkedList = new LinkedList<>();
		for (String s : split) {
			linkedList.addLast(s);
		}
		return decode(linkedList);
	}

	private TreeNode decode(LinkedList<String> linkedList) {
		if (linkedList.isEmpty()) {
			return null;
		}
		String root = linkedList.pollFirst();
		if (root.equals("#")) {
			return null;
		}
		TreeNode rootNode = new TreeNode(Integer.parseInt(root));
		String left = linkedList.pollFirst();
		if (left.equals("#")) {
			rootNode.left = null;
		} else {
			rootNode.left = new TreeNode(Integer.parseInt(left));
//			linkedList.addLast(String.valueOf(rootNode.left.val));
		}
		String right = linkedList.pollFirst();
		if (right.equals("#")) {
			rootNode.right = null;
		} else {
			rootNode.right = new TreeNode(Integer.parseInt(right));
//			linkedList.addLast(String.valueOf(rootNode.right.val));
		}
		return rootNode;
	}
}
