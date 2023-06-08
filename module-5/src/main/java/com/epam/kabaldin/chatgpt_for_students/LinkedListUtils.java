package com.epam.kabaldin.chatgpt_for_students;

public class LinkedListUtils {
    public static LinkedListNode findKthToLast(LinkedListNode head, int k) {
        if (head == null || k < 1) {
            return null;
        }

        LinkedListNode p1 = head;
        LinkedListNode p2 = head;

        for (int i = 0; i < k; i++) {
            if (p2 == null) {
                return null;
            }
            p2 = p2.next;
        }

        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        LinkedListNode node2 = new LinkedListNode(2);
        LinkedListNode node3 = new LinkedListNode(3);
        LinkedListNode node4 = new LinkedListNode(4);
        LinkedListNode node5 = new LinkedListNode(5);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        int k = 2;

        LinkedListNode result = findKthToLast(head, k);
        if (result != null) {
            System.out.println("The " + k + "th to last element is: " + result.data);
        } else {
            System.out.println("Invalid input or k is larger than the length of the list.");
        }
    }
}