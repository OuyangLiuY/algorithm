package linkedlist;

import base.ListNode;
import utils.Utils;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 * 删除链表的倒数第 N 个结点
 */
public class P19_RemoveNthFromEnd {
    public static void main(String[] args) {
        var n1 = new ListNode(1);
        n1.next = new ListNode(2);
        n1.next.next = new ListNode(3);
        n1.next.next.next = new ListNode(4);
        n1.next.next.next.next = new ListNode(5);

        ListNode listNode = new P19_RemoveNthFromEnd().removeNthFromEnd(n1, 2);
        Utils.printListNode(listNode);
        System.out.println();
    }

    //  使用双指针，来计算。
    // 快指针先走n步，然后跟慢指针一起走，
    // 快指针走完，那么说明慢指针到需要删除节点的上一个节点
    // a -> b -> c -> d -> e     n=1
    //                     f
    //                s
    // 此时，s.next = s.next.next
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if (fast == null) return dummy.next; // 假如只有一个元素的情况
        while (fast.next != null) { // 2个元素，n=1.正好要移除掉最后一个元素
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

}
