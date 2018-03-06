/*
 * Copyright (c) Simon Knott 2018.
 */

public class BinaryTree<ContentType> {

  /**
   * Attributes
   */

  private ContentType content;
  private BinaryTree<ContentType> leftTree;
  private BinaryTree<ContentType> rightTree;

  /**
   * Constructors
   */

  public BinaryTree() {}

  public BinaryTree(ContentType content) {
    if (content == null) { return; }

    this.content = content;

    this.leftTree = new BinaryTree<>();
    this.rightTree = new BinaryTree<>();
  }

  public BinaryTree(ContentType content, BinaryTree<ContentType> leftTree, BinaryTree<ContentType> rightTree) {
    if (content == null) { return; }

    this.content = content;

    if (leftTree == null) { this.leftTree = new BinaryTree<>(); }
    else { this.leftTree = leftTree; }

    if (rightTree == null) { this.rightTree = new BinaryTree<>(); }
    else { this.rightTree = rightTree; }
  }

  /**
   * Methods
   */
  boolean isEmpty() {
    return this.content == null;
  }

  /**
   * Getters / Setters
   */

  public ContentType getContent() {
    return content;
  }

  public void setContent(ContentType content) {
    this.content = content;
  }

  public BinaryTree<ContentType> getLeftTree() {
    return leftTree;
  }

  public void setLeftTree(BinaryTree<ContentType> leftTree) {
    if (leftTree == null) {
      return;
    }
    if (this.isEmpty()) {
      return;
    }
    this.leftTree = leftTree;
  }

  public BinaryTree<ContentType> getRightTree() {
    return rightTree;
  }

  public void setRightTree(BinaryTree<ContentType> rightTree) {
    if (rightTree == null) {
      return;
    }
    if (this.isEmpty()) {
      return;
    }

    this.rightTree = rightTree;
  }
}
