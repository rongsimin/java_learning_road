package kaike;

/**
 * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 * <p>
 * 138. 复制带随机指针的链表
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 * <p>
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * <p>
 * 返回复制链表的头节点。
 * <p>
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * <p>
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 1000
 * -104 <= Node.val <= 104
 * Node.random 为 null 或指向链表中的节点。
 */
public class CopyRandomList {

	public static void main(String[] args) {
		Node head = new Node(1);
		Node next = new Node(2);
		head.next = next;
		head.random = next;
		next.random = next;
		Node newHead = new CopyRandomList().copyRandomList(head);
		System.out.println(head);
		System.out.println(newHead);
	}

	public Node copyRandomList(Node head) {
		if (head == null) {
			return null;
		}

		Node cur = head;
		// 1、遍历原链表，插入复制节点
		while (cur != null) {
			Node copy = new Node(cur.val);
			copy.next = cur.next;
			cur.next = copy;
			cur = copy.next;
		}

		// 2、改变复制节点的random指针
		cur = head;
		while (cur != null) {
			Node copyNode = cur.next;
			copyNode.random = cur.random == null ? null : cur.random.next;
			cur = copyNode.next;
		}

		// 3、断开复制节点与原链表的next指针
		cur = head;
		Node newHead2 = head.next;
		Node newHead = newHead2;
		while (cur != null) {
			cur.next = newHead.next;
			newHead.next = cur.next == null ? null : cur.next.next;
			newHead = newHead.next;
			cur = cur.next;
		}
		return newHead2;
	}
}
