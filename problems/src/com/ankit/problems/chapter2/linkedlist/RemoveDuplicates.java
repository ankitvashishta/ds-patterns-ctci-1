package com.ankit.problems.chapter2.linkedlist;

import java.util.HashSet;

public class RemoveDuplicates {

    /**
     * Space Complexity : O(N)
     * TIme Complexity : O(N)
     * @param node - the head node
     */
    public static void deleteDuplicates(LinkedListNode node) {
        HashSet<Integer> dataSet = new HashSet<>();
        LinkedListNode previous = null;

        while (node != null) {
            if (dataSet.contains(node.data)) {
                previous.next = node.next;
            } else {
                dataSet.add(node.data);
                previous = node;
            }
            node = node.next;
        }
    }

    /**
     * Space Complexity : O(1)
     * TIme Complexity : O(N^2)
     * @param node - the head node
     */
    public static void deleteDuplicates_noBuffer(LinkedListNode node) {
        LinkedListNode current = node;
        while (current != null) {
            LinkedListNode runner = current;
            while (runner.next != null) {
                if (current.data == runner.next.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    public static void main(String[] args) {
        LinkedListNode node = initialiseLinkedList();
        printList(node);
        deleteDuplicates(node);
        printList(node);

        System.out.println("************ No Bueffer ************");
        node = initialiseLinkedList();
        printList(node);
        deleteDuplicates_noBuffer(node);
        printList(node);

    }

    private static LinkedListNode initialiseLinkedList() {
        LinkedListNode node = new LinkedListNode(1);
        node.next = new LinkedListNode(2);
        node.next.next = new LinkedListNode(3);
        node.next.next.next = new LinkedListNode(4);
        node.next.next.next.next = new LinkedListNode(2);
        node.next.next.next.next.next = new LinkedListNode(3);
        node.next.next.next.next.next.next = new LinkedListNode(4);
        return node;
    }

    private static void printList(LinkedListNode node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }


}
