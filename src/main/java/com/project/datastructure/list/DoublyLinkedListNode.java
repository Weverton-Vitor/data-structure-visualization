package com.project.datastructure.list;

public class DoublyLinkedListNode {

        private int value;
        private DoublyLinkedListNode next;
        private DoublyLinkedListNode previous;

        public DoublyLinkedListNode() {
        }

        public DoublyLinkedListNode(int value) {
            this.value = value;
        }

        public DoublyLinkedListNode(int value, DoublyLinkedListNode next) {
            this.value = value;
            this.next = next;
        }

        public DoublyLinkedListNode(int value, DoublyLinkedListNode next, DoublyLinkedListNode previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public DoublyLinkedListNode getNext() {
            return next;
        }

        public void setNext(DoublyLinkedListNode next) {
            this.next = next;
        }

        public DoublyLinkedListNode getPrevious() {
            return previous;
        }

        public void setPrevious(DoublyLinkedListNode previous) {
            this.previous = previous;
        }


}
