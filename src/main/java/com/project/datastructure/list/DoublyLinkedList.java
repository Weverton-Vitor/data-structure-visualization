package com.project.datastructure.list;

public class DoublyLinkedList {

    private DoublyLinkedListNode head;
    private DoublyLinkedListNode tail;
    private int length;

    public DoublyLinkedList() {
    }

    public DoublyLinkedList(DoublyLinkedListNode head) {
        this.head = head;
        this.tail = head;
    }

    public boolean add(int value, int position) {
        DoublyLinkedListNode element = new DoublyLinkedListNode(value);
        if (position <= 0 || position > this.length + 1) {
            return false;
        }

        if (position == 1 && this.isEmpty()) { // First position in empty list
            this.head = element;
            this.tail = element;

            this.head.setPrevious(this.tail);
            this.tail.setNext(this.head);

            this.length++;
            return true;
        }

        if (position == 1 && !this.isEmpty()) { // First Position
            DoublyLinkedListNode headCopy = this.head;
            this.head = element;
            this.head.setNext(headCopy);

            headCopy.setPrevious(this.head);

            this.head.setPrevious(this.tail);
            this.tail.setNext(this.head);

            this.length++;

            return true;
        }

        if (position == this.length + 1) { // Last Position
            DoublyLinkedListNode tail_copy = this.tail;
            tail_copy.setNext(element);
            this.tail = element;
            this.tail.setPrevious(tail_copy);

            this.tail.setNext(this.head);
            this.head.setPrevious(this.tail);


            this.length++;

            return true;
        }

        DoublyLinkedListNode previous = this.head;
        for (int i = 2; i <= position - 1; i++) {
            previous = previous.getNext();
        }
        DoublyLinkedListNode next = previous.getNext();

        element.setNext(next);
        element.setPrevious(previous);

        previous.setNext(element);
        next.setPrevious(element);

        this.length++;

        return true;
    }

    public boolean remove(int position) {
        if (position <= 0 && position > this.length)
            return false;
        if (position == 1) {
            this.setHead(this.getHead().getNext());
            this.getHead().setPrevious(null);
        } else if (position == this.length) {
            this.setTail(this.getTail().getPrevious());
            this.getTail().setNext(null);
        } else {
            DoublyLinkedListNode copyNode = this.getHead();
            for (int i = 2; i <= position; i++) {
                copyNode = copyNode.getNext();
            }

            copyNode.getNext().setPrevious(copyNode.getPrevious());
            copyNode.getPrevious().setNext(copyNode.getNext());
        }

        this.length--;

        return true;
    }

    public int searchByPosition(int position) {
        if (position <= 0 || position > length) {
            return -1;
        }

        if (this.length  <= 0)
            return -1;

        DoublyLinkedListNode auxNode = this.head;
        for(int i = 2; i <= position; i++) { // Se a posição for 1 não entra no loop
            auxNode = auxNode.getNext();
        }

        return auxNode.getValue();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        DoublyLinkedListNode copy = this.head;
        for (int i = 1; i <= this.length; i++) {
            if (copy.getNext() != null)
                result.append(copy.getValue()).append(", ");
            else
                result.append(copy.getValue());
            copy = copy.getNext();
        }
        result.append("]");
        return result.toString();
    }

    public boolean isEmpty() {
        if (length >= 1) {
            return false;
        }

        return true;
    }

    public DoublyLinkedListNode getHead() {
        return head;
    }

    public void setHead(DoublyLinkedListNode head) {
        this.head = head;
    }

    public DoublyLinkedListNode getTail() {
        return tail;
    }

    public void setTail(DoublyLinkedListNode tail) {
        this.tail = tail;
    }

    public int getLength() {
        return length;
    }


}


