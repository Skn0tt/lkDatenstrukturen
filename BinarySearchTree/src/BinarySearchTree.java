/*
 * Copyright (c) Simon Knott 2018.
 */

public class BinarySearchTree<T extends ComparableContent<T>> {
  private T content;
  private BinarySearchTree<T> leftTree;
  private BinarySearchTree<T> rightTree;

  /**
   * Getter / Setter
   */
  public T getContent() {
    return content;
  }

  public BinarySearchTree<T> getLeftTree() {
    return leftTree;
  }

  public BinarySearchTree<T> getRightTree() {
    return rightTree;
  }

  private void setContent(T item) {
    this.content = item;
  }

  /**
   * Methods
   */
  public boolean isEmpty() {
    return content == null;
  }

  public void insert(T item) {
    if (item == null) {
      return;
    }

    if (this.isEmpty()) {
      setContent(item);
    }

    if (content.isEqual(item)) {
      return;
    }

    if (item.isLess(content)) {
      if (leftTree == null) {
        leftTree = new BinarySearchTree<>();
      }

      leftTree.insert(item);
    }

    if (rightTree == null) {
      rightTree = new BinarySearchTree<>();
    }

    rightTree.insert(item);
  }

  private BinarySearchTree<T> searchTree(T item) {
    if (item == null) {
      return null;
    }

    if (isEmpty()) {
      return null;
    }

    if (content.isEqual(item)) {
      return this;
    }

    BinarySearchTree<T> leftResult = leftTree.searchTree(item);
    if (leftResult != null) {
      return leftResult;
    }

    BinarySearchTree<T> rightResult = rightTree.searchTree(item);
    if (rightResult!= null) {
      return rightResult;
    }

    return null;
  }

  public T search(T item) {
    return searchTree(item).getContent();
  }

  public void remove(T item) {
    if (item == null) {
      return;
    }

    BinarySearchTree<T> found = searchTree(item);
    if (found == null) {
      return;
    }

    BinarySearchTree<T> temp = this;
    // Step Right
    temp = temp.rightTree;

    // Step left until no leftChild
    while (temp.leftTree != null) {
      temp = temp.leftTree;
    }

    // Set to Current
    content = temp.getContent();

    // Remove
    temp.content = null;
  }
}

interface ComparableContent<T> {
  boolean isGreater(T than);
  boolean isEqual(T than);
  boolean isLess(T than);
}
