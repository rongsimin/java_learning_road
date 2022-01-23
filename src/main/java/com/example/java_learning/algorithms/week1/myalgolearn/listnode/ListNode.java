package com.example.java_learning.algorithms.week1.myalgolearn.listnode;

/**
 * @author rongsimin
 * @date 2022/1/23 10:57
 */
public class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ListNode :");
		ListNode listNode = this;
		while (listNode != null) {
			builder.append(listNode.val + "->");
			listNode = listNode.next;
		}
		builder.append("null");
		return builder.toString();
	}
}
