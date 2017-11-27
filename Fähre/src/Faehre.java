/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 09.10.2015
  * @author B. Reichelt
  */
import java.io.*;


public class Faehre {
  // Anfang Attribute
  /** Stack für die erste Spur mit dem Namen reihe1*/
  Stack<Auto> reihe1 = new Stack<>();

  /** Stack für die zweite Spur  mit dem Namen reihe2*/
  Stack<Auto> reihe2 = new Stack<>();

  /** Stack für die dritte Spur  mit dem Namen reihe3*/
  Stack<Auto> reihe3 = new Stack<>();

   /** Stack für die Wartespur  mit dem Namen wartereihe*/
   Stack<Auto> wartereihe = new Stack<>();

  /** Freie Länge der ersten Reihe in Metern */
  private double freiReihe1 = 0;
  /** Freie Länge der zweiten Reihe in Metern */
  private double freiReihe2 = 0;
  /** Freie Länge der dritten Reihe in Metern */
  private double freiReihe3 = 0;
  /** Maximale Länge der drei Reihen */
  private final double MAXLAENGE;

  // Ende Attribute
  
  /**
  * Erzeugt eine Fähre und die Reihe mit wartenden Autos 
  * @param pDateiname Reihe mit wartenden Autos
  * @param pLaenge maximale Länge der drei Reihen der Fähre
  */
  public Faehre(String pDateiname, double pLaenge) {
    File datei = new File(pDateiname);
    String dateiString = "";
    //Ausbau der Warteschlange aus der Datei
    try {
      FileReader reader = new FileReader(datei);
      BufferedReader puffer = new BufferedReader(reader);
      String s = puffer.readLine();
      
      while (s != null) {
        dateiString = dateiString + s + ";";
        s = puffer.readLine();
      }
      
      reader.close();
      dateiString = dateiString.replace(',', '.');
      
      String[] zeilen = dateiString.split(";");
      
      for (int i = 0; i < zeilen.length; i++) {
        Auto auto = new Auto(Double.parseDouble(zeilen[i]));
        wartereihe.push(auto);
      } // end of for
    } catch (FileNotFoundException e) {
      System.out.println("Datei nicht vorhanden");
    } catch (IOException e) {
      System.err.println(e.toString());
    }
    
    if (pLaenge > 1) {
      freiReihe1 = pLaenge;
      freiReihe2 = pLaenge;
      freiReihe3 = pLaenge;
      MAXLAENGE = pLaenge;
    } else {
      freiReihe1 = 20;
      freiReihe2 = 20;
      freiReihe3 = 20;
      MAXLAENGE = 20;
    } // end of if-else
  }
  
  /**
  * Erzeugt eine Fähre und die Reihe mit wartenden Autos.
  * Die maximale Länge der drei Reihen beträgt 20m.
  * @param pDateiname Reihe mit wartenden Autos
  */
  public Faehre(String pDateiname) {
    File datei = new File(pDateiname);
    String dateiString = "";
    
    //Ausbau der Warteschlange aus der Datei
    try {
      FileReader reader = new FileReader(datei);
      BufferedReader puffer = new BufferedReader(reader);
      String s = puffer.readLine();
      
      while (s != null) {
        dateiString = dateiString + s + ";";
        s = puffer.readLine();
      }
      
      reader.close();
      dateiString = dateiString.replace(',', '.');
      
      String[] zeilen = dateiString.split(";");
      
      for (int i = 0; i < zeilen.length; i++) {
        Auto auto = new Auto(Double.parseDouble(zeilen[i]));
        wartereihe.push(auto);
      } // end of for
    } catch (FileNotFoundException e) {
      System.out.println("Datei nicht vorhanden");
    } catch (IOException e) {
      System.err.println(e.toString());
    }
    MAXLAENGE = 20;
    freiReihe1 = MAXLAENGE;
    freiReihe2 = MAXLAENGE;
    freiReihe3 = MAXLAENGE;
  }
  // Anfang Methoden
  /**
  * Gibt den noch vorhandenen Platz in Reihe 1 zurück.
  * @return freie Meteranzahl in Reihe 1
  */
  public double getFreiReihe1() {
    return freiReihe1;
  }
  /**
  * Gibt den noch vorhandenen Platz in Reihe 2 zurück.
  * @return freie Meteranzahl in Reihe 2
  */
  public double getFreiReihe2() {
    return freiReihe2;
  }
  /**
  * Gibt den noch vorhandenen Platz in Reihe 3 zurück.
  * @return freie Meteranzahl in Reihe 3
  */
  public double getFreiReihe3() {
    return freiReihe3;
  }
  /**
  * Gibt das oberste Auto in Reihe 1 zurück
  * @return oberstes Auto in Reihe 1
  */
  public Auto getTopReihe1() {
    return reihe1.top();
  }
  /**
  * Gibt das oberste Auto in Reihe 2 zurück
  * @return oberstes Auto in Reihe 2
  */
  public Auto getTopReihe2() {
    return reihe2.top();
  }
  /**
  * Gibt das oberste Auto in Reihe 3 zurück
  * @return oberstes Auto in Reihe 3
  */
  public Auto getTopReihe3() {
    return reihe3.top();
  }
  
