/*
 * Copyright (c) Simon Knott 2018.
 */

public class BinarySearchTree<T extends ComparableContent<T>> {
  private T content;
  private BinarySearchTree<T> leftTree;
  private BinarySearchTree<T> rightTree;

  /**
   * Beans
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

  /*
   * Methods
   */

  /**
   * @return if this Tree is empty
   */
  public boolean isEmpty() {
    return content == null;
  }

  /**
   * Inserts a new Item into the tree, keeping it sorted
   * @param item to insert
   */
  public void insert(T item) {
    // rec-base
    if (this.isEmpty()) { // if this is dangling
      // insert item
      this.setContent(item);

      // create dangling trees
      this.leftTree = new BinarySearchTree<>();
      this.rightTree= new BinarySearchTree<>();
    }

    // rec-step
    if (item.isLess(getContent())) { // if item is lower than content
      leftTree.insert(item);
    } else if (getContent().isLess(item)) { // if item is higher than content
      rightTree.insert(item);
    }

    // last case: item is equal to content:
    // do nothing
  }

  /**
   * Helper Method
   * Recursively searchs a specific tree
   * @param tree to search through
   * @param item to search for
   * @return found tree, null if not found
   */
  private static <T extends ComparableContent<T>> BinarySearchTree<T> searchTree(BinarySearchTree<T> tree, T item) {
    // rec-base
    if (tree.isEmpty()) { // if this is dangling, item is not in tree
      return null;
    }

    // rec-step
    if (item.isLess(tree.getContent())) { // item is lower than content
      // search in left tree
      return searchTree(
        tree.getLeftTree(),
        item
      );
    } else if (tree.getContent().isLess(item)) { // item is bigger than content
      // search in right tree
      return searchTree(
        tree.getRightTree(),
        item
      );
    } else { // item is equal to content
      // found item
      return tree;
    }
  }

  /**
   * Searchs the tree for a given item in O(log n)
   * @param item to search for
   * @return found item; null if not in tree
   */
  public T search(T item) {
    // validation
    if (item == null) {
      return null;
    }

    // search
    BinarySearchTree<T> result = searchTree(this, item);

    if (result == null) { // not found
      return null;
    }

    // found
    return result.getContent();
  }

  /**
   * Helper method, finds subtree with biggest content lower than root.
   * Deletes that subtree and returns it's value.
   * @param tree to search
   * @return value found
   */
  private T leftStrategy(BinarySearchTree<T> tree) {
    BinarySearchTree<T> temp = tree;

    // step left
    temp = temp.getLeftTree();

    // step right until not possible
    while (
      !temp.getRightTree().isEmpty() && // right tree exists
      !temp.getRightTree().getRightTree().isEmpty() // right tree is not a leaf
    ) {
      temp = temp.getRightTree();
    }

    T value;
    if (temp.getRightTree().isEmpty()) { // item is direct child
      value = temp.getContent();

      // remove
      temp.setContent(null);
    } else {
      value = temp.getRightTree().getContent();

      // remove
      temp.getRightTree().setContent(null);
    }

    // return found Value
    return value;
  }

  /**
   * Helper method, finds subtree with smallest content bigger than root.
   * Deletes that subtree and returns it's value.
   * @param tree to search
   * @return value found
   */
  private T rightStrategy(BinarySearchTree<T> tree) {
    BinarySearchTree<T> temp = tree;

    // step right
    temp = temp.getRightTree();

    // step left until not possible
    while (
      !temp.getLeftTree().isEmpty() && // left tree exists
      !temp.getLeftTree().getLeftTree().isEmpty() // left tree is not a leaf
    ) {
      temp = temp.getLeftTree();
    }

    T value;
    if (temp.getLeftTree().isEmpty()) { // item is direct child
      value = temp.getContent();

      // remove
      temp.setContent(null);
    } else {
      value = temp.getLeftTree().getContent();

      // remove
      temp.getLeftTree().setContent(null);
    }

    // return found value
    return value;
  }

  /**
   * Removes item if it's in the tree
   * keeps the tree sorted
   * @param item to remove
   */
  public void remove(T item) {
    // validation
    if (item == null) {
      return;
    }

    BinarySearchTree<T> tree = searchTree(this, item);
    if (tree == null) { // item not found
      return;
    }

    if (!tree.getLeftTree().isEmpty()) { // left tree exists
      // set content to biggest lower than current
      tree.setContent(leftStrategy(tree));
    } else if (!tree.getRightTree().isEmpty()) { // right tree exits
      // set content to lowest bigger than current
      tree.setContent(rightStrategy(tree));
    } else { // leaf
      // remove item
      tree.setContent(null);
    }
  }
}
