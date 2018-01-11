/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 09.11.2015
  * @author B. Reichelt
  */
public class SorterTest {
  public static void main(String[] args) {
    Sorter s = new Sorter();

    List<Integer> liste = s.erzeugeZufallsListe(10000);
    System.out.println("\nUnsortiert:");
    System.out.println(s.ausgabe(liste));

    long timeStart = System.currentTimeMillis();
    liste = s.mergesort(liste);
    long time = System.currentTimeMillis() - timeStart;
    System.out.println("\nSortiert:");
    System.out.println(s.ausgabe(liste));
    System.out.println("\nZeit:");
    System.out.println(time);
  } // end of main
} // end of class SorterTest
