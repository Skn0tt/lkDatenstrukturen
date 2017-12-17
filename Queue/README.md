# Queue
*Queue*

- Dynamische Listenstruktur
- FIFO (First in, First Out)
  - Werden schneller Elemente hinzugefügt als entfernt, so werden alle Elemente berücksichtigt

## Modellierung
Zwei Klassen:

- Queue
  - Kennt *erstes* (`head`), *letztes* Element (`tail`)

## Algorithmen
### Standard
Es gibt vier Standard-Methoden, die eine Queue charakterisieren.

#### isEmpty()
Gibt zurück, ob der Stack leer ist.

```java
return head == null;
```
#### front()
Gibt den Inhalt des ersten Elements zurück.

```java
if (isEmpty()) return null;
return head.getContent();
```
#### enqueue()
Hängt der Queue ein neues Element `element` an;

```java
if (isEmpty()) {
  this.head = node;
  this.tail = node;
} else {
  tail.setNext(node);
  this.tail = node;
}
```
Unterscheidung:
- Leere Schlange:
  - Element ist neuer `head` und `tail`
- Befüllte Schlange
  - alter `tail` zeigt auf Element
  - Element ist neuer `tail`

Je nach Modellierung ist es notwendig, den eingefügten Wert auf Zulässigkeit zu überprüfen.


#### dequeue()
Entfernt das erste Element.

```java
if (isEmpty()) return;

head = head.getNext();
```
- Das Element nach `head` ist neuer `head`

# PriorityQueue()
In der PriorityQueue wird jedem Element beim einfügen eine Priorität zugeordnet, nach der es in der Queue einsortiert wird.
Dabei gilt:
- $p \in \mathbb{N}$
- Haben zwei Elemente die gleiche Priorität, wird das ältere bevorzugt

Dafür muss man die Node um den `int`-Wert `priority` erweitern, dieser lässt sich mti `getPriority()` abrufen.
Außerdem muss die `enqueue()` Methode angepasst werden:

```java
void enqueue(ContentType content, int priority) {
  if (content == null) return;
  PriorityNode newNode = new PriorityNode(content, priority);

  // Fall 1: Queue ist leer
  if (isEmpty()) {
    head = newNode;
    tail = newNode;
  }
  // Fall 2: newNode muss an den Schluss
  else if (newNode.getPriority() <= tail.getPriority()){
    tail.setNext(newNode);
    tail = newNode;
  }
  // Fall 3: newNode muss an den Beginn
  else if (newNode.getPriority() > head.getPriority()) {
    newNode.setNext(head);
    head = newNode;
  }
  // Default: newNode muss in die Mitte
  else {
    PriorityNode node = head;

    // Node finden, deren Nachfolger eine kleinere Prio als `newNode` hat
    while (node.getNext().getPriority() >= newNode.getPriority()) node = node.getNext();

    // `newNode` zwischen `node` und `node.getNext()` platzieren
    newNode.setNext(node.getNext());
    node.setNext(newNode);
  }
}
```
*Fall 1*, die Schlange ist leer, sowie *Fall 2*, die Node muss ans Ende der Queue, sind identisch mit der normalen Queue.
In *Fall 3*, die Node muss an den Anfang, wird analog zum Stack verfahren.

```java
PriorityNode node = head;

while (node.getNext().getPriority() >= newNode.getPriority()) node = node.getNext();

newNode.setNext(node.getNext());
node.setNext(newNode);
```
Interessant ist *Fall 4*:  
Mit der `while`-Schleife hangelt man sich, von `head` beginnend (Z. 1), solange durch alle Nodes bis man die Node findet, deren Nachfolger eine kleinere Priorität als die einzufügende Node besitzt (Z. 3).
Denn: Zwischen dieser Node `node` und der nachfolgenden Node `node.getNext()` musss die neue Node `newNode` einsortiert werden.

Zuerst wird der Pointer der `newNode` auf die `node.getNext()` umgebogen (Z. 5).
Dann wird der Pointer der `node` auf die `newNode` umgebogen (Z. 6).


# Nutzungsszenarien

- Hilfreich, um Prozesse zu modellieren
- Beispiel:
  - Wartezimmer
  - Becherschlange bei Starbucks
- Multithreading
  - Mehrere Aufgaben werden in der Schlange abgelegt
  - Jeder Worker kann sich welche davon nehmen
