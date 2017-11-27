import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

/**
 * Created by simon.knott on 17.11.2017.
 */
public class Stack<ContentType> {
    /*
     * Attributes
     */
    private Node<ContentType> head;

    /*
     * # Constructor
     */

    /**
     * Constructs an empty Stack.
     */
    Stack() {}

    /*
     * # Interface
     */
    /**
     * ## Standard
     */

    public void push(@NotNull ContentType value) {
      if (value == null) return;

      Node<ContentType> node = new Node<>(value);
      node.setNext(head);
      this.head = node;
    }

    /**
     * Pop the first value off the stack
     * @return first value
     */
    @Nullable
    public void pop() {
        if (isEmpty()) return;

        head = head.getNext();
    }

    /**
     * Get Value of top Node without removing it
     * @return null if empty
     */
    @Nullable
    public ContentType top() {
        if (isEmpty()) return null;

        return head.getContent();
    }

    /**
     * Checks if the stack is empty
     * @return true if empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /*
     * ## Additional
     */

    /**
     * Returns null if Node not existent.
     * @param index
     * @return Node<ContentType> or null
     */
    public ContentType get(int index) {
        return getNode(index).getContent();
    }

    /**
     * Sets the value of the given node.
     * @param index of node
     * @param value to set
     */
    public void set(int index, ContentType value) {
        getNode(index).setContent(value);
    }

    /**
     * Inserts a new value so that it is at the given index.
     * @param index of the new value
     * @param value to inset
     */
    public void insert(int index, ContentType value) {
        if (index == 0) {
            push(value);
            return;
        }

        Node<ContentType> previous = getNode(index - 1);
        Node<ContentType> node = new Node<>(value);

        node.setNext(previous.getNext());
        previous.setNext(node);
    }

    /**
     * Gets the length of the stack
     * @return length
     */
    public int length() {
        Node<ContentType> node = head;

        int i = 0;
        while(node.getNext() != null) {
            node = node.getNext();
            i++;
        }

        return i;
    }

    /*
     * # Helper Methods
     */
    /**
     * Get Node at given index
     * @param index to get
     * @return Node
     */
    private Node<ContentType> getNode(int index) {
        Node<ContentType> node = head;

        for (int i = 0; i < index; i++) {
            if (node == null) return null;

            node = node.getNext();
        }

        return node;
    }
}
