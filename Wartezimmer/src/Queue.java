import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

/**
 * Created by simon.knott on 17.11.2017.
 */
public class Queue<ContentType> {
    private class Node {
        private ContentType content;
        private Node next;

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
        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
    /*
     * Attributes
     */
    private Node head;
    private Node tail;

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

      Node node = new Node(value);

      if (isEmpty()) {
        this.head = node;
        this.tail = node;
      } else {
        tail.setNext(node);
        this.tail = node;
      }
    }

    @Nullable
    public void dequeue() {
        if (isEmpty()) return;

        head = head.getNext();
    }

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
