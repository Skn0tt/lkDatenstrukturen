# List
*Liste*

Die Liste ist die letzte dynamische Datenstruktur der Reihe.
Im Gegensatz zu Stack und Queue kann man in ihr an jeder Stelle einfügen, entfernen und lesen.

Die Liste stellt dafür ein aktuelles Element bereit, welches entweder zum Beginn, zum Ende oder zum nächsten Element verschieben kann.

## Modellierung

![Modellierung Node](https://www.planttext.com/plantuml/svg/SoWkIImgAStDuKhEIImkLl3BJqbLgEPIKD1LSCxFAqdDAmcfB4Y5iO7YsWfb-Paf2iMf9Nwf5IKQcc0XiAvq525atK0PA1SeMSlba9gN0hGH0000 "UML-Diagramm Node")

Die Modellierung der `Node` ist schon aus Stack und Queue bekannt, diese verändert sich nicht.

![Modellierung List](https://www.planttext.com/plantuml/svg/XP312i8m38RlUueS5V4DH2QY9_7a2yGsk6BNbYOR3l7ThJXSAuYv_L-_92Kr2qRfMbifYynmC2nmho3L4evU4pI4Ei-2nkOPhzAHVQC5dBstX0uCxzeWmsp-nHlaZLB4_7dflT7Ww2Ob4RytaIUuzKx8oMa811UICPOzFF5Sxz5sL8WO0ZdzHpAEAOwJVZdAEuKoUrrjbKrRWqsaC2ErlgVqtqTLfztfuay0 "UML-Diagramm Liste")

Die `List` speichert drei Pointer zwischen:
- `head` zeigt auf das erste Element
- `tail` zeigt auf das letzte Element
- `aktuell` zeigt auf das aktuelle Element

`aktuell` ist ein Pointer auf ein Element der Liste, das man *aktuell* betrachtet.
Es kann dabei von folgenden Methoden verschoben werden:

- `next()` verschiebt zu seinem Nachfolger.
- `toFirst()` verschiebt zum ersten Element.
- `toLast()` verschiebt zum letzen Element.

Der Inhalt des aktuellen Element kann mit `setContent()` verändert werden.

Mit folgenden Methoden kann man neue Elemente anhängen:
- `append()` hängt ein neues Element am Ende der Liste an.
- `insert()` fügt ein neues Element vor dem aktuellen ein.
- `concat()` verkettet zwei Listen.

`remove()` entfernt das aktuelle Element.

## Algorithmen
Die Methoden der Klasse sind hier noch einmal aufgelistet, die interessanten auch erklärt.

#### isEmpty()
```java
boolean isEmpty() {
  return head == null;
}
```

#### hasAccess()
```java
boolean hasAccess() {
  return aktuell != null;
}
```
Eine Liste "hat Zugriff", wenn `aktuell` auf ein Element zeigt.
Dann kann man mit ihr arbeiten, "auf sie zugreifen".
Diese Methode ist hilfreich um durch die Liste zu iterieren, aber auch um in den anderen Methoden Fehlern vorzubeugen.

#### next()
```java
void next() {
  if (!hasAccess()) return;
  aktuell = aktuell.getNext();
}
```

#### toFirst()
```java
void toFirst() {
  aktuell = head;
}
```

#### toLast()
```java
void toLast() {
  aktuell = tail;
}
```

#### getContent()
```java
ContentType getContent() {
  if (!hasAccess()) return null;

  return aktuell.getContent();
}
```

#### setContent()
```java
void setContent(ContentType content) {
  if (!hasAccess()) return;
  if (aktuell.getContent() == null) return;

  aktuell.setContent(content);
}
```

#### append()
```java
void append(ContentType content) {
  Node node = new Node(content);

  if (isEmpty()) {
    head = node;
    tail = node;
  } else {
    tail.setNext(node);
    tail = node;
  }
}
```
Die beiden Fälle sind schon aus der [Queue](https://simonknott.de/articles/Queue) bekannt.

#### insert()
```java
void insert(ContentType content) {
  if (content == null) return;
  if (!hasAccess() && isEmpty()) return;
  if (!hasAccess() && !isEmpty()) {
    append(content);
    return;
  }
  Node node = new Node(content);

  if (aktuell == head) {
    node.setNext(head);
    head = node;
  } else {
    Node last = getNodeBefore(aktuell);
    last.setNext(node);
    node.setNext(aktuell);
  }
}
```
Zu beginn werden die Sonderfälle abgearbeitet:
- Falls `Content` unzulässig ist, passiert nichts. (Z. 2)
- Falls wir keinen Zugriff haben und die Liste leer ist, passiert nichts (Z. 3)
- Falls wir keinen Zugriff haben und die Liste nicht leer ist, wird stattdessen angehängt. (Z. 4)
- Falls `aktuell` auf den ersten Wert zeigt, passiert das selbe wie beim [Stack](https://simonknott.de/articles/Stack).

Sonst wird der Wert vor dem aktuellen mithilfe der unten erklärten Helfer-Funktion `getNodeBefore()` abgerufen und das neue Element zwischen dieses und das aktuelle eingeschoben.

##### getNodeBefore()
```java
Node getNodeBefore(Node n) {
  Node node = head;
  while (
    node != null &&
    node.getNext() != n
  ) {
    node = node.getNext();
  }
  return node;
}
```
Diese Methode gibt das Element vor einem übergebenen Element `n` zurück.
Es beginnt beim ersten Element und traversiert die Liste.
Findet es das Element `node`, das vor `n` liegt, für das also nicht gilt `node.getNext() != n` (Z. 5), bricht es ab und gibt dieses Element zurück.

#### concat()
```java
concat(List<ContentType> list) {
  if (list == null) return;
  if (list.isEmpty()) return;

  this.tail.setNext(list.head);
  this.tail = list.tail;
  list.head = null;
}
```
Diese Methode verkettet zwei Listen.
Zuerst wird einigen Fehlern vorgebeugt: Wird `null` oder eine leere Liste übergeben, passiert nichts (Z. 2, 3).

Ist die übergebene Liste zulässig, werden die beiden Listen "angedockt":
An den eigenen `tail` wird der `head` der übergebenen Liste angehängt (Z. 5), dann wird der `tail` der übergebenen Liste zum neuen `tail` (Z. 6).
Zum Schluss wird die übergebene Liste geleert: Dafür genügt es, den `head` dieser Liste auf `null` zu setzen (Z. 7).

## Snippets
```java
while (list.hasAccess()) {
  // Do something

  list.next();
}
```

Mit diesem Snippet kann man durch eine Liste iterieren.

## Nutzungsszenarien

- Hilfreich, um Prozesse zu modellieren
- Beispiel: Ka!!!!
