/*
 * Copyright (c) Simon Knott 2018.
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class BinarySearchTreeTest {
  class Content implements ComparableContent<Content> {
    int content;

    Content(int i) {
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

  private BinarySearchTree<Content> tree;

  @BeforeEach
  void setUp() {
    tree = new BinarySearchTree<>();
    tree.insert(new Content(5));
    tree.insert(new Content(3));
    tree.insert(new Content(6));
    tree.insert(new Content(1));
    tree.insert(new Content(4));
    tree.insert(new Content(9));
    tree.insert(new Content(7));
    tree.insert(new Content(10));
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
  void testInsertRandom() {
    new Random()
      .ints(1000, -1000, 2000)
      .forEach(i -> {
        tree.insert(new Content(i));
        assertTrue(isSorted(tree));
      });
    assertTrue(isSorted(tree));
  }

  @Test
  void testSearchRandom() {
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
  void testSearchLeaf() {
    assertEquals(tree.search(new Content(4)).content, 4);
  }

  @Test
  void testSearchNotAvailable() {
    assertNull(tree.search(new Content(2)));
  }

  @Test
  void testSearchInner() {
    assertEquals(tree.search(new Content(3)).content, 3);
  }

  @Test
  void testSearchRoot() {
    assertEquals(tree.search(new Content(5)).content, 5);
  }

  @Test
  void testRemoveRandom() {
    new Random()
      .ints(1000, -1000, 2000)
      .forEach(i -> {
        tree.remove(new Content(i));
        Content result = tree.search(new Content(i));
        assertNull(result);
      });
  }

  @Test
  void testRemoveLeaf() {
    tree.remove(new Content(4));
    assertEquals(tree.getContent().content, 5);
    assertEquals(tree.getLeftTree().getContent().content, 3);
    assertEquals(tree.getLeftTree().getLeftTree().getContent().content, 1);
    assertTrue(tree.getLeftTree().getRightTree().isEmpty());
  }

  @Test
  void testRemoveRootLeft() {
    tree.remove(new Content(5));
    assertEquals(tree.getContent().content, 4);
    assertEquals(tree.getLeftTree().getContent().content, 3);
    assertEquals(tree.getLeftTree().getLeftTree().getContent().content, 1);
    assertEquals(tree.getRightTree().getContent().content, 6);
  }

  @Test
  void testRemoveRootRight() {
    tree = tree.getRightTree();
    tree.remove(new Content(6));

    assertEquals(tree.getContent().content, 7);
    assertEquals(tree.getRightTree().getContent().content, 9);
    assertEquals(tree.getRightTree().getRightTree().getContent().content, 10);
    assertTrue(tree.getRightTree().getLeftTree().isEmpty());
  }

  @Test
  void testRemoveInner() {
    tree.remove(new Content(3));
    assertEquals(tree.getContent().content, 5);
    assertEquals(tree.getLeftTree().getContent().content, 1);
    assertEquals(tree.getLeftTree().getRightTree().getContent().content, 4);
    assertTrue(tree.getLeftTree().getLeftTree().isEmpty());
    assertEquals(tree.getRightTree().getContent().content, 6);
  }

  @Test
  void testRemoveOnOnlyLeftBranch() {
    BinarySearchTree<Content> tree = new BinarySearchTree<>();
    tree.insert(new Content(6));
    tree.insert(new Content(5));
    tree.insert(new Content(4));

    tree.remove(new Content(6));
  }

  @Test
  void testRemoveOnOnlyRightBranch() {
    BinarySearchTree<Content> tree = new BinarySearchTree<>();
    tree.insert(new Content(4));
    tree.insert(new Content(5));
    tree.insert(new Content(6));

    tree.remove(new Content(4));
  }

  @Test
  void testRemoveOnOnlyRoot() {
    BinarySearchTree<Content> tree = new BinarySearchTree<>();
    tree.insert(new Content(4));

    tree.remove(new Content(4));
  }

  @AfterEach
  void testSorted() {
    assertTrue(isSorted(tree));
  }

  private <T extends ComparableContent<T>> boolean isSorted(BinarySearchTree<T> tree) {
    if (tree.isEmpty()) {
      return true;
    }

    if (!tree.getLeftTree().isEmpty() && tree.getContent().isLess(tree.getLeftTree().getContent())) {
      return false;
    }

    if (!tree.getRightTree().isEmpty() && tree.getRightTree().getContent().isLess(tree.getContent())) {
      return false;
    }

    return isSorted(tree.getLeftTree()) && isSorted(tree.getRightTree());
  }
}