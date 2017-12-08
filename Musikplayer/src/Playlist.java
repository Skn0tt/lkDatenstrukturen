import org.farng.mp3.id3.*;
import org.farng.mp3.*;

public class Playlist {
  private PriorityQueue<Lied> playlist = new PriorityQueue<>();
  private Lied aktuell;
  private MP3Player player;

  public boolean isEmpty() {
    return playlist.isEmpty();
  }

  /**
  *  ...
  *  ...
  *
  */
  public void hinzufuegen(String mp3file) {
    try {
      MP3File mp3 = new MP3File(mp3file);
      AbstractID3v2 tags = mp3.getID3v2Tag();

      playlist.enqueue(
        new Lied(
          tags.getSongTitle(),
          tags.getLeadArtist(),
          mp3file
        ),
              0
      );
    } catch (Exception e) {}
  }
     
    /**
   * Verschiebt den ersten Titel an die letzte Stelle der Warteschlange.
   * Startet die Wiedergabe.
   */

  public void abspielen() {
    playlist.enqueue(aktuell, 0);
    aktuell = playlist.front();
    playlist.dequeue();

    start();
  }

  /**
  * Gibt den aktuell abgespielten tTitel f�r die Ausgabe zurueck.
  */
  public String aktuellerTitel() {
    if (aktuell == null) return "";

    return aktuell.getTitel();
  }
  /**
  *  ...
  *  ...
  *
  * @return ...
  */
  public String anzeigen() {
    PriorityQueue<Lied> temp = new PriorityQueue<>();
    StringBuilder builder = new StringBuilder();

    while (!playlist.isEmpty()) {
      builder.append(playlist.front());
      builder.append('\n');

      temp.enqueue(playlist.front(), 0);
      playlist.dequeue();
    }

    playlist = temp;
    return builder.toString();
  }
  /**
  *  ...
  *  ...
  */
  public void start() {
    if (aktuell == null) return;

    player = new MP3Player(aktuell);
    player.start();
  }
  /**
  *  ...
  *
  */
  public void stop() {
    if (player == null) return;

    player.end();
  }
}
