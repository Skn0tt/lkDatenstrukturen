import org.farng.mp3.id3.*;
import org.farng.mp3.*;

public class Playlist {
  private Queue<Lied> playlist;
  private Lied aktuell;
  private MP3Player player;

  public Playlist() {
    //TODO Aufgabeteil b)
    
  }

  /**
  *  ...
  *  ...
  *
  * @param Dateiname der MP3.
  */
  public void hinzufuegen(String mp3Name) {
    try {
      MP3File mp3 = new MP3File(mp3Name);
      AbstractID3v2 tags = mp3.getID3v2Tag();
      
      //TODO Aufgabeteil c)
      
    } catch (Exception e) {
    }
  }
     
    /**
   * Verschiebt den ersten Titel an die letzte Stelle der Warteschlange.
   * Startet die Wiedergabe.
   */

  public void abspielen() {
    //TODO Aufgabeteil d)
    
  }

  /**
  * Gibt den aktuell abgespielten tTitel für die Ausgabe zurueck.
  *
  * @return Dateiname des aktuellen Titels.
  */
  public String aktuellerTitel() {
    //TODO Aufgabeteil e)
    return null;
  }
  /**
  *  ...
  *  ...
  *
  * @return ...
  */
  public String anzeigen() {
    //TODO Aufgabeteil f)
    return null;
  }
  /**
  *  ...
  *  ...
  */
  public void start() {
    player = new MP3Player(aktuell);
    player.start();
  }
  /**
  *  ...
  *
  */
  public void stop() {
    player.end();
  }
}
