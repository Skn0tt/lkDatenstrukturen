# Stack
*Stapel*

- Dynamische Listenstruktur
- LIFO (Last in, First Out)

## Modellierung
Zwei Klassen:

- Stack
  - Kennt oberstes Element
- Node
  - Speichert Wert
  - Kennt nächstes Element

![Beispiel](https://firebasestorage.googleapis.com/v0/b/simonknott-de.appspot.com/o/Stack%20Diaschau%201.png?alt=media&token=8e7981b6-e285-4c46-b046-46e4c79e2498)

- `oberster` (`head`) zeigt auf erstes Element
- jedes Element speichert Inhalt ("Klausur A/B/C/...")
- jedes Element zeigt auf nächstes

## Algorithmen
### Standard
Es gibt vier Standard-Methoden, die einen Stack charakterisieren.

#### isEmpty()
Gibt zurück, ob der Stack leer ist.

```java
return head == null;
```
#### top()
Gibt den Inhalt des obersten Elements zurück.

```java
if (isEmpty()) return null;
return head.getContent();
```
#### push()
Fügt dem Stack ein neues Element `element` hinzu.

```java
element.setNext(head);
head = element;
```

Je nach Modellierung ist es notwendig, den eingefügten Wert auf Zulässigkeit zu überprüfen.

#### pop()
Entfernt das oberste Element.

```java
head = head.getNext();
```
### Erweitert
#### get()
Gibt den $n$-ten Wert zurück, von `head` aus gezählt.

```java
Node<T> node = head;

for (int i = 0; i < index; i++) {
  if (node == null) return null;

  node = node.getNext();
}

return node.getContent()
```

#### set()
Setzt den $n$-ten Wert, von `head` aus gezählt.

```java
Node<T> node = head;

for (int i = 0; i < index; i++) {
  if (node == null) return;

  node = node.getNext();
}

node.setContent()
```

#### insert()
```java
void insert(int index, T value) {
    if (index == 0) {
        push(value);
        return;
    }

    Node<T> previous = getNode(index - 1);
    Node<T> node = new Node<>(value);

    node.setNext(previous.getNext());
    previous.setNext(node);
}
```

#### length()
```java
int length() {
  Node<T> node = head;

  int i = 0;
  while (node.getNext() != null) {
      node = node.getNext();
      i++;
  }

  return i;
}
```
