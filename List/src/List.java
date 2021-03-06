public class List<ContentType> {
  private class Node {
    private ContentType content;
    private Node next;

    Node(ContentType content) {
      this.content = content;
    }

    ContentType getContent() {
      return content;
    }

    void setContent(ContentType content) {
      this.content = content;
    }

    Node getNext() {
      return next;
    }

    void setNext(Node next) {
      this.next = next;
    }
  }

  Node head;
  Node tail;
  Node aktuell;

  /**
   * Returns wether List has Elements
   * @return true if head is pointing on element
   */
  boolean isEmpty() {
    return head == null;
  }

  /**
   * Returns wether List is pointing on current element
   * @return true if Aktuell is pointing on element
   */
  boolean hasAccess() {
    return aktuell != null;
  }

  /**
   * Moves Pointer to next Element
   */
  void next() {
    if (!hasAccess()) { return; }
    aktuell = aktuell.getNext();
  }

  /**
   * Moves Pointer to first element
   */
  void toFirst() {
    if (isEmpty()) { return; }

    aktuell = head;
  }

  /**
   * Moves Pointer to last element
   */
  void toLast() {
    if (isEmpty()) { return; }

    aktuell = tail;
  }

  /**
   * Returns Content of pointed Element
   * @return content, null if no Access
   */
  ContentType getContent() {
    if (!hasAccess()) return null;

    return aktuell.getContent();
  }

  /**
   * Sets Content of pointed Element
   * @param content to set
   */
  void setContent(ContentType content) {
    if (content == null) { return; }
    if (!hasAccess()) { return; }
    if (aktuell.getContent() == null) { return; }

    aktuell.setContent(content);
  }

  /**
   * Appends new Node to the end of the List
   * @param content to append
   */
  void append(ContentType content) {
    if (content == null) return;

    Node node = new Node(content);

    if (isEmpty()) {
      head = node;
      tail = node;
    } else {
      tail.setNext(node);
      tail = node;
    }
  }

  /**
   * Insert Element before pointed
   * @param content to insert
   */
  void insert(ContentType content) {
    if (content == null) { return; }
    if (!hasAccess() && !isEmpty()) { return; }
    if (!hasAccess() && isEmpty()) {
      append(content);
      return;
    }

    Node toInsert = new Node(content);

    if (aktuell == head) {
      toInsert.setNext(head);
      head = toInsert;
    } else {
      Node last = getNodeBefore(aktuell);

      toInsert.setNext(last.getNext());
      last.setNext(toInsert);
    }
  }

  private Node getNodeBefore(Node n) {
    if (n == null) { return null; }
    if (n == head) { return null; }
    if (this.isEmpty()) { return null; }

    Node node = head;
    while (node != null && node.getNext() != n) { node = node.getNext(); }
    return node;
  }

  /**
   * Concatenates another List
   * @param list
   */
  void concat(List<ContentType> list) {
    if (list == null) return;
    if (list.isEmpty()) return;

    if (this.isEmpty()) {
      this.head = list.head;
      this.tail = list.tail;
    } else {
      this.tail.setNext(list.head);
      this.tail = list.tail;
    }

    list.head = null;
    list.tail = null;
    list.aktuell = null;
  }

  /**
   * Removes pointed element, next element becomes pointed
   */
  void remove() {
    if (isEmpty()) { return; }
    if (!hasAccess()) { return; }

    if (aktuell == head) {
      head = head.getNext();
    } else {
      Node last = this.getNodeBefore(aktuell);
      if (aktuell == tail) { tail = last; }
      last.setNext(aktuell.getNext());
    }

    next();

    if (isEmpty()) { tail = null; }
  }

  public static void main(String... args) {
    List<Integer> list = new List<>();

    for (int i = 0; i < 10; i++) list.append(i);
    list.toFirst();

    for (int i = 0; i < 3; i++) list.next();


    list.remove();

    list.toFirst();
    while (!list.isEmpty() && list.hasAccess()) {
      System.out.println(list.getContent());
      list.next();
    }
  }
}
