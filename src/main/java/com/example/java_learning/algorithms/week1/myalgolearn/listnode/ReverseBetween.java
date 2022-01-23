package com.example.java_learning.algorithms.week1.myalgolearn.listnode;

/**
 * @author rongsimin
 * @date 2022/1/23 10:59
 */
public class ReverseBetween {

	private ListNode successor = null;

	public ListNode reverseBetweenNR(ListNode head, int left, int right) {
		ListNode dummyNode = new ListNode(-1, head);
		ListNode preNode = dummyNode;
		// preNode.next = left
		for (int i = 0; i < left - 1; i++) {
			preNode = preNode.next;
		}
		ListNode temp = null;
		ListNode cur = preNode.next;
		while (left <= right) {
			ListNode next = cur.next;
			cur.next = temp;
			temp = cur;
			cur = next;
			// 忘记left++
			left++;
		}
		// temp = right cur = right.next
		preNode.next.next = cur;
		preNode.next = temp;
		return dummyNode.next;
	}

	/**
	 * 递归翻转整个链表
	 */
	public ListNode reverseListR(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode last = reverseListR(head.next);
		head.next.next = head;
		head.next = null;
		return last;
	}

	/**
	 * 递归翻转链表的前 n 个节点，
	 * 核心思路，递归到底的情况，需要用一个后驱节点successor来保存， 第 n + 1 个节点
	 */
	public ListNode reverseNthListR(ListNode head, int n) {
		if (n <= 1) {
			successor = head.next;
			return head;
		}
		ListNode last = reverseNthListR(head.next, n - 1);
		head.next.next = head;
		head.next = successor;
		return last;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
		System.out.println(head);
		head = new ReverseBetween().reverseNthListR(head, 2);
		System.out.println(head);
	}


}
