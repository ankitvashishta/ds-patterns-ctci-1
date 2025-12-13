package com.ankit.problems.chapter2.linkedlist;

public class ReturnKthToLast {

    private static int indexCounter = 0;

    /**
     * Space Complexity : O(N) due to recursive calls.
     * Time Complexity : O(N)
     * @param k - to find the kth element from the last
     * @param node - the head node
     * @return - the index
     */
    public static int getKthNodeFromLast_Recursion(int k, LinkedListNode node){
        if(node == null)
            return 0;
        int index = getKthNodeFromLast_Recursion(k, node.next) + 1;
        if(index == k){
            System.out.println("The value of Kth Node is : " + node.data);
        }
        return index;
    }

    /**
     * Space Complexity : O(N) due to recursive calls.
     * Time Complexity : O(N)
     * @param k - to find the kth element from the last
     * @param head - the head node
     * @return - the kth node
     */
    public static LinkedListNode getKthNodeFromLast_RecursionAndCounter(int k, LinkedListNode head){
        if(head == null)
            return null;
        LinkedListNode node = getKthNodeFromLast_RecursionAndCounter(k, head.next);
        indexCounter = indexCounter + 1;
        if(indexCounter == k){
            return head;
        }
        return node;
    }

    /**
     * Space Complexity : O(1) due to recursive calls.
     * Time Complexity : O(N)
     * @param k - to find the kth element from the last
     * @param head - the head node
     * @return - the kth node
     */
    public static LinkedListNode getKthNodeFromLast_Iteration(int k, LinkedListNode head){
        LinkedListNode current = head;
        LinkedListNode runner = head;

        for(int i = 0; i < k; i++){
            runner = runner.next;
        }

        while(runner != null){
            current = current.next;
            runner = runner.next;
        }
        return current;
    }

    public static void main(String[] args){
        LinkedListNode head = initialiseLinkedList();
        printList(head);
        getKthNodeFromLast_Recursion(2, head);

        LinkedListNode node = getKthNodeFromLast_RecursionAndCounter(2, head);
        System.out.println("The value of Kth Node is : " + node.data);

        LinkedListNode nodeFromIteration = getKthNodeFromLast_Iteration(2, head);
        System.out.println("The value of Kth Node is : " + nodeFromIteration.data);
    }

    private static LinkedListNode initialiseLinkedList() {
        LinkedListNode node = new LinkedListNode(1);
        node.next = new LinkedListNode(2);
        node.next.next = new LinkedListNode(3);
        node.next.next.next = new LinkedListNode(4);
        node.next.next.next.next = new LinkedListNode(5);
        node.next.next.next.next.next = new LinkedListNode(6);
        node.next.next.next.next.next.next = new LinkedListNode(7);
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
