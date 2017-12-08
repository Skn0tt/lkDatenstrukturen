import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

/**
 * Created by simon.knott on 17.11.2017.
 */
public class PriorityQueue<ContentType> {
    private class PriorityNode<ContentType> {
        private final ContentType content;
        private PriorityNode<ContentType> next;
        private final int priority;

        /**
         *
         * @param content
         */
        public PriorityNode(ContentType content, int priority) {
            this.content = content;
            this.priority = priority >= 0 ? priority : 0;
        }

        public ContentType getContent() {
            return content;
        }

        public int getPriority() {
            return priority;
        }

        @Nullable
        public PriorityNode<ContentType> getNext() {
            return next;
        }

        public void setNext(PriorityNode<ContentType> next) {
            this.next = next;
        }
    }
    /*
     * Attributes
     */
    private PriorityNode<ContentType> head;
    private PriorityNode<ContentType> tail;

    /*
     * # Interface
     */
    /**
     * ## Standard
     */

    public void enqueue(@NotNull ContentType value, int priority) {
      if (value == null) return;

      PriorityNode<ContentType> node = new PriorityNode<>(value, priority);

      // Leere Schlange
      if (isEmpty()) {
        this.head = node;
        this.tail = node;
      } else if (node.priority == 0) {
        tail.setNext(node);
        tail = node;
      } else {
        PriorityNode<ContentType> temp = head;
        while (temp.getNext() != null && temp.getPriority() >= node.getPriority()) temp = temp.getNext();

        temp.setNext(node);
        node.setNext(temp.getNext());
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
     * Get Value of front PriorityNode without removing it
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

    public static void main(String... args) {
        PriorityQueue<String> queue = new PriorityQueue<>();

        queue.enqueue("Prio 0!", 100);
        queue.enqueue("Prio 1!", 10);

        while(!queue.isEmpty()) {
            System.out.println(queue.front());
            queue.dequeue();
        }
    }
}
