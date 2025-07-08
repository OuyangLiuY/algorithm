package linkedlist;

import base.ListNode;
import utils.Utils;

/**
 * 链表反转，根据给定的l和r。
 * https://leetcode.cn/problems/reverse-linked-list-ii/
 */
public class P92_ReverseBetween {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println();
        Utils.printListNode(new P92_ReverseBetween().reverseBetween(head, 2, 4));
    }

    /**
     * 流程：
     * 1.先定位到需要转化的left的前一个位置。的链表 pre
     * 2.然后将pre反转，
     *      那么start是原链表的头部，反转后变成了尾部
     *      prev最终指向了反转后的子链表头部
     * 3.重新链接链表，
     *      pre.next = prev, 将前驱节点链接到反转后的字链表头部
     *      end.next = curr, 将链表尾部链接到剩余未反转的部分。
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre =  pre.next;
        }
        ListNode start = pre.next;  //子链表开始位置
        ListNode cur = start;
        ListNode end = start;   // 没有特别大的最用，只是为了便于理解，在反转之后需要链接的位置。
        ListNode prev = null;   // 用于反转子链表, 反转之后prev代表的就是后面4节点，需要跟pre接起来。

        for (int i = 0; i < right - left + 1; i++) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        // 此时，cur来到了未反转之前的4号节点的下一个位置。
        // 那么此时已经拿到 反转之后需要接起来的位置所代表的节点，prev，
        // 剩余未被反转的节点：cur
        // 那么只需要跟 start 链接起来就好
        pre.next = prev;    // pre.next 指向反转后的子链表头部
        end.next = cur;  // 子链表的尾部连接剩余部分
        return dummy.next;
    }
}
