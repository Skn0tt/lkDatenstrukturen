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
    if (this.isEmpty()) {
      setContent(item);
    }

    if (item.isLess(getContent())) {
      if (getLeftTree() == null) {
        leftTree = new BinarySearchTree<>();
      }

      leftTree.insert(item);
    } else if (content.isLess(item)) {
      if (getRightTree() == null) {
        rightTree = new BinarySearchTree<>();
      }
      rightTree.insert(item);
    }
  }

  private static <T extends ComparableContent<T>> BinarySearchTree<T> searchTree(BinarySearchTree<T> tree, T item) {
    if (item == null) {
      return null;
    }

    if (tree == null) {
      return null;
    }

    if (tree.isEmpty()) {
      return null;
    }

    if (item.isLess(tree.getContent())) {
      return searchTree(tree.getLeftTree(), item);
    } else if (tree.getContent().isLess(item)) {
      return searchTree(tree.getRightTree(), item);
    } else {
      return tree;
    }
  }

  public T search(T item) {
    BinarySearchTree<T> result = searchTree(this, item);

    if (result == null) {
      return null;
    }

    return result.getContent();
  }

  private T leftStrategy(BinarySearchTree<T> tree) {
    BinarySearchTree<T> temp = tree;

    // Step Left
    temp = temp.getLeftTree();

    BinarySearchTree<T> previous = tree;
    // Step Right until not possible
    while (temp.getRightTree() != null) {
      previous = temp;
      temp = temp.getRightTree();
    }

    if (previous != tree) {
      // Cleanup
      previous.rightTree = null;
    } else {
      tree.leftTree = null;
    }

    // Return found Value
    return temp.getContent();
  }

  private T rightStrategy(BinarySearchTree<T> tree) {
    BinarySearchTree<T> temp = tree;

    // Step Right
    temp = temp.getRightTree();

    BinarySearchTree<T> previous = tree;
    // Step Left Until not possible
    while (temp.getLeftTree() != null) {
      previous = temp;
      temp = temp.getLeftTree();
    }

    if (previous != tree) {
      // Cleanup
      previous.leftTree= null;
    } else {
      tree.rightTree = null;
    }

    // Return found Value
    return temp.getContent();
  }

  public void remove(T item) {
    if (item == null) {
      return;
    }

    BinarySearchTree<T> tree = searchTree(this, item);
    if (tree == null) {
      return;
    }

    if (tree.getLeftTree() != null) {
      tree.setContent(leftStrategy(tree));
    } else if (tree.getRightTree() != null) {
      tree.setContent(rightStrategy(tree));
    } else {
      tree.setContent(null);
    }
  }
}
