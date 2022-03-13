package com.example.java_learning;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author rongsimin
 * @date 2022/2/19 22:57
 */
public class Test {
	public static void main(String[] args) {
		final Queue<Integer> queue = new LinkedList<>();

		queue.offer(null);
		System.out.println(queue.poll());
	}
}
