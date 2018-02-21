/*
 * Copyright (c) Simon Knott 2018.
 */

import java.util.ArrayList;
import java.util.List;

public class Traverse {

  /**
   * preOrder Traversing
   * @param tree
   * @param <T>
   * @return
   */
  static <T> List<T> preOrder(BinaryTree<T> tree) {
    return preOrder(tree, new ArrayList<T>());
  }

  private static <T> List<T> preOrder(BinaryTree<T> tree, List<T> values) {
    if (tree.isEmpty()) { return values; }

    values.add(tree.getContent());
    values.addAll(preOrder(tree.getLeftTree()));
    values.addAll(preOrder(tree.getRightTree()));

    return values;
  }

  /**
   * inOrder Traversing
   * @param tree
   * @param <T>
   * @return
   */
  static <T> List<T> inOrder(BinaryTree<T> tree) {
    return inOrder(tree, new ArrayList<T>());
  }

  private static <T> List<T> inOrder(BinaryTree<T> tree, List<T> values) {
    if (tree.isEmpty()) { return values; }

    values.addAll(inOrder(tree.getLeftTree()));
    values.add(tree.getContent());
    values.addAll(inOrder(tree.getRightTree()));

    return values;
  }

  /**
   * postOrder Traversing
   * @param tree
   * @param <T>
   * @return
   */
  static <T> List<T> postOrder(BinaryTree<T> tree) {
    return postOrder(tree, new ArrayList<T>());
  }

  private static <T> List<T> postOrder(BinaryTree<T> tree, List<T> values) {
    if (tree.isEmpty()) { return values; }

    values.addAll(postOrder(tree.getLeftTree()));
    values.addAll(postOrder(tree.getRightTree()));
    values.add(tree.getContent());

    return values;
  }

  private static void printList(List list) {
    for (Object o : list) { System.out.println(o.toString()); }
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
    printList(preOrder(tree));
    System.out.println("inOrder:");
    printList(inOrder(tree));
    System.out.println("postOrder:");
    printList(postOrder(tree));
  }
}
