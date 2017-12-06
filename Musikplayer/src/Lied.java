public class Lied {
  private String titel;
  private String interpret;
  private String mp3Name;

  public Lied(String titel, String interpret, String mp3Name) {
    this.titel = titel;
    this.interpret = interpret;
    this.mp3Name = mp3Name;
  }

  public String getTitel() {
    return this.titel;
  }

  public String getInterpret() {
    return this.interpret;
  }

  public String getMp3Name() {
    return this.mp3Name;
  }
}
