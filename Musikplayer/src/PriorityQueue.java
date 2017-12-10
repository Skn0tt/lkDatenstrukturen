public class PriorityQueue<ContentType> {
  // private Klasse fuer ein Element der Schlange
  private class PriorityNode {
    private ContentType content;
    private PriorityNode nextNode;
    private final int priority;

    public PriorityNode(ContentType content, int priority) {
      this.content = content;
      this.priority = priority;
    }

    public void setNext(PriorityNode pNext) {
      nextNode = pNext;
    }

    public PriorityNode getNext() {
      return nextNode;
    }

    public ContentType getContent() {
      return content;
    }

    public int getPriority() {
      return priority;
    }
  } // Ende private Klasse fuer ein Element

  // Attribute der Klasse Queue
  private PriorityNode head;
  private PriorityNode tail;

  // Methoden der Klasse Queue

  /**
   * Eine leere Schlange wird erzeugt. Objekte, die in dieser Schlange
   * verwaltet werden, muessen vom Typ ContentType sein.
   */
  public PriorityQueue() {
    head = null;
    tail = null;
  }

  /**
   * Die Anfrage liefert den Wert true, wenn die Schlange keine Objekte
   * enthaelt, sonst liefert sie den Wert false.
   *
   * @return true, falls die Schlange leer ist, sonst false
   */
  public boolean isEmpty() {
    return head == null;
  }

  /**
   * Das Objekt pContent wird an die Schlange angehaengt. Falls
   * pContent gleich null ist, bleibt die Schlange unveraendert.
   *
   * @param pContent das anzuhaengende Objekt
   */
  public void enqueue(ContentType pContent, int priority) {
    if (pContent == null) return;

    PriorityNode newNode = new PriorityNode(pContent, priority);

    // Case: Queue ist leer
    if (isEmpty()) {
      head = newNode;
      tail = newNode;
    }
    // Case: newNode muss an den Schluss
    else if (priority == 0 || newNode.getPriority() <= tail.getPriority()){
      tail.setNext(newNode);
      tail = newNode;
    }
    // Case: newNode muss an den Beginn
    else if (newNode.getPriority() > head.getPriority()) {
      newNode.setNext(head);
      head = newNode;
    }
    // Default Case
    else {
      PriorityNode node = head;

      // Node finden, deren Nachfolger eine kleinere Prio als `newNode` hat
      while (node.getNext().getPriority() >= newNode.getPriority()) node = node.getNext();

      // `newNode` zwischen `node` und `node.getNext()` platzieren
      newNode.setNext(node.getNext());
      node.setNext(newNode);
    }
  }

  /**
   * Das erste Objekt wird aus der Schlange entfernt. Falls die Schlange
   * leer ist, wird sie nicht veraendert.
   */
  public void dequeue() {
    if (!this.isEmpty()) {
      head = head.getNext();
    }
  }

  /**
   * Die Anfrage liefert das erste Objekt der Schlange. Die Schlange
   * bleibt unveraendert. Falls die Schlange leer ist, wird null zurueckgegeben.
   *
   * @return das erste Objekt der Schlange oder null, falls
   * die Schlange leer ist.
   */
  public ContentType front() {
    if (this.isEmpty()) {
      return null;
    } else {
      return head.getContent();
    }
  }

  public static void main(String... args) {
    PriorityQueue<String> queue = new PriorityQueue<>();

    for (int i = 10; i < 100; i += 3) queue.enqueue("Prio " + i, i);

    queue.enqueue("Prio 32", 32);
    queue.enqueue("Prio 32 #2", 32);
    queue.enqueue("Prio 32 #3", 32);
    print(queue);
  }

  private static void print(PriorityQueue queue) {
    System.out.println("----");
    while (!queue.isEmpty()) {
      System.out.println(queue.front());
      queue.dequeue();
    }
  }
}
