package algo.hot100;

import base.ListNode;
import utils.Utils;

public class P02_TwoNumbers {

    public static void main(String[] args) {
        ListNode h1 = new ListNode(2);
        h1.next = new ListNode(4);
        h1.next.next = new ListNode(3);

        ListNode h2 = new ListNode(5);
        h2.next = new ListNode(6);
        h2.next.next = new ListNode(4);
        ListNode res = new P02_TwoNumbers().addTwoNumbers(h1, h2);
        Utils.printListNode(res);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;
            int val = l1Val + l2Val;
            int cur = val + carry;

            carry = cur / 10;
            ListNode node = new ListNode(cur % 10);
            cursor.next = node;
            cursor = node;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return root.next;
    }

    public  ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) return null;
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        ListNode root = new ListNode(0);
        ListNode cursor = root;

        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;
            int val = l1Val + l2Val + carry;

            carry = val / 10;
            int cur = val % 10;
            ListNode node = new ListNode(cur);
            cursor.next = node;
            cursor = node;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return root.next;
    }
}
