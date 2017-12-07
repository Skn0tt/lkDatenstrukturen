# Stack
*Stapel*

- Dynamische Listenstruktur
- LIFO (Last in, First Out)
  - Werden schneller Elemente hinzugefügt als entfernt, bleiben die untersten (ersten) Elemente unberührt

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

# Nutzungsszenarien

- Hilfreich, um Prozesse zu modellieren
- Beispiel:
  - Klausurenstapel