  /**
  * Das aktuelle wartende Auto fährt in die zugewiesene Reihe.
  * Falls kein Auto wartet, geschieht nichts.
  * @param pReihe Reihe, in die das Auto fahren soll
  */
  public void inReiheFahren(int pReihe) {
    Auto auto = wartereihe.top();
    if (auto != null) {
      switch (pReihe) {
        case 1:
        
        if ((auto.getLaenge() + 0.30) < freiReihe1) {
          if (reihe1.isEmpty()) {
            freiReihe1 = freiReihe1 - auto.getLaenge();
          } else {
            freiReihe1 = freiReihe1 - (auto.getLaenge() + 0.30);
          } // end of if-else
          
          reihe1.push(auto);
          wartereihe.pop();
        }
        
        break;
        
        case 2:
        
        if ((auto.getLaenge() + 0.30) < freiReihe2) {
          if (reihe2.isEmpty()) {
            freiReihe2 = freiReihe2 - auto.getLaenge();
          } else {
            freiReihe2 = freiReihe2 - (auto.getLaenge() + 0.30);
          } // end of if-else
          
          reihe2.push(auto);
          wartereihe.pop();
        }
        
        break;
        
        case 3:
        
        if ((auto.getLaenge() + 0.30) < freiReihe3) {
          if (reihe3.isEmpty()) {
            freiReihe3 = freiReihe3 - auto.getLaenge();
          } else {
            freiReihe3 = freiReihe3 - (auto.getLaenge() + 0.30);
          } // end of if-else
          
          reihe3.push(auto);
          wartereihe.pop();
        }
        
        break;
        
        default:
        System.out.println("Fähre voll!");
      } // end of switch
      
      //System.out.println("Fahre in Reihe " + pReihe);
    } // end of if
    
  }
  /**
  * Gibt das aktuell wartende Auto zurück.
  * @return erstes wartenden Auto
  */
  public Auto erstesWartendesAuto() {
    return wartereihe.top();
  }
  /**
  * Berechnet die Gesamtlänge der noch wartenden Autos.
  * Die Wartereihe ist anschließend leer.
  * @return Gesamtlänge der wartenden Autos.
  */
  public double restlaenge() {
    double rest = 0;

    while (!wartereihe.isEmpty()) {
      rest += wartereihe.top().getLaenge();
      wartereihe.pop();
    }
    
    return rest;
  }
  /**
  * Kurze aber vollständige Beschreibung der Strategie A.
  * @return reihe, in die das aktuell wartende Auto zugewiesen wird.
  */
  public int strategieA() {
    // TODO hier Quelltext einfügen
    return 0;
  }
  
  /**
  * Kurze aber vollständige Beschreibung der Strategie B.
  * @return reihe, in die das aktuell wartende Auto zugewiesen wird.
  */
  public int strategieB() {
    // TODO hier Quelltext einfügen
    return 0;
  }
  /**
  * Kurze aber vollständige Beschreibung der Strategie C.
  * @return reihe, in die das aktuell wartende Auto zugewiesen wird.
  */
  public int strategieC() {
    // TODO hier Quelltext einfügen
    return 0;
  }

  class Auto {
    private double laenge;

    public Auto(double length) {
      this.laenge = length;
    }

    public double getLaenge() {
      return laenge;
    }
  }
  
  
  // Ende Methoden
} // end of Faehre
