public class SortedList<ContentType extends Sortable<ContentType>> extends List<ContentType> {
  public void setContent(ContentType pContent) {
    //TODO
  }

  public void insert(ContentType pContent) {
    //TODO
  }

  public void append(ContentType pContent) {
    //TODO
  }

  public void concat(List<ContentType> pList) {
    pList.toFirst();

    while (!pList.hasAccess()) {
      append(pList.getContent());
      pList.remove();
    }
  }

  public void previous() {
    //TODO
  }

  public void getByID(String pId) {
    //TODO
  }

  public void removeByID(String pId) {
    //TODO
  }
}
