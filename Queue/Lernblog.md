# Queue
*Schlange*

Die Queue ist eine weitere Dynamische Listenstruktur.  
Sie ist nach einer Menschenschlange modelliert und befolgt deshalb das Prinzip *First in, First Out* (FIFO):
Möchte man ein neues Element anfügen, wird dieses hinten angehängt.  
Entfernt man ein Element weg, so entfernt man immer das vorderste.  
So kommt der Mensch, der sich zuerst anstellt, auch zuerst dran.

![Queue](https://firebasestorage.googleapis.com/v0/b/simonknott-de.appspot.com/o/Queue. png?alt=media&token=c151bc2a-c24d-4cab-8b3a-d2c289cc6d08 "Queue")

<!--more-->

## Modellierung

Die Queue wird, wie der [Stack](https://simonknott.de/articles/Stack), mit zwei Klassen modelliert:

![Modellierung Node](https://www.planttext.com/plantuml/svg/SoWkIImgAStDuKhEIImkLl3BJqbLgEPIKD1LSCxFAqdDAmcfB4Y5iO7YsWfb-Paf2iMf9Nwf5IKQcc0XiAvq525atK0PA1SeMSlba9gN0hGH0000 "UML-Diagramm Node")

Die Klasse Node kennen wir schon aus dem Stack: Sie speichert einen Wert des generischen Typs *ContentType* sowie einen Pointer auf die nächste Node, beides kann man über *Getter*-Methoden abfragen.

![Modellierung Queue](https://www.planttext.com/plantuml/svg/LOvD2i9038NtSueiA-8T2k9M4Bn0DAzq89tKJgPGnBix_a5zk-_b8xmsEqzU1WrNvPpfL510zq0rEpgQW7gmhDavwfktT35JSAAO3yFeIxFvzhD58QJRwrcpj-H8Vbv6qCnQy6S9FjQlNDlTLA5U7g55aZho2G00 "UML-Diagramm Queue")

Die Queue speichert einen Pointer auf das erste Element der Schlange `head` sowie einen Pointer auf das letzte Element `tail`.

Darüber hinaus bietet sie vier Methoden, mit der man mit ihr interagieren kann.
Diese sind im folgenden Beschrieben.

## Algorithmen
### Standard-Qeue
Es gibt vier Standard-Methoden, die eine Queue charakterisieren.

##### isEmpty()
Gibt zurück, ob der Stack leer ist.

```java
boolean isEmpty() {
  return head == null;
}
```
##### front()
Gibt den Inhalt des ersten Elements zurück.

```java
ContentType front() {
  if (isEmpty()) return null;
  return head.getContent();
}
```

Falls die Schlange leer ist, gibt es kein erstes Element.
Dann gibt die Methode `null` zurück (siehe Z. 2).

##### enqueue()
Diese Methode hängt der Queue ein neues Element mit dem Wert `value` an;

```java
void enqueue(ContentType value) {
  Node node = new Node(value);

  if (isEmpty()) {
    this.head = node;
    this.tail = node;
  } else {
    tail.setNext(node);
    this.tail = node;
  }
}
```

In Zeile 2 wird ein neues Element mit dem Wert `value` erzeugt.
Dann wird dieses Element angehängt, wobei zwei Fälle unterschieden werden müssen:

Ist die Schlange leer (Z. 4), so wird das Element der neue `head` (Z. 5) und der neue `tail` (Z. 6).

Ist die Schlange nicht leer, also schon befüllt (Z. 7), so muss das Element angehangen werden.
Dafür wird zuerst der Zeiger des aktuell letzten Elements, der akutell auf `null` zeigt, auf das neue Element umgebogen werden (Z. 8).
Dann wird der eigene Zeiger `tail` auf das neue Element - den neuen `tail` - umgebogen (Z. 9).

Je nach Modellierung ist es notwendig, den eingefügten Wert auf Zulässigkeit zu überprüfen.

#### dequeue()
Diese Methode entfernt das erste Element.

```java
void dequeue() {
  if (isEmpty()) return;

  head = head.getNext();
}
```
Falls die Schlange keine Elemente hat, also leer ist, kann auch kein Element entfernt werden. Dieser Fall wird in Z. 2 abgefangen.

Sonst wird einfach der eigene `head`-Pointer auf das Element nach dem aktuellen `head`, also das zweite Element in der Queue, umgebogen (Z. 4).
Dieses wird zum neuen `head`.

### PriorityQueue
Die PriorityQueue ist eine Erweiterung der Queue:

Jedem Element wird beim einfügen eine Priorität zugeordnet, unter Berücksichtigung derer es in der Queue einsortiert wird.
So kann man auch Prozesse modellieren, in denen nicht jedes Element gleichberechtigt ist.
Beispiel: Eine Schlange am Flughafen, bei der Platin-Kunden schneller einchecken dürfen.

Dabei gilt:
- $p \in \mathbb{N}$
- Haben zwei Elemente die gleiche Priorität, wird das ältere bevorzugt


![PriorityNode](https://www.planttext.com/plantuml/svg/SoWkIImgAStDuKhEIImkLWWeoimloYofzCjFILMevb800bq5v_oyajIyaf3AWbI5P0WRAfEPLw9G0DK25j9M0EjCJotnIwqeqT242fRbPwOe5AC98XiBr03bmgL1PWE5OIu2YjToEQJcfG0T2m00 "UML-Diagramm PriorityNode")

Dafür muss man die Klasse Node um den `int`-Wert `priority` erweitern, dieser lässt sich mit `getPriority()` abrufen.
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

Es gibt vier Fälle:
1. Die Schlange ist leer.
2. Das Element muss ans Ende der Queue.
3. Das Element muss an den Anfang der Queue.
4. Das Element muss zwischen Anfang und Ende.


```java
// Fall 1: Schlange ist leer
if (isEmpty()) {
  head = newNode;
  tail = newNode;
}
```
*Fall 1* ist identisch mit der normalen Queue.  

```java
// Fall 2: newNode muss an den Schluss
if (newNode.getPriority() <= tail.getPriority()){
  tail.setNext(newNode);
  tail = newNode;
}
```
*Fall 2* erkennt man daran, dass die Priorität des neuen Elements kleiner/gleich ist zu der des aktuell letzen Elements.  
In diesem Fall verfährt man wie in der normalen Queue.

```java
// Fall 3: newNode muss an den Beginn
if (newNode.getPriority() > head.getPriority()) {
  newNode.setNext(head);
  head = newNode;
}
```
*Fall 3* erkennt man daran, dass die Priorität des neuen Elements größer ist als die des aktuell ersten Elements.
In diesem Fall wird der Pointer des neuen Elements auf den aktuellen `head` (das neue 2. Element) umgebogen (Z. 3).
Dann wird der `head`-Pointer auf das neue Element umgebogen (Z. 4).
Das neue Element wird also wie beim Stack "von oben" auf die Schlange aufgeschoben.

```java
PriorityNode node = head;

while (node.getNext().getPriority() >= newNode.getPriority()) node = node.getNext();

newNode.setNext(node.getNext());
node.setNext(newNode);
```
Interessant ist der *Default Case*, *Fall 4*:  
Hier muss erst einmal herausgefunden werden, wo das neue Element eingefügt werden muss,
Dafür hangelt man sich, von `head` beginnend (Z. 1), solange durch alle Elemente bis man das Element findet, deren Nachfolger eine kleinere Priorität als das einzufügende Element besitzt (Z. 3).
Denn: Zwischen dieser Node `node` und der nachfolgenden Node `node.getNext()` musss die neue Node `newNode` einsortiert werden.

Zuerst wird der Pointer der `newNode` auf die `node.getNext()` umgebogen (Z. 5).
Dann wird der Pointer der `node` auf die `newNode` umgebogen (Z. 6).
Das neue Element wird also zwischen dem gefundenen Element `node` und seinem Nachfolger `node.getNext()` eingeschoben.


## Nutzungsszenarien

Queues sind sehr hilfreich, um Prozesse zu modellieren.
Im Unterricht haben wir zum als Beispiel das Wartezimmer kennengelernt.

Ein weitere Einsatzbereich ist die Modellierung asynchroner Abläufe:
Man kann alle zu erledigenden Aufgaben in eine Schlange ablegen.
Möchte man nun eine Aufgabe erledigen, nimmt man sich einfach die oberste herunter und arbeitet sie ab.
So wird es ermöglicht, mit einem Prozess Aufgaben zu erstellen, die dann von mehreren *Worker*-Prozessen abgearbeitet werden.
Beispielhaft für diese Arbeitsweise ist die Becherschlange bei Starbucks: Hier gibt es einen Kassierer, der neue Becher/Aufgaben in eine Schlange stellt.
Nun gibt es mehrere Baristas/Worker, die diese Schlange abarbeiten [^1].

Der wesentliche Unterschied zwischen Stack und Queue ist, wo neue Elemente angehangen werden.
Fügt man einem Stack schneller Elemente hinzu, als man sie wieder entfernt, so wird man die unteren Elemente nie zu sehen bekommen - für eine Aufgabenverwaltung ist das nicht zu gebrauchen.

Fügt man hingegen der Queue schneller Elemente hinzu, als man sie wieder entfernt, wird auch jedes Element wieder entfernt werden - perfekt für eine Aufgabenverwaltung.

[^1]: [https://particular.net/blog/what-starbucks-can-teach-us-about-software-scalability](https://particular.net/blog/what-starbucks-can-teach-us-about-software-scalability)

