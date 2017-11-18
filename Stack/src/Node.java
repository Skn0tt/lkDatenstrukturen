import com.sun.istack.internal.Nullable;

/**
 * Created by simon.knott on 17.11.2017.
 */
public class Node<T> {
    private T content;
    private Node<T> next;

  /**
   *
   * @param content
   */
  public Node(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Nullable
    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
