/*
 * Copyright (c) Simon Knott 2018.
 */

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BinaryTreeTest {

  /*
   * isEmpty()
   */
  @Test
  public void testIsEmpty() throws Exception {
    BinaryTree<Integer> tree = new BinaryTree<>(5);
    assertFalse(tree.isEmpty());
  }

  @Test
  public void testIsEmptyOnHasEmptySubtrees() throws Exception {
    BinaryTree<Integer> tree = new BinaryTree<>(5, new BinaryTree<>(), new BinaryTree<>());
    assertFalse(tree.isEmpty());
  }

  @Test
  public void testIsEmptyOnHasSubtrees() throws Exception {
    BinaryTree<Integer> tree = new BinaryTree<>(5, new BinaryTree<>(8), new BinaryTree<>(5));
    assertFalse(tree.isEmpty());
  }

  @Test
  public void testIsEmptyOnEmpty() throws Exception {
    BinaryTree<Integer> tree = new BinaryTree<>();
    assertTrue(tree.isEmpty());
  }

  /*
   * setContent()
   */
  @Test
  public void testSetContent() throws Exception {
    BinaryTree<Integer> tree = new BinaryTree<>(5);
    assertEquals(tree.getContent().intValue(), 5);

    tree.setContent(6);
    assertEquals(tree.getContent().intValue(), 6);
  }

  @Test
  public void testSetNull() throws Exception {
    BinaryTree<Integer> tree = new BinaryTree<>(5);
    assertEquals(tree.getContent().intValue(), 5);

    tree.setContent(null);
    assertNull(tree.getContent());
  }

  @Test
  public void testSetSubtree() throws Exception {
    BinaryTree<Integer> tree = new BinaryTree<>(5, new BinaryTree<>(6), new BinaryTree<>(7));
    assertEquals(tree.getLeftTree().getContent().intValue(), 6);

    tree.getLeftTree().setContent(10);
    assertEquals(tree.getLeftTree().getContent().intValue(), 10);
  }

  /*
   * getLeftTree() / getRightTree()
   */
  @Test
  public void testGetLeftRightTree() {
    BinaryTree<Integer> left = new BinaryTree<>(2);
    BinaryTree<Integer> right = new BinaryTree<>(null);
    BinaryTree<Integer> root = new BinaryTree<>(1, left, right);

    assertEquals(root.getContent().intValue(), 1);
    assertEquals(root.getLeftTree().getContent().intValue(), 2);
    assertNull(root.getRightTree().getContent());

    assertSame(root.getRightTree(), right);
    assertSame(root.getLeftTree(), left);
  }

  @Test
  public void testGetLeftRightTreeOnLeaf() {
    BinaryTree<Integer> root = new BinaryTree<>(1);

    assertNull(root.getLeftTree().getContent());
    assertTrue(root.getRightTree().isEmpty());

    assertNull(root.getRightTree().getContent());
    assertTrue(root.getRightTree().isEmpty());
  }

  /*
   * setLeftTree() / setRightTree()
   */
  @Test
  public void testSetLeftRightTree() {
    BinaryTree<Integer> tree = new BinaryTree<>();

    assertTrue(tree.isEmpty());
    assertNull(tree.getLeftTree());
    assertNull(tree.getRightTree());

    BinaryTree<Integer> left = new BinaryTree<>(1);
    BinaryTree<Integer> right = new BinaryTree<>(2);
    tree.setLeftTree(left);
    tree.setRightTree(right);

    assertEquals(tree.getLeftTree().getContent().intValue(), 1);
    assertEquals(tree.getRightTree().getContent().intValue(), 2);
    assertTrue(tree.isEmpty());
    assertSame(tree.getLeftTree(), left);
    assertSame(tree.getRightTree(), right);
  }
}