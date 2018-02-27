/*
 * Copyright (c) Simon Knott 2018.
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class BinarySearchTreeTest {
  class Content implements ComparableContent<Content> {
    int content;

    public Content(int i) {
      this.content = i;
    }

    @Override
    public boolean isLess(Content than) {
      return content < than.content;
    }

    @Override
    public String toString() {
      return "" + content;
    }
  }

  BinarySearchTree<Content> tree;

  @BeforeEach
  void setUp() {
    tree = new BinarySearchTree<>();
    tree.insert(new Content(5));
    tree.insert(new Content(3));
    tree.insert(new Content(6));
    tree.insert(new Content(1));
    tree.insert(new Content(4));
  }

  @Test
  void testSetUp() {
    assertEquals(tree.getContent().content, 5);
    assertEquals(tree.getLeftTree().getContent().content, 3);
    assertEquals(tree.getLeftTree().getLeftTree().getContent().content, 1);
    assertEquals(tree.getLeftTree().getRightTree().getContent().content, 4);
    assertEquals(tree.getRightTree().getContent().content, 6);
  }

  @Test
  public void testInsertRandom() {
    new Random()
      .ints(1000, -1000, 2000)
      .forEach(i -> {
        tree.insert(new Content(i));
        assertTrue(isSorted(tree));
      });
    assertTrue(isSorted(tree));
  }

  @Test
  public void testSearchRandom() {
    assertNull(tree.search(new Content(5000)));

    new Random()
      .ints(1000, -1000, 2000)
      .forEach(i -> {
        tree.insert(new Content(i));
        Content result = tree.search(new Content(i));
        assertNotNull(result);
      });
  }

  @Test
  public void testRemoveRandom() {
    new Random()
      .ints(1000, -1000, 2000)
      .forEach(i -> {
        tree.remove(new Content(i));
        Content result = tree.search(new Content(i));
        assertNull(result);
      });
  }

  @Test
  public void testRemoveOnOnlyLeftBranch() {
    BinarySearchTree<Content> tree = new BinarySearchTree<>();
    tree.insert(new Content(6));
    tree.insert(new Content(5));
    tree.insert(new Content(4));

    tree.remove(new Content(6));
  }

  @Test
  public void testRemoveOnOnlyRightBranch() {
    BinarySearchTree<Content> tree = new BinarySearchTree<>();
    tree.insert(new Content(4));
    tree.insert(new Content(5));
    tree.insert(new Content(6));

    tree.remove(new Content(4));
  }

  @Test
  public void testRemoveOnOnlyRoot() {
    BinarySearchTree<Content> tree = new BinarySearchTree<>();
    tree.insert(new Content(4));

    tree.remove(new Content(4));
  }

  @AfterEach
  public void testSorted() {
    assertTrue(isSorted(tree));
  }

  private <T extends ComparableContent<T>> boolean isSorted(BinarySearchTree<T> tree) {
    if (tree.isEmpty()) {
      return true;
    }

    if (tree.getLeftTree() != null) {
      if (tree.getContent().isLess(tree.getLeftTree().getContent())) {
        return false;
      }
      if (!isSorted(tree.getLeftTree())) {
        return false;
      }
    }

    if (tree.getRightTree() != null) {
      if (tree.getRightTree().getContent().isLess(tree.getContent())) {
        return false;
      }
      if (!isSorted(tree.getRightTree())) {
        return false;
      }
    }

    return true;
  }
}