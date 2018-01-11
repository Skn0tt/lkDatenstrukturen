import java.util.Random;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 09.11.2017
  * @author B. Reichelt
  */
public class Sorter {
  public List<Integer> erzeugeZufallsListe(int length) {
    List<Integer> list = new List<>();

    int[] ints = new Random().ints(length, 0, length).toArray();

    for (int i : ints) { list.append(i); }

    list.toFirst();

    return list;
  }
  
  
  public String ausgabe(List<Integer> list) {
    StringBuilder builder = new StringBuilder();

    list.toFirst();
    while (list.hasAccess()) {
      builder.append(list.getContent());

      builder.append('\n');

      list.next();
    }

    return builder.toString();
  }

  private void minimumSuchen(List<Integer> list) {
    if (list!=null &&  !list.isEmpty()) {
      list.toFirst();
      Integer minimum = Integer.MAX_VALUE;
      
      while (list.hasAccess()) {
        if (list.getContent() < minimum) {
          minimum = list.getContent();
        } // end of if 
        
        list.next();
      } // end of while
      
      list.toFirst();
      
      while (list.hasAccess() && (list.getContent() != minimum)) {
        list.next();
      } // end of while
    } // end of if
  }

  public List<Integer> minSort(List<Integer> list) {
    List<Integer> result = new List<>();

    while (!list.isEmpty()) {
      minimumSuchen(list);

      result.append(list.getContent());

      list.remove();
    }

    return result;
  }

  public List<Integer> quicksort(List<Integer> list) {
    if (list.isEmpty()) return list;

    List<Integer> a = new List<>();
    List<Integer> b = new List<>();


    list.toFirst();
    int pivot = list.getContent();
    list.next();


    while (list.hasAccess()) {
      int current = list.getContent();

      if (current < pivot) { a.append(current); }
      else { b.append(current); }

      list.remove();
    }

    a = quicksort(a);
    b = quicksort(b);

    a.concat(list);
    a.concat(b);
    return a;
  }

  private int size(List list) {
    list.toFirst();

    int i = 0;
    for (list.toFirst(); list.hasAccess(); list.next()) { i++; }

    return i;
  }

  public List<Integer> mergesort(List<Integer> list) {
    int size = size(list);

    if (size == 1) return list;

    List<Integer> a = new List<>();
    List<Integer> b = new List<>();

    list.toFirst();

    for (int i = 0; i < size / 2; i++) {
      a.append(list.getContent());
      list.next();
    }

    while (list.hasAccess()) {
      b.append(list.getContent());
      list.next();
    }

    a = mergesort(a);
    b = mergesort(b);

    return merge(a, b);
  }

  private List<Integer> merge(List<Integer> a, List<Integer> b) {
    List<Integer> result = new List<>();

    a.toFirst();
    b.toFirst();

    while (a.hasAccess() && b.hasAccess()) {
      if (a.getContent() < b.getContent()) {
        result.append(a.getContent());
        a.next();
      } else {
        result.append(b.getContent());
        b.next();
      }
    }

    while (a.hasAccess()) {
      result.append(a.getContent());
      a.next();
    }

    while (b.hasAccess()) {
      result.append(b.getContent());
      b.next();
    }

    return result;
  }

  // Ende Methoden
} // end of Sorter
