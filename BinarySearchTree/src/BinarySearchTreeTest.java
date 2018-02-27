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

  BinarySearchTree<Content> treeA;

  @BeforeEach
  void setUp() {
    treeA = new BinarySearchTree<>();
    treeA.insert(new Content(5));
    treeA.insert(new Content(3));
    treeA.insert(new Content(6));
    treeA.insert(new Content(1));
    treeA.insert(new Content(4));
  }

  @Test
  void testSetUp() {
    assertEquals(treeA.getContent().content, 5);
    assertEquals(treeA.getLeftTree().getContent().content, 3);
    assertEquals(treeA.getLeftTree().getLeftTree().getContent().content, 1);
    assertEquals(treeA.getLeftTree().getRightTree().getContent().content, 4);
    assertEquals(treeA.getRightTree().getContent().content, 6);
  }

  @Test
  public void testInsertRandom() {
    new Random()
      .ints(1000, -1000, 2000)
      .forEach(i -> {
        treeA.insert(new Content(i));
        assertTrue(isSorted(treeA));
      });
    assertTrue(isSorted(treeA));
  }

  @Test
  public void testSearchRandom() {
    assertNull(treeA.search(new Content(5000)));

    new Random()
      .ints(1000, -1000, 2000)
      .forEach(i -> {
        treeA.insert(new Content(i));
        Content result = treeA.search(new Content(i));
        assertNotNull(result);
      });
  }

  @Test
  public void testSearchLeaf() {
    assertEquals(treeA.search(new Content(4)).content, 4);
  }

  @Test
  public void testSearchNotAvailable() {
    assertNull(treeA.search(new Content(2)));
  }

  @Test
  public void testSearchInner() {
    assertEquals(treeA.search(new Content(3)).content, 3);
  }

  @Test
  public void testSearchRoot() {
    assertEquals(treeA.search(new Content(5)).content, 5);
  }

  @Test
  public void testRemoveRandom() {
    new Random()
      .ints(1000, -1000, 2000)
      .forEach(i -> {
        treeA.remove(new Content(i));
        Content result = treeA.search(new Content(i));
        assertNull(result);
      });
  }

  @Test
  public void testRemoveLeaf() {
    treeA.remove(new Content(4));
    assertEquals(treeA.getContent().content, 5);
    assertEquals(treeA.getLeftTree().getContent().content, 3);
    assertEquals(treeA.getLeftTree().getLeftTree().getContent().content, 1);
    assertNull(treeA.getLeftTree().getRightTree().getContent());
  }

  @Test
  public void testRemoveRoot() {
    treeA.remove(new Content(5));
    assertEquals(treeA.getContent().content, 4);
    assertEquals(treeA.getLeftTree().getContent().content, 3);
    assertEquals(treeA.getLeftTree().getLeftTree().getContent().content, 1);
    assertEquals(treeA.getRightTree().getContent().content, 6);
  }

  @Test
  public void testRemoveInner() {
    treeA.remove(new Content(3));
    assertEquals(treeA.getContent().content, 5);
    assertEquals(treeA.getLeftTree().getContent().content, 1);
    assertEquals(treeA.getLeftTree().getRightTree().getContent().content, 4);
    assertNull(treeA.getLeftTree().getLeftTree());
    assertEquals(treeA.getRightTree().getContent().content, 6);
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
    assertTrue(isSorted(treeA));
  }

  private <T extends ComparableContent<T>> boolean isSorted(BinarySearchTree<T> tree) {
    if (tree.isEmpty()) {
      return true;
    }

    if (tree.getLeftTree() != null) {
      if (tree.getLeftTree().getContent() != null && tree.getContent().isLess(tree.getLeftTree().getContent())) {
        return false;
      }
      if (!isSorted(tree.getLeftTree())) {
        return false;
      }
    }

    if (tree.getRightTree() != null) {
      if (tree.getRightTree().getContent() != null && tree.getRightTree().getContent().isLess(tree.getContent())) {
        return false;
      }
      if (!isSorted(tree.getRightTree())) {
        return false;
      }
    }

    return true;
  }
}