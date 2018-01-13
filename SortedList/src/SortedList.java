public class SortedList<ContentType extends Sortable<ContentType>> extends List<ContentType> {
  public void setContent(ContentType pContent) {
    this.remove();
    this.insert(pContent);
  }

  public void insert(ContentType pContent) {
    if (pContent == null) { return; }
    if (this.isEmpty()) {
      super.append(pContent);
      return;
    }

    // Save Current
    String currentId = null;
    if (hasAccess()) {
      currentId = this.getContent().getID();
    }

    // Actual insert
    this.toFirst();
    while (this.hasAccess() && this.getContent().compareTo(pContent) < 0) {
      this.next();
    }

    if (hasAccess()) { super.insert(pContent); }
    else {
      super.append(pContent);
    }

    // Restore Current
    if (currentId != null) { this.getByID(currentId); }
    else {
      toLast();
      next();
    }
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

    ContentType currentItem = this.getContent();
    int steps = 0;
    toFirst();

    while (hasAccess() && getContent() != currentItem) {
      steps++;
      next();
    }

    toFirst();

    for (int i = 0; i < steps-1; i++) { next(); }
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
