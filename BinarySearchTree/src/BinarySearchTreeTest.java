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
    public boolean isEqual(Content than) {
      return content == than.content;
    }

    @Override
    public boolean isGreater(Content than) {
      return content > than.content;
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
    tree.insert(new Content(11));
  }

  @Test
  void testSetUp() {
    assertEquals(5, tree.getContent().content);
    assertEquals(3, tree.getLeftTree().getContent().content);
    assertEquals(1, tree.getLeftTree().getLeftTree().getContent().content);
    assertEquals(4, tree.getLeftTree().getRightTree().getContent().content);
    assertEquals(6, tree.getRightTree().getContent().content);
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
    assertEquals(4, tree.search(new Content(4)).content);
  }

  @Test
  void testSearchNotAvailable() {
    assertNull(tree.search(new Content(2)));
  }

  @Test
  void testSearchInner() {
    assertEquals(3, tree.search(new Content(3)).content);
  }

  @Test
  void testSearchRoot() {
    assertEquals(5, tree.search(new Content(5)).content);
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
    assertEquals(5, tree.getContent().content);
    assertEquals(3, tree.getLeftTree().getContent().content);
    assertEquals(1, tree.getLeftTree().getLeftTree().getContent().content);
    assertTrue(tree.getLeftTree().getRightTree().isEmpty());
  }

  @Test
  void testRemoveRoot() {
    tree.remove(new Content(5));
    assertTrue(tree.getLeftTree().getRightTree().isEmpty());
    assertNull(tree.getLeftTree().getRightTree().getRightTree());
    assertNull(tree.getLeftTree().getRightTree().getLeftTree());
    assertEquals(4, tree.getContent().content);
    assertEquals(3, tree.getLeftTree().getContent().content);
    assertEquals(1, tree.getLeftTree().getLeftTree().getContent().content);
    assertEquals(6, tree.getRightTree().getContent().content);
  }

  @Test
  void testRemoveRootRight() {
    BinarySearchTree<Content> localTree = tree.getRightTree();
    localTree.remove(new Content(6));

    assertEquals(9, localTree.getContent().content);
    assertEquals(7, localTree.getLeftTree().getContent().content);
    assertTrue(localTree.getLeftTree().getLeftTree().isEmpty());
    assertTrue(localTree.getLeftTree().getRightTree().isEmpty());
    assertNull(localTree.getLeftTree().getRightTree().getLeftTree());
    assertNull(localTree.getLeftTree().getRightTree().getRightTree());
    assertNull(localTree.getLeftTree().getLeftTree().getLeftTree());
    assertNull(localTree.getLeftTree().getLeftTree().getRightTree());
    assertEquals(10, localTree.getRightTree().getContent().content);
    assertEquals(11, localTree.getRightTree().getRightTree().getContent().content);
    assertTrue(localTree.getRightTree().getLeftTree().isEmpty());
  }

  @Test
  void testRemoveInner() {
    tree.remove(new Content(3));
    assertEquals(5, tree.getContent().content);
    assertEquals(1, tree.getLeftTree().getContent().content);
    assertEquals(4, tree.getLeftTree().getRightTree().getContent().content);
    assertTrue(tree.getLeftTree().getLeftTree().isEmpty());
    assertEquals(6, tree.getRightTree().getContent().content);
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

  @Test
  void testRemoveOnOnlyLeft() {
    tree.remove(new Content(10));
    assertEquals(11, tree.getRightTree().getRightTree().getRightTree().getContent().content);
    assertTrue(tree.getRightTree().getRightTree().getRightTree().getRightTree().isEmpty());
  }

  @Test
  void testRemoveOnOnlyLeft2() {
    tree.remove(new Content(6));
    assertEquals(9, tree.getRightTree().getContent().content);
    assertEquals(7, tree.getRightTree().getLeftTree().getContent().content);
    assertEquals(10, tree.getRightTree().getRightTree().getContent().content);
  }

  @AfterEach
  void testSorted() {
    assertTrue(isSorted(tree));
  }

  private static <T extends ComparableContent<T>> boolean isSorted(BinarySearchTree<T> tree) {
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