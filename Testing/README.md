# NRW Abi-Klassen Dynamische Datenstrukturen Tests
Automatisierte Tests für die vorgegebenen Abiturklassen des Landes NRW.

- JUnit 4.12 (Testsuite)
- Gradle (Taskrunner)

## How-To
- Zu testende Klasse in `src/main/java` platzieren
- Im Hauptverzeichnis `$ ./gradlew test` ausführen

Unter Windows `gradlew.bat` benutzen.

## Sonstiges
Was die einzelnen Tests machen, ist im Quellcode dokumentiert.

Testverzeichnis: `src/test/java`

Nameskonvention:  
test der Klasse `Stack` -> `StackTest`  
test 1 der Methode `pop()` -> `testPop1()`
