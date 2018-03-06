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

  /**
   * checks setup
   */
  @Test
  void testSetUp() {
    assertEquals(5, tree.getContent().content);
    assertEquals(3, tree.getLeftTree().getContent().content);
    assertEquals(1, tree.getLeftTree().getLeftTree().getContent().content);
    assertEquals(4, tree.getLeftTree().getRightTree().getContent().content);
    assertEquals(6, tree.getRightTree().getContent().content);
  }

  /**
   * insert random items
   */
  @Test
  void testInsertRandom() {
    new Random()
      .ints(1000, -1000, 2000)
      .forEach(i -> {
        tree.insert(new Content(i));

        // should be sorted
        assertTrue(isSorted(tree));
      });
    assertTrue(isSorted(tree));
  }

  /**
   * search random items
   */
  @Test
  void testSearchRandom() {
    assertNull(tree.search(new Content(5000)));

    new Random()
      .ints(1000, -1000, 2000)
      .forEach(i -> {
        // insert item
        Content item = new Content(i);
        tree.insert(item);

        // should be found
        assertNotNull(tree.search(new Content(i)));

        // should be equal
        assertEquals(tree.search(item).content, i);
      });
  }

  /**
   * search a leaf
   */
  @Test
  void testSearchLeaf() {
    // should be found
    assertEquals(4, tree.search(new Content(4)).content);
  }

  /**
   * search an item that's not in tree
   */
  @Test
  void testSearchNotAvailable() {
    // should not be found
    assertNull(tree.search(new Content(2)));
  }

  /**
   * search inner node
   */
  @Test
  void testSearchInner() {
    // should be found
    assertEquals(3, tree.search(new Content(3)).content);
  }

  /**
   * search root item
   */
  @Test
  void testSearchRoot() {
    // should be found
    assertEquals(5, tree.search(new Content(5)).content);
  }

  /**
   * randomly remove items
   */
  @Test
  void testRemoveRandom() {
    new Random()
      .ints(1000, -1000, 2000)
      .forEach(i -> {
        // remove
        tree.remove(new Content(i));

        // should not be found afterwards
        assertNull(tree.search(new Content(i)));
      });
  }

  /**
   * remove leaf item
   */
  @Test
  void testRemoveLeaf() {
    tree.remove(new Content(4));
    assertEquals(5, tree.getContent().content);
    assertEquals(3, tree.getLeftTree().getContent().content);
    assertEquals(1, tree.getLeftTree().getLeftTree().getContent().content);
    assertTrue(tree.getLeftTree().getRightTree().isEmpty());
  }

  /**
   * remove root item
   */
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

  /**
   * remove root item just right branch
   */
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

  /**
   * remove an inner item with left and right branch
   */
  @Test
  void testRemoveInner() {
    tree.remove(new Content(3));
    assertEquals(5, tree.getContent().content);
    assertEquals(1, tree.getLeftTree().getContent().content);
    assertEquals(4, tree.getLeftTree().getRightTree().getContent().content);
    assertTrue(tree.getLeftTree().getLeftTree().isEmpty());
    assertEquals(6, tree.getRightTree().getContent().content);
  }

  /**
   * remove item with just a right branch
   */
  @Test
  void testRemoveOnOnlyRightBranch() {
    BinarySearchTree<Content> tree = new BinarySearchTree<>();
    tree.insert(new Content(4));
    tree.insert(new Content(5));
    tree.insert(new Content(6));

    tree.remove(new Content(4));
  }

  /**
   * remove root
   */
  @Test
  void testRemoveOnOnlyRoot() {
    BinarySearchTree<Content> tree = new BinarySearchTree<>();
    tree.insert(new Content(4));

    tree.remove(new Content(4));
  }

  /**
   * remove item with just a left branch
   */
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

  /**
   * assert that tree is always sorted.
   */
  @AfterEach
  void testSorted() {
    assertTrue(isSorted(tree));
  }

  /**
   * Checks a tree for being sorted
   * @param tree to check
   * @param <T> must be comparable to T
   * @return true if sorted
   */
  private static <T extends ComparableContent<T>> boolean isSorted(BinarySearchTree<T> tree) {
    // rec-base
    if (tree.isEmpty()) {
      return true;
    }

    if (
      !tree.getLeftTree().isEmpty() &&
      tree.getContent().isLess(tree.getLeftTree().getContent())
    ) {
      return false;
    }

    if (
      !tree.getRightTree().isEmpty() &&
      tree.getRightTree().getContent().isLess(tree.getContent())
    ) {
      return false;
    }

    return (
      isSorted(tree.getLeftTree()) &&
      isSorted(tree.getRightTree())
    );
  }
}