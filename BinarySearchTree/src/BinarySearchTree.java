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

  private void setContent(T to) {
    this.content = to;
  }

  private void setLeftTree(BinarySearchTree<T> to) {
    this.leftTree = to;
  }

  private void setRightTree(BinarySearchTree<T> to) {
    this.rightTree = to;
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

  private static <T extends ComparableContent<T>> BinarySearchTree<T> findMax(BinarySearchTree<T> tree) {
    if (!tree.getRightTree().isEmpty()) {
      return tree.getRightTree();
    }
    return tree;
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

    if (
      tree.getLeftTree().isEmpty() && tree.getRightTree().isEmpty() // is Leaf
    ) {
      // just make this tree dangling
      tree.setContent(null);
      tree.setLeftTree(null);
      tree.setRightTree(null);
    } else if (
      !tree.getLeftTree().isEmpty() ^ !tree.getRightTree().isEmpty() // exactly one of left, right exists
    ) {
      // find existing one of left, right
      BinarySearchTree<T> existingTree =
          !tree.getLeftTree().isEmpty()
            ? tree.getLeftTree()
            : tree.getRightTree();

      // set current tree to existing tree
      tree.setContent(existingTree.getContent());
      tree.setLeftTree(existingTree.getLeftTree());
      tree.setRightTree(existingTree.getRightTree());
    } else { // left and right exists
      // find max in left tree
      BinarySearchTree<T> maxInLeftTree = findMax(tree.getLeftTree());

      // set current content to max in left tree
      // will keep the tree sorted
      tree.setContent(maxInLeftTree.getContent());

      // remove max in found tree
      maxInLeftTree.remove(maxInLeftTree.getContent());
    }
  }
}
