public interface Sortable<ContentType> {
  public int compareTo(ContentType pContent);

  public String getID();
}
