public class SortedList<ContentType extends Sortable<ContentType>> extends List<ContentType> {
  public void setContent(ContentType pContent) {
    this.remove();
    this.insert(pContent);
  }

  public void insert(ContentType pContent) {
    if (this.isEmpty()) {
      super.append(pContent);
      return;
    }

    // Save current Element
    String currentId = this.getContent().getID();

    super.toFirst();

    // Search Element
    while (
      super.hasAccess() &&
      (pContent.compareTo(this.getContent()) == 1)
    ) { super.next(); }

    super.insert(pContent);

    // Go back to current
    this.getByID(currentId);
  }

  public void append(ContentType pContent) {
    this.insert(pContent);
  }

  public void concat(List<ContentType> pList) {
    pList.toFirst();

    while (!pList.hasAccess()) {
      this.append(pList.getContent());
      pList.remove();
    }
  }

  public void previous() {
    if (!this.hasAccess()) { return; }
    if (this.isEmpty()) { return; }

    // Save Current
    ContentType current = this.getContent();
    this.toFirst();

    String idOfLast = current.getID();
    for (
      this.toFirst();
      this.hasAccess() &&
      !(this.getContent().compareTo(current) == 0);
      this.next()
    ) { idOfLast = this.getContent().getID(); }

    // Go to idOfLast
    this.getByID(idOfLast);
  }

  public void getByID(String pId) {
    this.toFirst();
    while (
      this.hasAccess() &&
      this.getContent().getID().equals(pId)
    ) { this.next(); }
  }

  public void removeByID(String pId) {
    this.getByID(pId);
    this.remove();
  }
}
