import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

/**
 * Created by simon.knott on 17.11.2017.
 */
public class Node<ContentType> {
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
