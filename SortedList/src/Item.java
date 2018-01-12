public class Item implements Sortable<Item> {
  // Anfang Attribute
  private String iD;

  // Ende Attribute
  public Item(String pID) {
    this.iD = pID;
  }

  // Anfang Methoden
  public String getID() {
    return iD;
  }

  @Override
  public int compareTo(Item pItem) {
    return iD.compareTo(pItem.getID());
  }

  @Override
  public String toString() {
    return iD;
  }

  // Ende Methoden
}
