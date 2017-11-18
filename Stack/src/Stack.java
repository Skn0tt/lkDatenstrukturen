/**
 * Created by simon.knott on 17.11.2017.
 */
public class Stack<T> {
    /*
     * Attributes
     */
    private Node<T> head;

    /*
     * # Constructor
     */
    /**
     * Constructs new Stack with one node.
     * @param head first node
     */
    Stack(Node<T> head) {
        this.head = head;
    }

    /**
     * Constructs an empty Stack.
     */
    Stack() {}

    /**
     * Constructs new Stack with one value.
     * @param value first value
     */
    Stack(T value) {
        this(new Node<>(value));
    }

    /*
     * # Interface
     */
    /**
     * ## Standard
     */

    public void push(T value) {
        Node<T> node = new Node<>(value);
        node.setNext(head);
        this.head = node;
    }

    /**
     * Pop the first value off the stack
     * @return first value
     */
    public T pop() {
        Node<T> n = head;
        head = head.getNext();
        return n.getContent();
    }

    /**
     * Get Value of top Node without removing it
     * @return
     */
    public T top() {
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
     * @return Node<T> or null
     */
    public T get(int index) {
        return getNode(index).getContent();
    }

    /**
     * Sets the value of the given node.
     * @param index of node
     * @param value to set
     */
    public void set(int index, T value) {
        getNode(index).setContent(value);
    }

    /**
     * Inserts a new value so that it is at the given index.
     * @param index of the new value
     * @param value to inset
     */
    public void insert(int index, T value) {
        if (index == 0) {
            push(value);
            return;
        }

        Node<T> previous = getNode(index - 1);
        Node<T> node = new Node<>(value);

        node.setNext(previous.getNext());
        previous.setNext(node);
    }

    /**
     * Gets the length of the stack
     * @return length
     */
    public int length() {
        Node<T> node = head;

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
    private Node<T> getNode(int index) {
        Node<T> node = head;

        for (int i = 0; i < index; i++) {
            if (node == null) return null;

            node = node.getNext();
        }

        return node;
    }
}
