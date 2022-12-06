package com.project.datastructure.tree;

public class Tree {

  private Node root;
  private StringBuilder inOrder, preOrder, postOrder;

  public Tree() {
    this.inOrder = new StringBuilder();
    this.preOrder = new StringBuilder();
    this.postOrder = new StringBuilder();
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

  public int getHeigth(Node node){
    if (node == null)
      return 0;

    int leftHeigth = getHeigth(node.getLeft());
    int rightHeigth = getHeigth(node.getRight());
    if (leftHeigth > rightHeigth)
      return leftHeigth + 1;

    return rightHeigth + 1;
  }

  public void print() {
    System.out.println();
    System.out.println(walkInOrder());
  }

  public void print(Type type) {
    System.out.println();
    switch (type) {
      case INORDER -> System.out.println(this.walkInOrder());
      case PREORDER -> System.out.println(this.walkPreOrder());
      case POSTORDER -> System.out.println(this.walkPostOrder());
    }
  }

  private void walkInOrder(Node node) {
    if (node == null) return;

    this.walkInOrder(node.getLeft());
    this.inOrder.append(node.getValue()).append("  ");
    this.walkInOrder(node.getRight());
  }

  private String walkInOrder() {
    this.inOrder = new StringBuilder();
    if (this.empty()) return "";
    this.walkInOrder(this.root);
    return this.inOrder.toString();
  }

  private void walkPreOrder(Node node) {
    if (node == null) return;

    this.preOrder.append(node.getValue()).append("  ");
    this.walkPreOrder(node.getLeft());
    this.walkPreOrder(node.getRight());
  }

  private String walkPreOrder() {
    this.preOrder = new StringBuilder();
    if (this.empty()) return "";
    this.walkPreOrder(this.root);
    return this.preOrder.toString();
  }

  private void walkPostOrder(Node node) {
    if (node == null) return;

    this.walkPostOrder(node.getLeft());
    this.walkPostOrder(node.getRight());
    this.postOrder.append(node.getValue()).append("  ");
  }

  private String walkPostOrder() {
    this.postOrder = new StringBuilder();
    if (this.empty()) return "";
    this.walkPostOrder(this.root);
    return this.postOrder.toString();
  }

  public String walk(Type type) {
    System.out.println();
    switch (type) {
      case INORDER: return this.walkInOrder();
      case PREORDER: return this.walkPreOrder();
      case POSTORDER: return this.walkPostOrder();
      default: return "";
    }
  }

  public Node getRoot() {
    return root;
  }
}
