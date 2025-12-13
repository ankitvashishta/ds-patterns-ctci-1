package com.ankit.problems.chapter2.linkedlist;

public class DeleteGivenNode {

    /**
     * Space Complexity : O(1)
     * Time Complexity : O(1)
     * @param node - the node to delete
     * @return boolean - whether node is deleted or not. Last node cannot be deleted.
     */
    public static boolean deleteGivenNode(LinkedListNode node){
        if(node == null || node.next == null)
            return false;
        node.data = node.next.data;
        node.next = node.next.next;
        return true;
    }

    public static void main(String[] args){
        LinkedListNode head = initialiseLinkedList();
        LinkedListNode nodeToDelete = head.next.next;
        deleteGivenNode(nodeToDelete);
        printList(head);
    }

    private static LinkedListNode initialiseLinkedList() {
        LinkedListNode node = new LinkedListNode(1);
        node.next = new LinkedListNode(2);
        node.next.next = new LinkedListNode(3);
        node.next.next.next = new LinkedListNode(4);
        node.next.next.next.next = new LinkedListNode(5);
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
