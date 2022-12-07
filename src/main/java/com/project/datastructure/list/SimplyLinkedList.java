package com.project.datastructure.list;

public class SimplyLinkedList {
    private SimplyLinkedListNode head;
    private int length = 0;

    public SimplyLinkedList() {
    }

    public SimplyLinkedList(SimplyLinkedListNode firstNode) {
        this.head = firstNode;
    }

    public boolean vazia(){
        if(this.length == 0)return true;
        else return false;
    }

    public SimplyLinkedListNode getHead() {
        return head;
    }

    public void setHead(SimplyLinkedListNode head) {
        this.head = head;
    }

    public int getLength() {
        return length;
    }

    public void add(int value) {
        if (this.head == null) {
            this.head = new SimplyLinkedListNode(value);
        } else {
            SimplyLinkedListNode auxNode = this.head;
            while (auxNode.getNext() != null){ // busca o ultimo item, last -> obj.getNext() == null
                auxNode = auxNode.getNext();
            }
            auxNode.setNext(new SimplyLinkedListNode(value));
        }
        this.length += 1;
    }

    public boolean insert(int value, int position) {
        if (position <= 0 || position > length+1) { // Pemite que a posição seja uma undade maior que o tamnho da lista
            return false;
        }
        SimplyLinkedListNode previousNode = null;
        SimplyLinkedListNode posteriorNode = null;

        SimplyLinkedListNode auxNode = this.head;
//        for(int i = 1; i <= position; i++) { // Definindo os nós de antes e depois
//            if ((i + 1) == position) {
//                previousNode = auxNode;
//            }
//
//            if ((i + 1) > position) {
//                posteriorNode = auxNode;
//            }
//            if (i == position) {
////                System.out.println(auxNode.getValue());
//            }
////            System.out.println(auxNode.getValue());
//
////            System.out.println(previousNode.getValue());
////            System.out.println(posteriorNode.getValue());
//            auxNode = auxNode.getNext();
//            if (auxNode == null)
//                break;
//        }

        for(int i = 1; i <= position-1; i++) { // Definindo os nós de antes e depois
            if (i == position-1) {
                previousNode = auxNode;
            }

            auxNode = auxNode.getNext();
            if (auxNode == null)
                break;
        }
        if (previousNode != null) {
            posteriorNode = previousNode.getNext();
        } else {
            posteriorNode = this.head;
        }



        if ((previousNode != null) && (posteriorNode != null)) {
            previousNode.setNext(new SimplyLinkedListNode(value, posteriorNode)); // usando o segundo construtor para informar qual nó sera o posterior do novo nó
            System.out.println(previousNode.getValue() + " (" + value + ") " + posteriorNode.getValue());
        } else if ((previousNode == null)) { // Primeira posição
            this.head = new SimplyLinkedListNode(value, posteriorNode); // usando o segundo construtor para informar qual nó sera o posterior do novo nó
            System.out.println( "- (" + value + ") " + posteriorNode.getValue());

        } else { // Ultima posição
            this.add(value);
            this.length -= 1;
            System.out.println(previousNode.getValue() + " (" + value + ") -");
        }

        this.length += 1;

        return true;

    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("[");
        SimplyLinkedListNode auxNode = this.head;
        while (auxNode.getNext() != null){ // Percorre a lista até o penultimo item
            s.append(auxNode.getValue());
            s.append(", ");

            auxNode = auxNode.getNext();
        }
        s.append(auxNode.getValue());
        s.append("]");
        return s.toString();
    }

    public int searchByPosition(int position) {
        if (position <= 0 || position > length) {
            return -1;
        }

        if (this.length  <= 0)
            return -1;

        SimplyLinkedListNode auxNode = this.head;
        for(int i = 2; i <= position; i++) { // Se a posição for 1 não entra no loop
            auxNode = auxNode.getNext();
        }

        return auxNode.getValue();
    }

    public int searchByValue(int value) {

        SimplyLinkedListNode auxNode = this.head;
        for(int i = 1; i <= length; i++) {
            if (auxNode.getValue() == value) {
                return i;
            }
            auxNode = auxNode.getNext();
        }

        return -1;
    }
    
    
    public boolean remove(int position) {
        SimplyLinkedListNode copy = this.getHead();

        if (this.length == 1) {
            this.head = null;
            this.length--;
            return true;
        }

        if (position == 1)
            this.head = copy.getNext();

        if (position > this.length || position <= 0)
            return false;

        for (int i = 2; i < position; i++) {
            copy = copy.getNext();
        }

        copy.setNext(copy.getNext().getNext());

        this.length--;
        return true;
    }

}
