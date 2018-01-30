/*
 * Copyright (c) Simon Knott 2018.
 */

import java.util.List;
import java.util.stream.Collectors;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 25.11.2015
  * @author B. Reichelt
  */

public class TreeOutput {
  
  public static <T> String preOrder (BinaryTree<T> pTree) {
    StringBuilder b = new StringBuilder();
    List<T> items = Traverse.preOrder(pTree);
    return items
      .stream()
      .map(Object::toString)
      .collect(
        Collectors.joining("\n")
      );
  }
  public static <T> String inOrder (BinaryTree<T> pTree) {
    StringBuilder b = new StringBuilder();
    List<T> items = Traverse.inOrder(pTree);
    return items
      .stream()
      .map(Object::toString)
      .collect(
        Collectors.joining("\n")
      );
  }
  public static <T> String postOrder (BinaryTree<T> pTree) {
    StringBuilder b = new StringBuilder();
    List<T> items = Traverse.postOrder(pTree);
    return items
      .stream()
      .map(Object::toString)
      .collect(
        Collectors.joining("\n")
      );
  }

  public static <T> int getNodeCount(BinaryTree<T> pTree) {
    if (pTree.isEmpty()) { return 0; }
    return getNodeCount(pTree.getLeftTree()) + getNodeCount(pTree.getRightTree()) + 1;
  }
  public static <T> int getDepth(BinaryTree<T> pTree) {
    if (pTree.isEmpty()) { return 0; }

    int max = 0;

    final int leftDepth = getDepth(pTree.getLeftTree());
    final int righDepth = getDepth(pTree.getLeftTree());

    if (leftDepth > max) { max = leftDepth; }
    if (righDepth > max) { max = righDepth; }

    return righDepth + 1;
  }

  public static void main(String... args) {
    BinaryTree<String> leftLeftSubtree = new BinaryTree<>("d");
    BinaryTree<String> leftRightSubtree = new BinaryTree<>("e");
    BinaryTree<String> leftSubtree = new BinaryTree<>("b", leftLeftSubtree, leftRightSubtree);

    BinaryTree<String> rightLeftSubtree = new BinaryTree<>("f");
    BinaryTree<String> rightRightSubtree = new BinaryTree<>("g");
    BinaryTree<String> rightSubtree = new BinaryTree<>("c", rightLeftSubtree, rightRightSubtree);

    BinaryTree<String> tree = new BinaryTree<>("a", leftSubtree, rightSubtree);

    System.out.println("preOrder:");
    System.out.println(preOrder(tree));
    System.out.println("inOrder:");
    System.out.println(inOrder(tree));
    System.out.println("postOrder:");
    System.out.println(postOrder(tree));
    System.out.println("nodeCount:");
    System.out.println(getNodeCount(tree));
    System.out.println("depth:");
    System.out.println(getDepth(tree));
  }
} // end of class TreeOutput
