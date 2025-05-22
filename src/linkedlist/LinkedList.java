package linkedlist;

import base.DoubleNodeList;
import base.ListNode;

import static utils.Utils.printListNode;

public class LinkedList {


    public static ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        ListNode cur = head;
        ListNode pre = null;
        ListNode next = null;
        while (cur != null) {
            cur = cur.next;

            cur.next = pre;

            pre = cur;

            cur = next;
        }
        return pre;
    }

    public static DoubleNodeList reverseDoubleList(DoubleNodeList head){
        if(head == null)
            return null;
        DoubleNodeList next = null;
        DoubleNodeList pre = null;
        DoubleNodeList cur = head;
        while (cur != null){
            next = cur.next;

            cur.next = pre;
            cur.pre = next;

            pre = cur;
            cur = next;
        }
        return cur;
    }

    public static DoubleNodeList reverseDoubleList2(DoubleNodeList head){
        if(head == null)
            return null;
        DoubleNodeList tmp = null;
        DoubleNodeList cur = head;
        while (cur != null){
            tmp = cur.next;

            cur.next = cur.pre;
            cur.pre = tmp;
            cur = cur.pre;
        }
        return tmp;
    }



    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);


        printListNode(node);
        node = reverseList(node);
        printListNode(node);

        DoubleNodeList node1 = new DoubleNodeList(1);
        DoubleNodeList node2 = new DoubleNodeList(2);
        DoubleNodeList node3 = new DoubleNodeList(3);

        node1.next = node2;
        node1.pre = null;

        node2.next = node3;
        node2.pre = node1;


        node3.next = null;
        node3.pre = node2;

        printListNode(node1);
        node1 = reverseDoubleList(node1);
        printListNode(node1);

//        reverseDoubleList2()
    }
}
