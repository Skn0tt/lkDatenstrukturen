import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
  /**
   * Checks push()
   */
  @Test
  public void testPush1() {
    Stack<String> stack = new Stack<>();

    stack.push("100");
  }

  /**
   * Checks massive push()
   */
  @Test
  public void testPush2() {
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < 1000000; i++) stack.push(i);
  }

  /**
   * Checks push() null
   */
  @Test
  public void testPush3() {
    Stack<String> stack = new Stack<>();

    stack.push("1");
    stack.push(null);

    assertEquals(stack.top(), "1");
  }

  /**
   * Checks pop()
   */
  @Test
  public void testPop1() {
    Stack<String> stack = new Stack<>();

    stack.push("1");
    stack.push("2");

    stack.pop();

    assertEquals(stack.top(), "1");
  }

  /**
   * Checks pop() of empty Stack
   */
  @Test
  public void testPop2() {
    Stack<Integer> stack = new Stack<>();

    stack.pop();
  }

  /**
   * Checks massive pop()
   */
  @Test
  public void testPop3() {
    Stack<String> stack = new Stack<>();

    for (int i = 0; i <= 100000; i++) stack.push("" + i);

    for (int i = 100000; i > 0; i--) {
      assertEquals(stack.top(), "" + i);
      stack.pop();
    }
  }

  /**
   * Checks top()
   */
  @Test
  public void testTop1() {
    Stack<String> stack = new Stack<>();

    stack.push("1");

    assertEquals(stack.top(), "1");
  }

  /**
   * Checks top() on empty Stack
   */
  @Test
  public void testTop2() {
    Stack<String> stack = new Stack<>();

    assertNull(stack.top());
  }

  /**
   * Checks isEmpty() on empty Stack
   */
  @Test
  public void testIsEmpty1() {
    Stack<String> stack = new Stack<>();

    assertTrue(stack.isEmpty());
  }

  /**
   * Checks isEmpty() on non-empty Stack
   */
  @Test
  public void testIsEmpty2() {
    Stack<String> stack = new Stack<>();
    stack.push("Test");

    assertFalse(stack.isEmpty());
  }
}
