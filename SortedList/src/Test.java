import java.util.Random;

public class Test {
  public static void main(String... args) {
    SortedList<Item> list = new SortedList<>();

    int[] ints = new Random().ints(100, 0, 100).toArray();

    for (int i : ints) { list.insert(new Item("" + i)); }

    list.toFirst();
    while (list.hasAccess()) {
      System.out.println(list.getContent().getID());
      list.next();
    }
  }
}
