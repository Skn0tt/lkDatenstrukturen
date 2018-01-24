public interface Sortable<ContentType> {
  int compareTo(ContentType pContent);

  String getID();
}
