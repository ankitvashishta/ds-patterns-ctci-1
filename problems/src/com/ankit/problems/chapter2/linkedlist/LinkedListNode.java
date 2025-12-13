package com.ankit.problems.chapter2.linkedlist;

public class LinkedListNode {

    int data;
    LinkedListNode next = null;

    LinkedListNode(int data) {
        this.data = data;
    }

    LinkedListNode(int data, LinkedListNode next) {
        this.data = data;
        this.next = next;
    }
}
