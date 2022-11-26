package com.project.datastructure.tree;

public class Tree {

  private Node root;

  private void printInOrder(Node node) {
    if (node == null) return;

    this.printInOrder(node.getLeft());
    System.out.print(" " + node.getValue());
    this.printInOrder(node.getRight());
  }

  private void printPreOrder(Node node) {
    if (node == null) return;

    System.out.print(" " + node.getValue());
    this.printInOrder(node.getLeft());
    this.printInOrder(node.getRight());
  }

  private void printPostOrder(Node node) {
    if (node == null) return;

    this.printInOrder(node.getLeft());
    this.printInOrder(node.getRight());
    System.out.print(" " + node.getValue());
  }
  private void printInOrder() {
    if (this.empty()) return;

    this.printInOrder(this.root);
  }

  private void printPreOrder() {
    if (this.empty()) return;

    this.printPreOrder(this.root);
  }

  private void printPostOrder() {
    if (this.empty()) return;

    this.printPostOrder(this.root);
  }

  public boolean empty() {
    return this.root == null;
  }

  private Node find(Node node, int value) {
    if (node == null) {
      return null;
    }

    if (node.getValue() == value) {
      return node;
    }

    if (value > node.getValue())
      return this.find(node.getRight(), value);
    else
      return  this.find(node.getLeft(), value);
  }

  public boolean add(int value) {
    Node node = new Node(value);

    if (this.empty()) {
      this.root = node;
      return true;
    }

    Node son = this.root;
    Node root = null;
    while (son != null) {
      root = son;
      if (value == son.getValue()) {
        return false;
      }

      if (value > son.getValue()) {
        son = son.getRight();
      } else {
        son = son.getLeft();
      }
    }

    if (root == null) return false;

    if (value > root.getValue())
      root.setRight(node);
    else
      root.setLeft(node);

    return true;
  }

  public Node find(int value) {
    if(this.empty()) return null;

    return find(this.root, value);
  }

  public void print() {
    System.out.println();
    this.printInOrder();
  }

  public void print(Type type) {
    System.out.println();
    switch (type) {
      case INORDER -> this.printInOrder();
      case PREORDER -> this.printPreOrder();
      case POSTORDER -> this.printPostOrder();
    }
  }

}
