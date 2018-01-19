public class Warentyp implements Sortable<Warentyp> {
  // Anfang Attribute
  private String id;
  private String name;
  private String bild;
  private double preis;

  // Ende Attribute
  public Warentyp(String pName, String pBild, String pEan, double pPreis) {
    this.name = pName;
    this.bild = pBild;
    this.id = pEan;
    this.preis = pPreis;
  }

  // Anfang Methoden
  public String getName() {
    return name;
  }

  public void setName(String pName) {
    this.name = pName;
  }

  public String getBild() {
    return bild;
  }

  public void setBild(String pBild) {
    this.bild = pBild;
  }

  public String getID() {
    return id;
  }

  public double getPreis() {
    return preis;
  }

  public void setPreis(double pPreis) {
    this.preis = pPreis;
  }

  public int compareTo(Warentyp pWare) {
    return this.id.compareTo(pWare.getID());
  }

  // Ende Methoden
}
