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

# Nutzungsszenarien

- Hilfreich, um Prozesse zu modellieren
- Beispiel:
  - Wartezimmer
  - Becherschlange bei Starbucks
- Multithreading
  - Mehrere Aufgaben werden in der Schlange abgelegt
  - Jeder Worker kann sich welche davon nehmen
