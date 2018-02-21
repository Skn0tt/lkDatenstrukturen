/*
 * Copyright (c) Simon Knott 2018.
 */

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
    public boolean isGreater(Content than) {
      return content > than.content;
    }

    @Override
    public boolean isEqual(Content than) {
      return content == than.content;
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
    new Random()
      .ints(100, 0, 1000)
      .forEach(i -> tree.insert(new Content(i)));
  }

  @Test
  public void testInsert() throws Exception {
    assertTrue(isSorted(tree));
  }

  @Test
  public void testSearch() throws Exception {
    new Random()
      .ints(10, -1000, 2000)
      .forEach(i -> {
        tree.insert(new Content(i));
        Content result = tree.search(new Content(i));
        assertNotNull(result);
      });
  }

  @Test
  public void testRemove() throws Exception {
    new Random()
      .ints(10, -1000, 2000)
      .forEach(i -> {
        tree.remove(new Content(i));
        Content result = tree.search(new Content(i));
        assertNull(result);
      });
  }

  private <T extends ComparableContent<T>> boolean isSorted(BinarySearchTree<T> tree) {
    if (tree.isEmpty()) {
      return true;
    }

    return (
      tree.getContent().isGreater(tree.getLeftTree().getContent()) &&
      tree.getContent().isLess(tree.getRightTree().getContent()) &&
      isSorted(tree.getLeftTree()) &&
      isSorted(tree.getRightTree())
    );
  }
}