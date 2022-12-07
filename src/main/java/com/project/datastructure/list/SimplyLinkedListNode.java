package com.project.datastructure.list;

public class SimplyLinkedListNode {
    private int value;
    private SimplyLinkedListNode next;

    public SimplyLinkedListNode(int value) {
        this.value = value;
    }

    public SimplyLinkedListNode(int value, SimplyLinkedListNode next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public SimplyLinkedListNode getNext() {
        return next;
    }

    public void setNext(SimplyLinkedListNode next) {
        this.next = next;
    }
}
