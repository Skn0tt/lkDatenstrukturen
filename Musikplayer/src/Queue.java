import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

/**
 * Created by simon.knott on 17.11.2017.
 */
public class Queue<ContentType> {
    private class Node<ContentType> {
        private ContentType content;
        private Node<ContentType> next;

        /**
         *
         * @param content
         */
        public Node(ContentType content) {
            this.content = content;
        }

        public ContentType getContent() {
            return content;
        }

        public void setContent(@NotNull ContentType content) {
            this.content = content;
        }

        @Nullable
        public Node<ContentType> getNext() {
            return next;
        }

        public void setNext(Node<ContentType> next) {
            this.next = next;
        }
    }
    /*
     * Attributes
     */
    private Node<ContentType> head;
    private Node<ContentType> tail;

    /*
     * # Constructor
     */

    /**
     * Constructs an empty Queue.
     */
    Queue() {}

    /*
     * # Interface
     */
    /**
     * ## Standard
     */

    public void enqueue(@NotNull ContentType value) {
      if (value == null) return;

      Node<ContentType> node = new Node<>(value);

      if (isEmpty()) {
        this.head = node;
        this.tail = node;
      } else {
        tail.setNext(node);
        this.tail = node;
      }
    }

    /**
     * Pop the first value off the stack
     * @return first value
     */
    @Nullable
    public void dequeue() {
        if (isEmpty()) return;

        head = head.getNext();
    }

    /**
     * Get Value of front Node without removing it
     * @return null if empty
     */
    @Nullable
    public ContentType front() {
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
}
